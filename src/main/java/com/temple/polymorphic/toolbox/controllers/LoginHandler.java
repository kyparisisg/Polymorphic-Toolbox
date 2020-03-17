package com.temple.polymorphic.toolbox.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginHandler {

    @GetMapping("admin.html")
    public ModelAndView greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name); // ?
        return new ModelAndView("AdminDashboard");
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public String login(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
//        model.addAttribute("name", name);
//        return "login";
//    }
//    private static final String LOGIN_VIEW = "login";
//    private static final String LOGOUT_VIEW = "logout";

}
