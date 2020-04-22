package com.temple.polymorphic.toolbox.controllers;

import com.temple.polymorphic.toolbox.services.ServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class LoginHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerService.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        return "navigate";
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String index2(Model model) {
        return "navigate";
    }

//    @RequestMapping(value = "client", method = RequestMethod.GET)
//    public String client(Model model) {
//        return "clientDashboard";
//    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(Model model) {
        LOGGER.debug("index()");
        //return "redirect:/login.jsp";
        return "logout";

    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied(Principal user){
        ModelAndView model = new ModelAndView();
        if(user != null){
            model.addObject("msg", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg",
                    "You do not have permission to access this page!");
        }

        model.setViewName("403");
        return model;
    }

}
