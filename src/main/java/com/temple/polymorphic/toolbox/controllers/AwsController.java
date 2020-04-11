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
@RequestMapping("/aws/")
public class AwsController {


    @Autowired
    private TransferService transferService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferService.class);

    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public ModelAndView uploadFile(){

        return new ModelAndView("aws/uploadFile","command", new FileInfoDto());
    }
//    @RequestMapping(value = "upload", method = RequestMethod.GET)
//    public String uploadFile(){
//
//        return "aws/uploadFile";
//    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String uploadFileUsingAwsApi(FileInfoDto fileInfoDto, Model model){

//        TransferService.fileUpload(s3bucketName,fileInfoDto.getS3dir(),fileInfoDto.getFile_name());

//        model.addAttribute("FileInfoDto", FileInfoDto);
//        model.addAttribute("file_name", file_name);


//        model.addAttribute("id", us.getId());
//        model.addAttribute("firstName", us.getFirstName());
//        model.addAttribute("lastName", us.getLastName());
//        model.addAttribute("email", us.getEmail());
//        model.addAttribute("role", us.getRole());
//        model.addAttribute("request", "Deleted user");


        //Make the API calls to AWS using the FileInfoDto given
        //...
        //...
        //use the transferService to implement the services and just call them from here!

        //add attributes (one or more) to model so you can use them while rendering the awsApiSuccess.jsp
        String status = "The transaction was successfully, the file was uploaded!";
        model.addAttribute("status",status);
        model.addAttribute("file_name",fileInfoDto.getFile_name());
        model.addAttribute("s3dir",fileInfoDto.getS3dir());
        model.addAttribute("bucketName",bucketName);


        return "aws/awsApiSuccess";
    }

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public ModelAndView downloadFile(){

        return new ModelAndView("aws/downloadFile");
    }

    @RequestMapping(value = "download", method = RequestMethod.POST)
    public String downloadFileUsingAwsApi(FileInfoDto fileInfoDto, Model model){
        //Make the API calls to AWS to download a file from S3
        //...
        //...
        //use the transferService to implement the services and just call them from here!

        //add attributes (one or more) to model so you can use them while rendering the awsApiSuccess.jsp
        String status = "The transaction was successfully, the file was downloaded!";
        model.addAttribute("status",status);

        return "aws/awsApiSuccess";
    }

}
