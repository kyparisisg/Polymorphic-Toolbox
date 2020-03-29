package com.temple.polymorphic.toolbox.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/client")
public class ClientViewController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView clientRed1(Model model) {

        return new ModelAndView("clientDashboard");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView clientRed2(Model model) {

        return new ModelAndView("clientDashboard");
    }
}
