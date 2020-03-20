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

@Controller
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerService.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index(Model model) {

        return new ModelAndView("AdminDashboard");
    }

    @GetMapping("/get")
    public ModelAndView getUserFrom() {
        return new ModelAndView("GetUser");
    }

    @GetMapping("/get/{userEmail}")
    public ModelAndView getUser(@PathVariable String userEmail, Model model)  {
        UserDto userDto = userService.getUser(userEmail);

        model.addAttribute("user",userDto);
        model.addAttribute("firstName", userDto.getFirstName());
        model.addAttribute("lastName",userDto.getLastName());
        model.addAttribute("email",userDto.getEmail());
        model.addAttribute("role",userDto.getRole());

        return new ModelAndView("DisplayUser");
        //return ResponseEntity.ok(userService.getUser(userEmail));
        //return DisplayUser(userService.getUser(userEmail));
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public ModelAndView user() {
        return new ModelAndView("saveuser", "command", new UserDto());
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("SpringWeb")UserDto userDto, ModelMap model) {
        userService.addUser(userDto);
        model.addAttribute("firstName", userDto.getFirstName());
        model.addAttribute("lastName", userDto.getLastName());
        model.addAttribute("email", userDto.getEmail());
        model.addAttribute("request", "Add new user");

        return "addsuccess";
    }

    @PutMapping("/update")
    public String updateUser(@ModelAttribute("SpringWeb")UserDto userDto, ModelMap model){
        userService.updateUser(userDto);
        model.addAttribute("firstName", userDto.getFirstName());
        model.addAttribute("lastName", userDto.getLastName());
        model.addAttribute("email", userDto.getEmail());
        model.addAttribute("request", "Update existing user");

        return "addsuccess";
    }

    @DeleteMapping("/delete/{email}")
    public void deleteUser(@PathVariable String email){
        userService.deleteUser(email);
    }

}
