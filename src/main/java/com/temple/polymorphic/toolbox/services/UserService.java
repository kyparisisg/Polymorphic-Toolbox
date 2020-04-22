package com.temple.polymorphic.toolbox.services;

import com.temple.polymorphic.toolbox.PermissionRepository;
import com.temple.polymorphic.toolbox.TransactionRepository;
import com.temple.polymorphic.toolbox.UserRepository;
import com.temple.polymorphic.toolbox.ServerRepository;
import com.temple.polymorphic.toolbox.models.User;
import com.temple.polymorphic.toolbox.dto.UserDto;
import com.temple.polymorphic.toolbox.models.Permissions;
import com.temple.polymorphic.toolbox.dto.PermissionsDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private PermissionRepository permissionsRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public void setUserRepository(UserRepository userRepository) { this.userRepository = userRepository; }

    public void setPermissionsRepository(PermissionRepository permissionRepository) { this.permissionsRepository = permissionRepository; }

    public List<UserDto> getUsers() {
        Type listType = new TypeToken<List<UserDto>>() {}.getType();
        return new ModelMapper().map(userRepository.findAll(), listType);
    }

    public UserDto getUser(String email){
        ModelMapper mm = new ModelMapper();
        User user = userRepository.findByEmail(email);  //was .get() at the end
        if(user == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        UserDto userDto = mm.map(user, UserDto.class);

        return userDto;
    }

    public List<UserDto> getSingleUserList(String email){
        ModelMapper mapper = new ModelMapper();
        UserDto userDto = mapper.map(userRepository.findByEmail(email), UserDto.class);
        LinkedList<UserDto> list = new LinkedList<UserDto>();
        //hide password of user
        userDto.setPassword("");
        if(!list.add(userDto)){
            //something went wrong
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not create list with single user.");
        }
        return list;
    }

    public UserDto getUserById(Long id){
        ModelMapper mm = new ModelMapper();
        User user = userRepository.findById(id).get();
        if(user == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        UserDto userDto = mm.map(user, UserDto.class);

        return userDto;
    }

    public Long addUser(UserDto userdto){

        if( userdto.getEmail() == null || !(userdto.getEmail().contains("@")) )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        //generate tmp password -- change this to set password with email invitation
        String randToken = generateRndPassword();
        String encodedPass = new BCryptPasswordEncoder().encode(randToken);//to encrypt the random generated password
        userdto.setPassword(encodedPass);
        User user = new User(userdto.getFirstName(), userdto.getLastName(), userdto.getEmail(), userdto.getPassword(), userdto.getRole());
        if( userRepository.findByEmail(userdto.getEmail()) == null ) {
            //send user email invitation
            if(inviteUser(userdto, randToken)){
                if(user.getRole().equalsIgnoreCase("admin")){
                    user.setRole("ROLE_ADMIN");
                }
                //default role if not given
                user.setRole("ROLE_USER");
                //success, then save user in db
                userRepository.save(user);

                return user.getId(); //returns the assigned ID of the user in the db (the PK)
            }
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
            // throw exception if email-invitation could not be sent to new user
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);

    }

    //update the user's password according to given email (only call when user has authenticate) or store user.id to cookies when logged in
    public UserDto updateUser(UserDto userdto){
        if( userdto.getEmail() == null || !(userdto.getEmail().contains("@")) )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        //retrieve user.id and set it user obj, then update value in db
        User userFound = userRepository.findByEmail(userdto.getEmail());
        ModelMapper modelmapper = new ModelMapper();

        if(userFound != null){
            //user exists so update only the given values from userdto obj
            User user = modelmapper.map(userFound, User.class);
            if(userdto.getFirstName()!=null && !userdto.getFirstName().isEmpty() && userdto.getFirstName().length() > 1)
                user.setFirstName(userdto.getFirstName());

            if(userdto.getLastName()!=null && !userdto.getLastName().isEmpty() && userdto.getLastName().length() > 1)
                user.setLastName(userdto.getLastName());

            if(userdto.getPassword()!=null && !userdto.getPassword().isEmpty() && userdto.getPassword().length() > 6) {
                String encodedPass = new BCryptPasswordEncoder().encode(userdto.getPassword());//to encrypt password
                user.setPassword(encodedPass);
            }
            if(userdto.getRole()!=null && !userdto.getRole().isEmpty() && userdto.getRole().length() > 3)
                user.setRole(userdto.getRole());

            userRepository.save(user);
            UserDto userdto2 = modelmapper.map(user, UserDto.class);
            return userdto2;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    }

    public void deleteUser(String email){
        if( email.isEmpty() || !(email.contains("@")) )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        User user = userRepository.findByEmail(email);
        if(user != null) {
            //user found so delete existing user
            if (permissionsRepository.findUserByEmail(email) != null) { //find foreign keys in permissions
                permissionsRepository.deleteByUser(user);
            }
            if (transactionRepository.findUserByEmail(email) != null) { //find foreign keys in transactions
                transactionRepository.deleteByUser(user);
            }
            //delete user
            userRepository.delete(user);
            return;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }


    private boolean inviteUser(UserDto userdto, String UnEncryptedPassword) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(userdto.getEmail());

        msg.setSubject("Polymorphic Toolbox Registration Invitation!");
        msg.setText("Hello, " + userdto.getLastName() + "\n Please use the following temporary password to login: " + UnEncryptedPassword + "\n");

        try {
            javaMailSender.send(msg);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String generateRndPassword(){
        //not implemented yet
        //String passwd = "password";

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String passwd = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        return passwd;
    }

    public List<PermissionsDto> getPermissions(String email) {
        List<Permissions> perms = permissionsRepository.findAllByEmail(email);
        ArrayList<PermissionsDto> permsDto = new ArrayList<PermissionsDto>();
        for(Permissions perm : perms) {
            permsDto.add(new PermissionsDto(perm.getUser(), perm.getServer(), perm.getCreationDate(), perm.getUsernameCred(), perm.getPasswordCred(), perm.getValid()));
        }
        return permsDto;
    }

    public void addPerm(String email, Long serverId, String username, String password){
        if(email == null || !(email.contains("@")) || serverId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        PermissionRepository permissionsRepository= applicationContext.getBean(PermissionRepository.class);

        Permissions newPerm;
        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            newPerm = new Permissions(userRepository.findByEmail(email), serverRepository.findById(serverId).get());
        }
        else{
            newPerm = new Permissions(userRepository.findByEmail(email), serverRepository.findById(serverId).get(), username, password);
        }
        permissionsRepository.save(newPerm);
    }

    public void deletePerm(Long userId, Long serverId){
        if(userId == null || serverId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        PermissionRepository permissionsRepository= applicationContext.getBean(PermissionRepository.class);

        if(permissionsRepository.findByIds(userId, serverId) != null){
            permissionsRepository.deleteById(permissionsRepository.findByIds(userId, serverId).getId());
            return;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

}
