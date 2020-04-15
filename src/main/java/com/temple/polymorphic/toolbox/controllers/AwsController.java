package com.temple.polymorphic.toolbox.controllers;

import com.temple.polymorphic.toolbox.dto.FileInfoDto;
import com.temple.polymorphic.toolbox.dto.UserDto;
import com.temple.polymorphic.toolbox.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/aws/")
public class AwsController {


    @Autowired
    private TransferService transferService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferService.class);

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView uploadFile(){

        return new ModelAndView("aws/uploadFile","command", new FileInfoDto());
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFileUsingAwsApi(@ModelAttribute("SpringWeb")FileInfoDto fileInfoDto, Model model, CommonsMultipartFile file, HttpSession session) throws IOException {

        String path=session.getServletContext().getRealPath("/");
        String filename=file.getOriginalFilename();

        System.out.println(path+" "+filename);

        try{
            byte barr[]=file.getBytes();

            BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream(path+"/"+filename));
            bout.write(barr);
            bout.flush();
            bout.close();

        }catch(Exception e){System.out.println(e);}



        TransferService.fileUpload(fileInfoDto.getBucket(),fileInfoDto.getS3dir(),fileInfoDto.getFile_name());
        model.addAttribute("fileInfoDto", fileInfoDto);
        String status = "The transaction was successfully, the file was uploaded!";
        model.addAttribute("status",status);
        model.addAttribute("file_name",fileInfoDto.getFile_name());
        model.addAttribute("s3dir",fileInfoDto.getS3dir());
        model.addAttribute("ipv4",fileInfoDto.getIpv4());
        model.addAttribute("user",fileInfoDto.getUser());
        model.addAttribute("bucket",fileInfoDto.getBucket());

        String downloadPath = "";
        model.addAttribute("filepath",filepath);



//        model.addAttribute("bucketName",bucketName);


        return "aws/awsApiSuccess";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView downloadFile(){

        return new ModelAndView("users/delete", "command", new FileInfoDto());
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public String downloadFileUsingAwsApi(@ModelAttribute("SpringWeb")FileInfoDto fileInfoDto, Model model) throws IOException {
        //Make the API calls to AWS to download a file from S3
        //...
        //...
        //use the transferService to implement the services and just call them from here!

        //add attributes (one or more) to model so you can use them while rendering the awsApiSuccess.jsp
        TransferService.fileDownload(fileInfoDto.getBucket(),fileInfoDto.getS3dir(),fileInfoDto.getFile_name());

        String status = "The transaction was successfully, the file was downloaded!";

        model.addAttribute("status",status);


        model.addAttribute("fileInfoDto", fileInfoDto);
        model.addAttribute("file_name",fileInfoDto.getFile_name());
        model.addAttribute("s3dir",fileInfoDto.getS3dir());
        model.addAttribute("bucket",fileInfoDto.getBucket());

        String downloadPath = "C:\\Users\\taira\\Documents\\"+fileInfoDto.getFile_name();
        model.addAttribute("filepath",filepath);



        return "aws/awsApiSuccess";
    }

    // add transfer
    // create bucket
    // delete bucket

}
