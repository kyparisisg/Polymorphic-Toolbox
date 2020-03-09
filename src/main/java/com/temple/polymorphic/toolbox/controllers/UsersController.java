package com.temple.polymorphic.toolbox.controllers;

import com.temple.polymorphic.toolbox.dto.UserDto;
import com.temple.polymorphic.toolbox.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/users")
    public void addUser(@RequestBody UserDto userdto) {
        userService.addUser(userdto);
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody UserDto userdto){
        userService.updateUser(userdto);
    }

    @DeleteMapping("/users")
    public void deleteUser(@RequestBody UserDto userdto){
        userService.deleteUser(userdto);
    }

}
