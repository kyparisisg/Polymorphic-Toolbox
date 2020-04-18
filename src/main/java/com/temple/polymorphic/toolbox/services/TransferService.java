package com.temple.polymorphic.toolbox.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.util.IOUtils;
import com.temple.polymorphic.toolbox.ServerRepository;
import com.temple.polymorphic.toolbox.TransactionRepository;
import com.temple.polymorphic.toolbox.UserRepository;
import com.temple.polymorphic.toolbox.dto.TransactionDto;
import com.temple.polymorphic.toolbox.PermissionRepository;
import com.temple.polymorphic.toolbox.dto.ServerDto;
import com.temple.polymorphic.toolbox.models.Server;
import com.temple.polymorphic.toolbox.models.Permissions;
import com.temple.polymorphic.toolbox.models.Transactions;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.StandardCopyOption;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.nio.file.Files;
import java.util.Iterator;
import java.nio.file.Paths;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
//import sun.rmi.runtime.Log;
import java.util.List;

@Service
public class TransferService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private PermissionRepository permissionsRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    /*----------------------------------------------------------------------------------------------------------------
    S3 OPERATIONS
     ----------------------------------------------------------------------------------------------------------------*/

    // for creating new buckets
    public static void createS3b(String bcknm){
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(Credentials.access_key_id, Credentials.secret_access_key);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-2").withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();


        String newBucketName = ""+bcknm;

        if(s3Client.doesBucketExistV2(newBucketName)){

        }else{
            s3Client.createBucket(newBucketName);
        }

    }

    //Use This to Delete 'Serevers' aka aws buckets from lists for admins
    public static void deleteRequest(String bcknm) throws IOException {
        AmazonS3 s3Client = setUpclient();
        BucketTools.deleteBucket(bcknm,s3Client);
    }

    public static void fileUpload(String bcktnm, String dir,String fileName) throws IOException{

        AmazonS3 s3Client = setUpclient();

            if(s3Client.doesBucketExistV2(bcktnm)){
            try {
                s3Client.putObject(
                        bcktnm,
                        dir + "" + fileName,
                        new File("C:\\Users\\taira\\Documents\\capstone\\Polymorphic-Toolbox\\src\\main\\webapp\\" + fileName)
                );
                File file = new File("C:\\Users\\taira\\Documents\\capstone\\Polymorphic-Toolbox\\src\\main\\webapp\\" + fileName);
                file.delete();

            }
            catch(AmazonServiceException ase){
                System.out.println("Caught an AmazonServiceException, which " +
                        "means your request made it " +
                        "to Amazon S3, but was rejected with an error response" +
                        " for some reason.");
                System.out.println("Error Message:    " + ase.getMessage());
                System.out.println("HTTP Status Code: " + ase.getStatusCode());
                System.out.println("AWS Error Code:   " + ase.getErrorCode());
                System.out.println("Error Type:       " + ase.getErrorType());
                System.out.println("Request ID:       " + ase.getRequestId());
            }
            catch (AmazonClientException ace){
                System.out.println("Caught an AmazonClientException, which " +
                        "means the client encountered " +
                        "an internal error while trying to " +
                        "communicate with S3, " +
                        "such as not being able to access the network.");
                System.out.println("Error Message: " + ace.getMessage());

            }
        }else{
            System.out.println("Bucket does not exist on s3 Instance");
        }
    }
    public static void fileDownload(String bcktnm, String dir,String fileName) throws IOException{
        AmazonS3 s3Client = setUpclient();

        S3Object s3obj = s3Client.getObject(new GetObjectRequest(bcktnm, dir+"/"+fileName));

        InputStream objectdata = s3obj.getObjectContent();

        java.nio.file.Files.copy(objectdata, Paths.get("C:\\Users\\taira\\Documents\\capstone\\Polymorphic-Toolbox\\src\\main\\webapp\\"+fileName),StandardCopyOption.REPLACE_EXISTING);

    }

    //Method that takes the credentials for S3 access and returns amazon s3 client object
    private static AmazonS3 setUpclient() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(Credentials.access_key_id, Credentials.secret_access_key);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-2").withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

        return s3Client;
    }

    /*----------------------------------------------------------------------------------------------------------------
    TRANSFER OPERATIONS
     ----------------------------------------------------------------------------------------------------------------*/

    public List<TransactionDto> getTransactions(String email) {
        List<Transactions> trans = transactionRepository.findAllByEmail(email);
        ArrayList<TransactionDto> transDto = new ArrayList<TransactionDto>();
        for(Transactions tran : trans) {
            transDto.add(new TransactionDto(tran.getUser(), tran.getSrc_server(), tran.getDst_server(), tran.getFileName(), tran.getCreationDate(), tran.getStatus()));
        }
        return transDto;
    }

    public boolean hasPermission(String email, Long serverId){
        Permissions perm = permissionsRepository.findByIds(userRepository.findByEmail(email).getId(), serverId);
        return perm != null;
    }

    public List<ServerDto> getServersForUser(String email){
        List<Server> servers = permissionsRepository.findAllServersByEmail(email);
        ArrayList<ServerDto> serverList = new ArrayList<ServerDto>();
        for(Server server: servers){
            serverList.add(new ServerDto(server.getId(), server.getName(), server.getIp(), server.getPort(),
                    server.getUsernameCred(), server.getPasswordCred(), server.getHealth(), server.getRegisterDate(), server.getKeyLocation()));
        }
        return serverList;
    }

    public List<String> getDirectory(Long serverId){
        /*
        Verify server
        Connect to server
        Get ls recursively
        return
         */
        ArrayList<String> directory = new ArrayList<>();
        directory.add("Example Path 1 for server" + serverId);
        directory.add("Example Path 2 for server" + serverId);
        return directory;
    }

    public boolean scp(Long srcServerId, String filePath, Long dstServerId, String targetPath){
        Server srcServer = serverRepository.findById(srcServerId).get();
        Server dstServer = serverRepository.findById(dstServerId).get();
        /*
        SCP from src server to dst server
         */
        return false;
    }
}
