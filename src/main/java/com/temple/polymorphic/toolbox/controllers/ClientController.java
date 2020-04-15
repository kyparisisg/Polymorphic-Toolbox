package com.temple.polymorphic.toolbox.controllers;


import com.temple.polymorphic.toolbox.dto.TransactionDto;
import com.temple.polymorphic.toolbox.dto.UserDto;
import com.temple.polymorphic.toolbox.models.User;
import com.temple.polymorphic.toolbox.services.TransferService;
import com.temple.polymorphic.toolbox.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private TransferService transferService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String clientDashboard(){
        return "clientDashboard";
    }

    @RequestMapping(value = "/transfer" , method = RequestMethod.GET)
    public ModelAndView getViewForTransfer(){

        return new ModelAndView("/client/");
    }


    @RequestMapping(value = "/myHistory" , method = RequestMethod.GET)
    public String myHistory(@CookieValue(value = "username", defaultValue = "NOT_FOUND") String email, Model model){

        return "client/viewHistory";
    }

    public List<TransactionDto> myHistory(String email){

        return null;//transferService.getTransfers(email);
    }


    //this function intentionally not mapped linked to the rest of the front end - must type in link manually
    @RequestMapping(value = "/viewUser" , method = RequestMethod.GET) //this function intentionally not mapped
    public String viewUser(@CookieValue(value = "username", defaultValue = "NOT_FOUND") String email, Model model){
        model.addAttribute("userDto",userService.getUser(email));
        return "client/viewUser";
    }
}
