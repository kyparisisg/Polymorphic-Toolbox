package com.temple.polymorphic.toolbox.controllers;


import com.temple.polymorphic.toolbox.dto.TransactionDto;
import com.temple.polymorphic.toolbox.dto.UserDto;
import com.temple.polymorphic.toolbox.services.TransferService;
import com.temple.polymorphic.toolbox.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
//import sun.text.normalizer.NormalizerBase;

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
        List<TransactionDto> transList = myHistory(email);
        model.addAttribute("transList", transList);
        //model.addAttribute("email", email);
        return "client/viewHistory";
    }

    public List<TransactionDto> myHistory(String email){
        return transferService.getTransactions(email);
    }


    //this function intentionally not mapped linked to the rest of the front end - must type in link manually
    @RequestMapping(value = "/viewUser" , method = RequestMethod.GET) //this function intentionally not mapped
    public String viewUser(@CookieValue(value = "username", defaultValue = "NOT_FOUND") String email, Model model){
        model.addAttribute("userDto",userService.getUser(email));
        return "client/viewUser";
    }

    @RequestMapping(value = "/mySettings", method = RequestMethod.GET)
    public ModelAndView mySettings(@CookieValue(value = "username", defaultValue = "NOT_FOUND") String email, Model model){
        UserDto userDto = new UserDto(email);
        try{
            userDto = userService.getUser(email);
        }catch (Exception e){
            //error handler - Internal Error 500
        }
        model.addAttribute("userDto", userDto);
        model.addAttribute("email", userDto.getEmail());


        return new ModelAndView("client/mySettings", "command", new UserDto());
    }

    @RequestMapping(value = "/mySettings", method = RequestMethod.POST)
    public String mySettingsUpdate(@CookieValue(value = "username", defaultValue = "NOT_FOUND") String email, @ModelAttribute UserDto userDto, Model model){
        if(!email.equals(userDto.getEmail())){
            //throw error and navigate to error handler -- Bad Request
        }
        userDto.setEmail(email);
        userDto = userService.updateUser(userDto);
        model.addAttribute("email", email);
        model.addAttribute("id", userDto.getId());
        model.addAttribute("firstName", userDto.getFirstName());
        model.addAttribute("lastName", userDto.getLastName());
        model.addAttribute("role", userDto.getRole());
        model.addAttribute("regDate", userDto.getRegisterDate());
        if(userDto.getPassword() != null && userDto.getPassword().length() > 20){   //checks if password was updated and encrypted successfully
            model.addAttribute("request", "Succeed");
        }else{
            model.addAttribute("request", "Failed, please contact your Admin!");
        }

        return "client/mySettingsSuccess";
    }
}
