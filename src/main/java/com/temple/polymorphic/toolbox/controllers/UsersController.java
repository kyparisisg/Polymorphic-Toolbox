package com.temple.polymorphic.toolbox.controllers;

import com.temple.polymorphic.toolbox.dto.UserDto;
import com.temple.polymorphic.toolbox.services.ServerService;
import com.temple.polymorphic.toolbox.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerService.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index(Model model) {

        return new ModelAndView("users/manageUser");
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getUserFrom(Model model) {
        List<UserDto> list = getAllUsers();
        model.addAttribute("list", list);

        return "users/allUsers";
    }

    private List<UserDto> getAllUsers(){
        List<UserDto> list = userService.getUsers();
        for (UserDto u : list) {
            u.setPassword("");          //to not return the password
        }
        return userService.getUsers();
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView getUserFrom() {

        return new ModelAndView("users/searchUser", "command", new UserDto()); //maybe new UserDto like user()
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ModelAndView getUser(@ModelAttribute UserDto userDto, Model model)  {
        UserDto us = userService.getUser(userDto.getEmail());

        model.addAttribute("id", us.getId());
        model.addAttribute("firstName", us.getFirstName());
        model.addAttribute("lastName",us.getLastName());
        model.addAttribute("email",us.getEmail());
        model.addAttribute("role",us.getRole());
        model.addAttribute("regDate",us.getRegisterDate());

        return new ModelAndView("users/getUser");
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public ModelAndView addUserForm() {
        return new ModelAndView("users/saveUser", "command", new UserDto());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("SpringWeb")UserDto userDto, ModelMap model) {
        userService.addUser(userDto);
        model.addAttribute("firstName", userDto.getFirstName());
        model.addAttribute("lastName", userDto.getLastName());
        model.addAttribute("email", userDto.getEmail());
        model.addAttribute("role", userDto.getRole());
        model.addAttribute("request", "Add new user");

        return "users/addSuccess";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateUser() {
        return new ModelAndView("updateUser", "command", new UserDto());
    }

    @RequestMapping(value = "/update/{email}", method = RequestMethod.GET)
    public ModelAndView updateUserForm(@PathVariable("email") String email, Model model) {
        UserDto userDto = userService.getUser(email);
        //to not return the password
        userDto.setPassword("");
        model.addAttribute("userDto", userDto);
        model.addAttribute("email",email);

        return new ModelAndView("users/updateUser", "command", new UserDto());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("SpringWeb")UserDto userDto, ModelMap model){
        userDto = userService.updateUser(userDto);
        //to not return the password
        userDto.setPassword("");
        model.addAttribute("firstName", userDto.getFirstName());
        model.addAttribute("lastName", userDto.getLastName());
        model.addAttribute("email", userDto.getEmail());
        model.addAttribute("role", userDto.getRole());
        model.addAttribute("request", "Update existing user");

        return "users/addSuccess";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteUserForm() {

        return new ModelAndView("users/deleteUser", "command", new UserDto()); //maybe new UserDto like user()
    }

    @RequestMapping(value = "/delete/{email}", method = RequestMethod.GET)
    public ModelAndView deleteUserForm(@PathVariable("email") String email, Model model) {
        UserDto userDto = userService.getUser(email);
        //to not return the password
        userDto.setPassword("");
        model.addAttribute("userDto", userDto);
        model.addAttribute("email",email);

        return new ModelAndView("users/deleteUser", "command", new UserDto());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteUser(@ModelAttribute UserDto userDto, Model model){
        UserDto us = userService.getUser(userDto.getEmail());
        userService.deleteUser(userDto.getEmail());
        //to not return the password
        userDto.setPassword("");
        model.addAttribute("firstName", us.getFirstName());
        model.addAttribute("lastName", us.getLastName());
        model.addAttribute("email", us.getEmail());
        model.addAttribute("role", us.getRole());
        model.addAttribute("request", "Deleted user");

        return "users/addSuccess";
    }
//
//    @DeleteMapping("/delete/{email}")
//    public void deleteUser(@PathVariable String email){
//        userService.deleteUser(email);
//    }

}
