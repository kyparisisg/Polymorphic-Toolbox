package com.temple.polymorphic.toolbox.controllers;

import com.temple.polymorphic.toolbox.dto.FileInfoDto;
import com.temple.polymorphic.toolbox.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView uploadFile(){

        return new ModelAndView("client/transfer/scp","command", new FileInfoDto());
    }

    @RequestMapping(value = "scp", method = RequestMethod.POST)
    public String uploadFileUsingAwsApi(FileInfoDto fileInfoDto, Model model){
        //Make the API calls to AWS using the FileInfoDto given
        //...
        //...
        //use the transferService to implement the services and just call them from here!

        //add attributes (one or more) to model so you can use them while rendering the transferSuccess.jsp
        String status = "The transaction was successfully, the file was sent to the server!";
        model.addAttribute("status",status);

        return "client/transfer/transferSuccess";
    }
}
