package com.temple.polymorphic.toolbox.controllers;


import com.temple.polymorphic.toolbox.dto.UserDto;
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

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private TransferService serverService;

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


//    @RequestMapping(value = "/viewhistory" , method = RequestMethod.GET)
//    public ModelAndView myHistory(){
//
//        return new ModelAndView("/client/viewhistory");
//    }

    @RequestMapping(value = "/viewhistory" , method = RequestMethod.GET)
    public String myHistory(@CookieValue(value = "username", defaultValue = "NOT_FOUND") String username, Model model){
        //if cookie value is not found then logout user
        UserDto userDto = userService.getUser(username);
        model.addAttribute("userDto",userDto);

        return "client/viewHistory";
        //return new ModelAndView("/client/viewhistory", "command", userDto);
    }
}
