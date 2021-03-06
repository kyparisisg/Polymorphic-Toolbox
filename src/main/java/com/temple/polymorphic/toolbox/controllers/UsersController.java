package com.temple.polymorphic.toolbox.controllers;

import com.temple.polymorphic.toolbox.dto.PermOperation;
import com.temple.polymorphic.toolbox.dto.PermissionsDto;
import com.temple.polymorphic.toolbox.dto.ServerDto;
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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private ServerService serverService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index() {

        return new ModelAndView("users/manageUser");
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getUserFrom(Model model) {
        List<UserDto> list = getAllUsers();
        model.addAttribute("list", list);

        return "users/get";
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

        return new ModelAndView("users/search", "command", new UserDto()); //maybe new UserDto like user()
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String getUser(@ModelAttribute UserDto userDto, Model model)  {
        //Initially was returning a ModelAndView with a single UserDto attributes but it is changed to
        //return a list with a single UserDto to use the same .jsp view as get all Users
        List<UserDto> list = userService.getSingleUserList(userDto.getEmail());
        model.addAttribute("list", list);

        return "users/get";
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public ModelAndView addUserForm() {
        return new ModelAndView("users/save", "command", new UserDto());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("SpringWeb")UserDto userDto, Model model) {
        Long pk_id = null;
        try{
            pk_id = userService.addUser(userDto);
        }catch (ResponseStatusException e){
            //return this.handleRequest(e, model, "Failed to create new User, please try again");
            String msg = "Could not create new User, please try again!";
            model.addAttribute("msg",msg);
            return "500";
        }
        model.addAttribute("userDto", userDto);    //can user either on jsp ${userDto.field} OR ${field}
        model.addAttribute("id", pk_id);
        model.addAttribute("firstName", userDto.getFirstName());
        model.addAttribute("lastName", userDto.getLastName());
        model.addAttribute("email", userDto.getEmail());
        model.addAttribute("role", userDto.getRole());
        model.addAttribute("request", "Add new user");

        return "users/requestSuccess";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateUser() {
        return new ModelAndView("users/update", "command", new UserDto());
    }

    @RequestMapping(value = "/update/{email}", method = RequestMethod.GET)
    public ModelAndView updateUserForm(@PathVariable("email") String email, Model model) {
//        UserDto userDto = userService.getUser(email);
//        //to not return the password
//        userDto.setPassword("");
//        model.addAttribute("userDto", userDto);
        model.addAttribute("email",email);

        return new ModelAndView("users/update", "command", new UserDto());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("SpringWeb")UserDto userDto, ModelMap model){
        try{
            userDto = userService.updateUser(userDto);
            //to not return the password
            userDto.setPassword("");
            model.addAttribute("id", userDto.getId());
            model.addAttribute("firstName", userDto.getFirstName());
            model.addAttribute("lastName", userDto.getLastName());
            model.addAttribute("email", userDto.getEmail());
            model.addAttribute("role", userDto.getRole());
            model.addAttribute("request", "Update existing user");

            return "users/requestSuccess";
        }catch (Exception e){
            String msg = "Could not update User, please try again!";
            model.addAttribute("msg",msg);
            return "500";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteUserForm() {

        return new ModelAndView("users/delete", "command", new UserDto()); //maybe new UserDto like user()
    }

//    @RequestMapping(value = "/delete/{email}", method = RequestMethod.GET)
//    public ModelAndView deleteUserForm(@PathVariable("email") String email, Model model) {
//        UserDto userDto = userService.getUser(email);
//        //to not return the password
//        userDto.setPassword("");
//        //model.addAttribute("userDto", userDto);
//        model.addAttribute("email",email);
//
//        return new ModelAndView("users/delete", "command", new UserDto());
//    }

    @RequestMapping(value = "/delete/{email}", method = RequestMethod.GET)
    public String deleteUserForm(@PathVariable("email") String email, Model model) {
        try{
            UserDto us = userService.getUser(email);
            userService.deleteUser(email);

            model.addAttribute("id", us.getId());
            model.addAttribute("firstName", us.getFirstName());
            model.addAttribute("lastName", us.getLastName());
            model.addAttribute("email", us.getEmail());
            model.addAttribute("role", us.getRole());
            model.addAttribute("request", "Deleted user");

            return "users/requestSuccess";
        }catch (Exception e){
            String msg = "Could not delete User, please try again!";
            model.addAttribute("msg",msg);
            return "500";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteUser(@ModelAttribute UserDto userDto, Model model){
        try{
            UserDto us = userService.getUser(userDto.getEmail());
            userService.deleteUser(userDto.getEmail());
            //to not return the password
            userDto.setPassword("");
            model.addAttribute("id", us.getId());
            model.addAttribute("firstName", us.getFirstName());
            model.addAttribute("lastName", us.getLastName());
            model.addAttribute("email", us.getEmail());
            model.addAttribute("role", us.getRole());
            model.addAttribute("request", "Deleted user");

            return "users/requestSuccess";
        }catch (Exception e){
            String msg = "Could not delete User, please try again!";
            model.addAttribute("msg",msg);
            return "500";
        }
    }

    @RequestMapping(value = "/permissions/get/{email}", method = RequestMethod.GET)
    public String getPermissions(@PathVariable("email") String email, Model model){
        List<PermissionsDto> perms = getPermissions(email);
        model.addAttribute("perms", perms);
        model.addAttribute("email", email);
        return "users/viewPerm";
    }

    public List<PermissionsDto> getPermissions(@ModelAttribute String email) {
        return userService.getPermissions(email);
    }



    //For the User's Permission
    @RequestMapping(value = "/permissions/add/{email}", method = RequestMethod.GET)
    public ModelAndView setPermissions(@PathVariable("email") String email, Model model){
        //see if user exists
        try {
            UserDto us = userService.getUser(email);

            //add model attributes for email and permissionsDto
            model.addAttribute("email", email);

            //ServerListToDisplay and select one
            List<ServerDto> serversList = serverService.getServers();
            model.addAttribute("list", serversList);

            return new ModelAndView("users/addPermissions", "command", new PermOperation());
        }catch (Exception e){
            String msg = "Could not set user's permission, please try again!";
            model.addAttribute("msg", msg);

            return new ModelAndView("500");
        }
    }

    @RequestMapping(value = "/permissions/add", method = RequestMethod.POST)
    public String setPermissions(@ModelAttribute PermOperation perm, Model model){
        //verify User's existence by email
        UserDto us;
        try{
            us = userService.getUser(perm.getEmail());
        }catch (ResponseStatusException e){
            //return this.handleRequest(e, model, "Failed to set new permission for User. Please try again");
            String msg = "Could not set user's permission, please try again!";
            model.addAttribute("msg",msg);
            return "500";
        }
        //verify Server's existence by IP
        ServerDto s;
        try{
            s = serverService.getServerById(perm.getServerId());
        }catch (ResponseStatusException e){
            //return this.handleRequest(e, model, "Failed to set new permission for User, server id does not exist. Please try again");
            String msg = "Could not set user's permission, server id doesn't exist! Please try again!";
            model.addAttribute("msg",msg);
            return "500";
        }
        //set permission to access server with id, for the given User (email), if permission does not already exist
        try{
            userService.addPerm(perm.getEmail(), perm.getServerId(), perm.getUsername(), perm.getPassword());
            //return success or fail status by adding attributes to the model

            //return user that the permissions were assigned
            model.addAttribute("id", us.getId());
            model.addAttribute("firstName", us.getFirstName());
            model.addAttribute("lastName", us.getLastName());
            model.addAttribute("email", us.getEmail());
            model.addAttribute("role", us.getRole());
            model.addAttribute("request", "Permissions access on server: "+ perm.getServerId() +", granted for user.");

            return "users/requestSuccess";
        }catch (Exception e){
            String msg = "Could not set user's permission, please try again!";
            model.addAttribute("msg",msg);
            return "500";
        }
    }

    @RequestMapping(value = "/permissions/delete/{userId}+{serverId}", method = RequestMethod.POST)
    public String deletePermissions(@PathVariable("userId")Long userId, @PathVariable("serverId") Long serverId, Model model){
        //verify User's existence by email
        UserDto us;
        try{
            us = userService.getUserById(userId);
        }catch (ResponseStatusException e){
            //return this.handleRequest(e, model, "Failed to set new permission for User. Please try again");
            String msg = "Could not delete user's permission, please try again!";
            model.addAttribute("msg",msg);
            return "500";
        }
        //verify Server's existence by IP
        ServerDto s;
        try{
            s = serverService.getServerById(serverId);
        }catch (ResponseStatusException e){
            //return this.handleRequest(e, model, "Failed to set new permission for User, server id does not exist. Please try again");
            String msg = "Could not set user's permission, server id doesn't exist! Please try again!";
            model.addAttribute("msg",msg);
            return "500";
        }
        //delete permission to access server with IP, for the given User (email), if permission exists
        try{
            userService.deletePerm(userId, serverId);
            //return success or fail status by adding attributes to the model

            //return user that the permissions were revoked

            model.addAttribute("id", us.getId());
            model.addAttribute("firstName", us.getFirstName());
            model.addAttribute("lastName", us.getLastName());
            model.addAttribute("email", us.getEmail());
            model.addAttribute("role", us.getRole());
            model.addAttribute("request", "Permissions access on server: "+ serverId +", revoked for user.");

            return "users/requestSuccess";
        }catch (Exception e){
            String msg = "Could not delete user's permission, please try again!";
            model.addAttribute("msg",msg);
            return "500";
        }
    }

    public String handleRequest(ResponseStatusException e, Model model, String reason) {

        model.addAttribute("msg", e.getMessage());
        model.addAttribute("status", e.getStatus());
        model.addAttribute("reason", reason);
        return "error";
    }

    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    public ModelAndView getLogOutPage() {

        return new ModelAndView("users/loginout", "command", new UserDto()); //maybe new UserDto like user()
    }

    @RequestMapping(value = "/loginout", method = RequestMethod.POST)
    public String getLoginOut(@ModelAttribute UserDto userDto, Model model)  {
        //Initially was returning a ModelAndView with a single UserDto attributes but it is changed to
        //return a list with a single UserDto to use the same .jsp view as get all Users
        List<UserDto> list = userService.getSingleUserList(userDto.getEmail());
        model.addAttribute("list", list);

        return "users/loginout";
    }

    @RequestMapping(value = "/aboutus", method = RequestMethod.GET)
    public ModelAndView getAboutUsPage() {

        return new ModelAndView("users/aboutus", "command", new UserDto()); //maybe new UserDto like user()
    }

    @RequestMapping(value = "/aboutus", method = RequestMethod.POST)
    public String getAboutUs(@ModelAttribute UserDto userDto, Model model)  {
        //Initially was returning a ModelAndView with a single UserDto attributes but it is changed to
        //return a list with a single UserDto to use the same .jsp view as get all Users
        List<UserDto> list = userService.getSingleUserList(userDto.getEmail());
        model.addAttribute("list", list);

        return "users/aboutus";
    }


}
