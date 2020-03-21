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

@Controller
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerService.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index(Model model) {

        return new ModelAndView("manageUser");
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView getUserFrom() {

        return new ModelAndView("searchUser", "command", new UserDto()); //maybe new UserDto like user()
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getUserFrom(Model model) {
        LinkedList<UserDto> list = getAllUsers();
        model.addAttribute("list", list);

        return "allUsers";
    }

    private LinkedList<UserDto> getAllUsers(){

        return userService.getUsers();
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ModelAndView getUser(@ModelAttribute UserDto userDto, Model model)  {
        UserDto us = userService.getUser(userDto.getEmail());

        model.addAttribute("firstName", us.getFirstName());
        model.addAttribute("lastName",us.getLastName());
        model.addAttribute("email",us.getEmail());
        model.addAttribute("role",us.getRole());
        model.addAttribute("regDate",us.getRegisterDate());

        return new ModelAndView("getUser");
        //return ResponseEntity.ok(userService.getUser(userEmail));
        //return DisplayUser(userService.getUser(userEmail));
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public ModelAndView addUserForm() {
        return new ModelAndView("saveUser", "command", new UserDto());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("SpringWeb")UserDto userDto, ModelMap model) {
        userService.addUser(userDto);
        model.addAttribute("firstName", userDto.getFirstName());
        model.addAttribute("lastName", userDto.getLastName());
        model.addAttribute("email", userDto.getEmail());
        model.addAttribute("role", userDto.getRole());
        model.addAttribute("request", "Add new user");

        return "addSuccess";
    }



    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateUserForm() {
        return new ModelAndView("updateUser", "command", new UserDto());
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String updateUser(@ModelAttribute("SpringWeb")UserDto userDto, ModelMap model){
        userService.updateUser(userDto);
        model.addAttribute("firstName", userDto.getFirstName());
        model.addAttribute("lastName", userDto.getLastName());
        model.addAttribute("email", userDto.getEmail());
        model.addAttribute("role", userDto.getRole());
        model.addAttribute("request", "Update existing user");

        return "addSuccess";
    }

    @DeleteMapping("/delete/{email}")
    public void deleteUser(@PathVariable String email){
        userService.deleteUser(email);
    }

}
