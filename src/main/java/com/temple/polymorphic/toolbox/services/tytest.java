package com.temple.polymorphic.toolbox.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class tytest {
    public static void main(String[] args) throws IOException {
        boolean IS_WINDOWS = System.getProperty( "os.name" ).contains( "indow" );

        //creating an s3 bucket
//        String bucketName = "testname";
//        TransferService.createS3b(bucketName);

//        TransferService.fileUpload("greekarmy","server 1/","important.txt");
//        TransferService.fileDownload("greekarmy","server 1","important.txt");
        String fileName = "important.txt";
//        Path path = Paths.get("\\capstone\\Polymorphic-Toolbox\\src\\resources");
        File t;
        if(IS_WINDOWS){
            String g = System.getenv("SystemDrive");
            t =  new File(g+"/pop/"+fileName);
            System.out.println(g);
        }else{
            t =  new File("C:\\Users\\taira\\Documents\\capstone\\Polymorphic-Toolbox\\src\\main\\resources\\tempStorage\\"+fileName);
            System.out.println("missed");
        }
//        File t =  new File("C:\\Users\\taira\\Documents\\capstone\\Polymorphic-Toolbox\\src\\main\\resources\\tempStorage\\"+fileName);
        System.out.println(t.exists());


        //deleting a bucket
    }
}
