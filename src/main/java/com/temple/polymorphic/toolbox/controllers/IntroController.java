package com.temple.polymorphic.toolbox.controllers;

import com.temple.polymorphic.toolbox.services.ServerService;
import com.temple.polymorphic.toolbox.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/api/aboutUs")
public class IntroController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerService.class);


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index(Model model) {

        return new ModelAndView("aboutUs");
    }
}
