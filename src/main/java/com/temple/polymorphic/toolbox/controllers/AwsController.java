package com.temple.polymorphic.toolbox.controllers;

import com.temple.polymorphic.toolbox.dto.FileInfoDto;
import com.temple.polymorphic.toolbox.dto.ServerDto;
import com.temple.polymorphic.toolbox.dto.TransferOperation;
import com.temple.polymorphic.toolbox.services.ServerService;
import com.temple.polymorphic.toolbox.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/client/aws/")
public class AwsController {
    public String fdt;

    @Autowired
    private TransferService transferService;

    @Autowired
    private ServerService serverService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferService.class);

    @RequestMapping(value = "/serverSelection", method = RequestMethod.GET)
    public ModelAndView wel(@CookieValue(value = "username", defaultValue = "NOT_FOUND") String email, Model model) {
        //get server list for user
        model.addAttribute("serverList", getServers(email));
        //cascade transfer info
        model.addAttribute("email", email);
        return new ModelAndView("client/aws/src","command", new TransferOperation());

        //return new ModelAndView("client/aws/chooseFile"); //Tyler
    }

    public List<ServerDto> getServers(String email){
        return transferService.getServersForUser(email);
    }

    @RequestMapping(value = "/fileSelection", method = RequestMethod.POST)
    public ModelAndView testjs(@ModelAttribute TransferOperation tran, Model model){
        //verify src server

        try{
//            if(!tran.getSrcServerId().getClass().getName().equals("java.lang.Long")){
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//            }
            if(serverService.getServerById(tran.getSrcServerId())==null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            if(!transferService.hasPermission(tran.getEmail(), tran.getSrcServerId())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        } catch (ResponseStatusException e) {
            return new ModelAndView("403","command", new TransferOperation());
        }
        //get directory for server
        //model.addAttribute("directory", transferService.getDirectory(tran.getSrcServerId()));

        //get all files from default user directory
        transferService.lsServer(tran.getSrcServerId());
        //cascade transfer info
        model.addAttribute("email", tran.getEmail());
        model.addAttribute("srcServerId", tran.getSrcServerId());

        return new ModelAndView("client/aws/fileUpload","command", new TransferOperation());
    }

    @RequestMapping(value = "/fileinput", method = RequestMethod.POST)
    public ModelAndView up(@RequestParam MultipartFile file, HttpSession session){
        String path = session.getServletContext().getRealPath("/");
        String filename = file.getOriginalFilename();

        try{
            byte barr[] = file.getBytes();
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path+"/"+filename));
            Object o = bout;
            bout.write(barr);
            bout.flush();
            bout.close();

            fdt = filename;

        }catch (Exception e){System.out.println((e));}


        return new ModelAndView("client/aws/uploadFile","command", new FileInfoDto());
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFileUsingAwsApi(@ModelAttribute("SpringWeb")FileInfoDto fileInfoDto, Model model) throws IOException {

        fileInfoDto.setFile_name(fdt);
        TransferService.fileUpload(fileInfoDto.getBucket(),fileInfoDto.getS3dir(),fileInfoDto.getFile_name());
        model.addAttribute("fileInfoDto", fileInfoDto);
        String status = "The transaction was successfully, the file was uploaded!";
        model.addAttribute("status",status);
        model.addAttribute("File_name",fileInfoDto.getFile_name());
        model.addAttribute("s3dir",fileInfoDto.getS3dir());
        model.addAttribute("ipv4",fileInfoDto.getIpv4());
        model.addAttribute("user",fileInfoDto.getUser());
        model.addAttribute("bucket",fileInfoDto.getBucket());

        String downloadPath = "";
//        model.addAttribute("filepath",filepath);



//        model.addAttribute("bucketName",bucketName);


        return "client/aws/awsApiSuccess";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView downloadFile(){

        return new ModelAndView("client/aws/downloadFile", "command", new FileInfoDto());
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public String downloadFileUsingAwsApi(@ModelAttribute("SpringWeb")FileInfoDto fileInfoDto, Model model) throws IOException {
        //Make the API calls to AWS to download a file from S3
        //...
        //...
        //use the transferService to implement the services and just call them from here!

        //add attributes (one or more) to model so you can use them while rendering the awsApiSuccess.jsp

        TransferService.fileDownload(fileInfoDto.getBucket(),fileInfoDto.getS3dir(),fileInfoDto.getFile_name());
        String dpath = "C:\\Users\\taira\\Documents\\";
        String status = "The transaction was successfully, the file was downloaded!";
        String filePath = "C:\\Users\\taira\\Documents\\capstone\\Polymorphic-Toolbox\\src\\main\\webapp\\";

        model.addAttribute("status",status);
//        model.addAttribute("dpath",dpath);
        model.addAttribute("file_name",fileInfoDto.getFile_name());
        model.addAttribute("s3dir",fileInfoDto.getS3dir());
        model.addAttribute("file_name",fileInfoDto.getFile_name());
        model.addAttribute("bucket",fileInfoDto.getBucket());



        model.addAttribute("fileInfoDto", fileInfoDto);
        model.addAttribute("file_name",fileInfoDto.getFile_name());
        model.addAttribute("s3dir",fileInfoDto.getS3dir());
        model.addAttribute("bucket",fileInfoDto.getBucket());

//        String downloadPath = "C:\\Users\\taira\\Documents\\"+fileInfoDto.getFile_name();
        model.addAttribute("filePath",filePath);



        return "client/aws/awsApiSuccess";
    }

    // add transfer
    // create bucket
    // delete bucket

}
