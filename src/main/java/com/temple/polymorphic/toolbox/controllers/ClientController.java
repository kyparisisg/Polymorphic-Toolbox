package com.temple.polymorphic.toolbox.controllers;


import com.temple.polymorphic.toolbox.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private TransferService serverService;

    @RequestMapping(value = "/transfer" , method = RequestMethod.GET)
    public ModelAndView getViewForTransfer(){

        return new ModelAndView("/client/");
    }
}
