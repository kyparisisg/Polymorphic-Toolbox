package com.temple.polymorphic.toolbox.controllers;

import com.amazonaws.services.s3.AmazonS3;
import com.temple.polymorphic.toolbox.BucketCredRepository;
import com.temple.polymorphic.toolbox.dto.FileInfoDto;
import com.temple.polymorphic.toolbox.dto.ServerDto;
import com.temple.polymorphic.toolbox.dto.TransferOperation;
import com.temple.polymorphic.toolbox.services.BucketTools;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/client/aws/")
public class AwsController {

    @Autowired
    private TransferService transferService;

    @Autowired
    private ServerService serverService;

    @Autowired
    private BucketTools bucketTools;

    private static String bucketNameC = "greekarmy";
//    private static String bucketNameC = "none";
    private static String privateKey = "none";
    private static String publicKey = "none";


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
        String files = transferService.lsServer(tran.getSrcServerId());
        List<String> fileList = transferService.makeListOfFiles(files); //fileList[0] is the total number of files found, exctract it
        fileList.remove(0);
        //cascade transfer info
        model.addAttribute("fileList", fileList);
        model.addAttribute("email", tran.getEmail());
        model.addAttribute("srcServerId", tran.getSrcServerId());

        return new ModelAndView("client/aws/fileSelect","command", new TransferOperation());
    }


    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String fileInput(@ModelAttribute TransferOperation tran, Model model ){
        //scp the file from the SrcServer to the following path /resources/tempFileStorage
        boolean scpStatus = transferService.scpFrom(tran.getEmail(), tran.getSrcServerId(), tran.getFileName());
        if(!scpStatus){
            //return error file could not be scp to our infrastructure
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "SCP failed to copy file: " + tran.getFileName() + ", to Polymorphic Temporary Storage Drive. Operation Aborted!");
        }

        //take /resources/tempFileStorage/fileToBeUploadedOnS3.ext and uploaded to S3
        //tran.getFileName();
        //BucketName = tran.getEmail();
        //bucketNameC = bucketTools.getBucketCred().getBucketName();
        //verify that file has been uploaded
        String bucketName = bucketTools.getBucketCred().getBucketName();

        try {
            TransferService.fileUpload(bucketName, tran.getEmail(), tran.getFileName());

        }catch (IOException e){
            //error handler
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "");    //just display thrown Exception msg
        }


        //boolean isthere =  TransferService.doesObjectExist(tran.getFileName(),Credentials.bucketNameC, tran.getEmail());
        //delete the file from /resources/tempFileStorage/
        if(!transferService.deleteTempFile(tran.getFileName())){
            //error handler
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not delete tmp file from Polymorphic Temporary Storage Drive.");
        }
        //add a new transaction

        //return status success

        String srcServerName = serverService.getServerNameFromId(tran.getSrcServerId());
        String dstServerName = "Amazon S3";

        model.addAttribute("email",tran.getEmail());
        model.addAttribute("src",srcServerName);
        model.addAttribute("dst",dstServerName);
        model.addAttribute("file",tran.getEmail());
        model.addAttribute("status", 1);
        model.addAttribute("request", "Transfer completed from: " + srcServerName + " to " + dstServerName);

        return "client/transferSuccess";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ModelAndView downloadFile(@CookieValue(value = "username", defaultValue = "NOT_FOUND") String email, Model model ){
        //bucketTool to return list
        AmazonS3 s3Client = transferService.setUpclient();
        List<FileInfoDto> fileList = bucketTools.getBucketItemList(bucketNameC, s3Client, email);   //email is the name of directory to be traversed

        model.addAttribute("fileList",fileList);
        model.addAttribute("email",email);

        return new ModelAndView("client/aws/downloadFile", "command", new TransferOperation());
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ModelAndView downloadFileUsingAwsApi(@CookieValue(value = "username", defaultValue = "NOT_FOUND") String email,@ModelAttribute()TransferOperation tran, Model model)
            throws IOException {

        try {
            TransferService.fileDownload(bucketNameC, email, tran.getFileName());
        }catch (IOException e){
            //error handler if file was not successfully downloaded
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File could not been downloaded from S3 Bucket");
        }
        //since file is downloaded, display servers to select server destination to complete transfer
        model.addAttribute("serverList", getServers(email));
        model.addAttribute("email", email);
        model.addAttribute("fileName", tran.getFileName());


        return new ModelAndView("client/aws/downloadDst", "command", new TransferOperation());
    }

    @RequestMapping(value = "/destScp", method = RequestMethod.POST)
    public String sendFileToDstServer(@ModelAttribute TransferOperation tran, Model model ){
        String dstServerName = serverService.getServerNameFromId(tran.getDstServerId());
        boolean scpStatus = transferService.scpTo(tran.getEmail(), tran.getDstServerId(), tran.getFileName());
        if(!scpStatus){
            //return error file could not be scp to our infrastructure
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "SCP failed to copy file: " + tran.getFileName() + ", to Destination Server:"+ dstServerName +". Operation Aborted!");
        }

        //delete the file from /resources/tempFileStorage/
        if(!transferService.deleteTempFile(tran.getFileName())){
            //error handler
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not delete tmp file from Polymorphic Temporary Storage Drive.");
        }

        String srcServerName = "Amazon S3 Bucket";

        model.addAttribute("email",tran.getEmail());
        model.addAttribute("src",srcServerName);
        model.addAttribute("dst",dstServerName);
        model.addAttribute("file",tran.getEmail());
        model.addAttribute("status", 1);
        model.addAttribute("request", "Transfer completed from: " + srcServerName + " to " + dstServerName);

        return "client/transferSuccess";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView getFilesToDelete(@CookieValue(value = "username", defaultValue = "NOT_FOUND") String email, Model model ){
        //bucketTool to return list
        AmazonS3 s3Client = transferService.setUpclient();
        List<FileInfoDto> fileList = bucketTools.getBucketItemList(bucketNameC, s3Client, email);   //email is the name of directory to be traversed

        model.addAttribute("fileList",fileList);
        model.addAttribute("email",email);

        return new ModelAndView("client/aws/displayS3Files", "command", new TransferOperation());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteFileOnS3(@ModelAttribute TransferOperation tran, Model model ){

        //delete file tran.getFileName() from S3 Bucket directory with name tran.email
        try {
            transferService.fileDelete(bucketNameC,tran.getFileName(),tran.getEmail());
        }catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File could not been deleted on S3 Bucket");
        }


        String srcServerName = "Amazon S3 Bucket";

        model.addAttribute("email",tran.getEmail());
        model.addAttribute("src",srcServerName);
        model.addAttribute("file",tran.getEmail());
        model.addAttribute("status", 1);
        model.addAttribute("request", "Delete file completed on: " + srcServerName );

        return "client/transferSuccess";
    }

}
