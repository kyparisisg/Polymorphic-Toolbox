package com.temple.polymorphic.toolbox.services;


import com.temple.polymorphic.toolbox.UserRepository;
import com.temple.polymorphic.toolbox.models.User;
import com.temple.polymorphic.toolbox.models.UserDto;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) { this.userRepository = userRepository; }

    public List<UserDto> getUsers() {
        Type listType = new TypeToken<List<UserDto>>() {}.getType();
        return new ModelMapper().map(userRepository.findAll(), listType);
    }

    public void addUser(UserDto userdto){
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        User user = new User(userdto.getFirstName(), userdto.getLastName(), userdto.getEmail(), userdto.getPassword(), userdto.getRole());
        if( userRepository.findByEmail(userdto.getEmail()) == null ) {
            userRepository.save(user);
            return;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);

    }

    //update according to email or store user.id to cookies when logged in
    public void updateUser(UserDto userdto){
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        ModelMapper modelmapper = new ModelMapper();
        User user = modelmapper.map(userdto, User.class);
        //retrieve user.id and update user obj, then update value
        User userFound = userRepository.findByEmail(user.getEmail());
        if(userFound != null){
            //user exists so update him
            user.setId(userFound.getId());
            userRepository.save(user);
            return;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);

    }

//    public void deleteUser(Long userId){
//        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
//        if(userRepository.findById(userId) != null){
//            //user found, therefore delete user
//            userRepository.deleteById(userId);
//        }
//
//        //check if user exists in db
//        // to delete
////        userRepository.deleteById(userdto.getId());
//
//    }

    public void deleteUser(UserDto userdto){
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        ModelMapper modelmapper = new ModelMapper();
        User user = modelmapper.map(userdto, User.class);
        if(userRepository.findByEmail(user.getEmail())!=null){
            //user found so delete existing user
            userRepository.deleteById(userRepository.findByEmail(user.getEmail()).getId());
            return;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

}
