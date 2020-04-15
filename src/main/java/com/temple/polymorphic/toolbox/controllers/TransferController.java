package com.temple.polymorphic.toolbox.controllers;

import com.temple.polymorphic.toolbox.dto.TransactionDto;
import com.temple.polymorphic.toolbox.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/client/transfer/")
public class TransferController {

    @Autowired
    private TransferService transferService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferService.class);

    /*@RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView uploadFile(){

        return new ModelAndView("client/transfer/scp","command", new TransactionDto());
    }*/

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String serverList(@CookieValue(value = "username", defaultValue = "NOT_FOUND") String email, Model model) {
        //get list of servers for a given user
        //add to model
        return "client/transfer/scp";
    }

    @RequestMapping(value = "scp", method = RequestMethod.POST)
    public String scpTransfer(Model model) {
        /*
        New transaction function (
        success = SCP function
        if(success){
            Update transaction status in db function ("success")
            String status = "The transfer was successfully, the file was sent to the server!";
        else{
            Update transaction status in db function ("failure")
            String status = "The transfer was NOT successfully, the file was NOT sent to the server!";
        }
        */
        String status = "Here will be result";
        model.addAttribute("status", status);
        return "client/transferSuccess";
    }
}
