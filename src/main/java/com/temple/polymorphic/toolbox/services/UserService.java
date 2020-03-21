package com.temple.polymorphic.toolbox.services;

import com.temple.polymorphic.toolbox.UserRepository;
import com.temple.polymorphic.toolbox.models.User;
import com.temple.polymorphic.toolbox.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public void setUserRepository(UserRepository userRepository) { this.userRepository = userRepository; }

    public LinkedList<UserDto> getUsers() {
        Type listType = new TypeToken<List<UserDto>>() {}.getType();
        return new ModelMapper().map(userRepository.findAll(), listType);
    }

    public UserDto getUser(String userEmail){
        ModelMapper mm = new ModelMapper();
        User user = userRepository.findByEmail(userEmail);  //was .get() at the end
        if(user == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        UserDto userDto = mm.map(user, UserDto.class);

        return userDto;
    }

    public void addUser(UserDto userdto){
        if( userdto.getEmail() == null || !(userdto.getEmail().contains("@")) )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        //generate tmp password -- change this to set password with email invitation
        userdto.setPassword(generateRndPassword());
        User user = new User(userdto.getFirstName(), userdto.getLastName(), userdto.getEmail(), userdto.getPassword(), userdto.getRole());
        if( userRepository.findByEmail(userdto.getEmail()) == null ) {
            //send user email invitation
            if(inviteUser(userdto)){
                //default role if not given
                user.setRole("user");
                //success, then save user in db
                userRepository.save(user);
                return;
            }
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE);
            // throw exception if email-invitation could not be sent to new user
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);

    }

    //update the user's password according to given email (only call when user has authenticate) or store user.id to cookies when logged in
    public void updateUser(UserDto userdto){
        if( userdto.getEmail() == null || !(userdto.getEmail().contains("@")) )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        //retrieve user.id and set it user obj, then update value in db
        User userFound = userRepository.findByEmail(userdto.getEmail());
        ModelMapper modelmapper = new ModelMapper();

        if(userFound != null){
            //user exists so update only the given values from userdto obj
            User user = modelmapper.map(userFound, User.class);
            if(userdto.getFirstName()!=null)
                user.setFirstName(userdto.getFirstName());

            if(userdto.getLastName()!=null)
                user.setLastName(userdto.getLastName());

            if(userdto.getPassword()!=null)
                user.setPassword(userdto.getPassword());

            userRepository.save(user);
            return;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    }

    public void deleteUser(String email){
        if( email.isEmpty() || !(email.contains("@")) )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        ModelMapper modelmapper = new ModelMapper();
//        User user = modelmapper.map(userdto, User.class);
        if(userRepository.findByEmail(email)!=null){
            //user found so delete existing user
            userRepository.deleteById(userRepository.findByEmail(email).getId());
            return;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    private boolean inviteUser(UserDto userdto) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(userdto.getEmail());

        msg.setSubject("Testing from Spring Boot, toolbox invitation!");
        msg.setText("Hello, " + userdto.getLastName() + "\n Please use the following temporary password to login: " + userdto.getPassword() + "\n");

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
        return "password";
    }
}
