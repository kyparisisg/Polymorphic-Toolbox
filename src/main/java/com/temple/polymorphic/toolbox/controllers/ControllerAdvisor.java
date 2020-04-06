package com.temple.polymorphic.toolbox.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.temple.polymorphic.toolbox.services.ServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Controller
//@RequestMapping("/error")
//
//public class ErrorHandler {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ServerService.class);
////
////    @RequestMapping(value = "", method = RequestMethod.GET)
////    @ResponseBody
//    @ExceptionHandler({ UsersController.class})
//    public String index(Model model, HttpServletRequest request, HttpServletResponse resp, Exception ex, ResponseStatusException req) {
//        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        Exception msg = (Exception) request.getAttribute("javax.servlet.error.exception");
//        LOGGER.debug("error");
//        LOGGER.error("Requested URL: "+request.getRequestURL());
//        LOGGER.error("Exception Raised: "+ex);
//        LOGGER.error("Response Status Exception: "+req);
//        LOGGER.error("Response Status Exception: "+resp);
//        model.addAttribute("status",status + "THIS IS STATUS");
//        model.addAttribute("msg",msg);
//        model.addAttribute("ex",ex);
//        model.addAttribute("req",req);
//        model.addAttribute("resp",resp);
//
//        return "error";
//    }
//}
