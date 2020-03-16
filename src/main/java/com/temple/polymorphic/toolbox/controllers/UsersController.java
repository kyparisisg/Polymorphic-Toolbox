package com.temple.polymorphic.toolbox.controllers;

import com.temple.polymorphic.toolbox.dto.UserDto;
import com.temple.polymorphic.toolbox.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId)  {
        return ResponseEntity.ok(userService.getUser(userId));
        //return userService.getUser(userId);
    }

//    @GetMapping("/users")
    @GetMapping()
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

//    @PostMapping("/users")
    @PostMapping()
    public void addUser(@RequestBody UserDto userdto) {
        userService.addUser(userdto);
    }

//    @PutMapping("/users")
    @PutMapping()
    public void updateUser(@RequestBody UserDto userdto){
        userService.updateUser(userdto);
    }

//    @DeleteMapping("/users")
    @DeleteMapping()
    public void deleteUser(@RequestBody UserDto userdto){
        userService.deleteUser(userdto);
    }

}
