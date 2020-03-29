package com.temple.polymorphic.toolbox.controllers;

import com.temple.polymorphic.toolbox.services.ServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerService.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        LOGGER.debug("index()");
//        return "redirect:/login.jsp";
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginRed(Model model) {
        LOGGER.debug("index()");
        //return "redirect:/login.jsp";
        return "login";

    }


    @RequestMapping(value = "login.jsp", method = RequestMethod.GET)
    public String login(Model model) {
        LOGGER.debug("index()");
        //return "redirect:/login.jsp";
        return "login";

    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(Model model) {
        LOGGER.debug("index()");
        //return "redirect:/login.jsp";
        return "mypage";

    }

}
