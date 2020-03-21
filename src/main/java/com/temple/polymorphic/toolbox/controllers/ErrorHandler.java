package com.temple.polymorphic.toolbox.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.temple.polymorphic.toolbox.services.ServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")

public class ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerService.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String index(Model model, HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception msg = (Exception) request.getAttribute("javax.servlet.error.exception");
        LOGGER.debug("error");
        model.addAttribute("status",status);
        model.addAttribute("msg",msg);
        return "error";
    }
}