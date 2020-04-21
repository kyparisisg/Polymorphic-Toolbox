package com.temple.polymorphic.toolbox.services;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.jcraft.jsch.*;
import com.temple.polymorphic.toolbox.ServerRepository;
import com.temple.polymorphic.toolbox.TransactionRepository;
import com.temple.polymorphic.toolbox.UserRepository;
import com.temple.polymorphic.toolbox.dto.TransactionDto;
import com.temple.polymorphic.toolbox.PermissionRepository;
import com.temple.polymorphic.toolbox.dto.ServerDto;
import com.temple.polymorphic.toolbox.models.Server;
import com.temple.polymorphic.toolbox.models.Permissions;
import com.temple.polymorphic.toolbox.models.Transactions;
import com.temple.polymorphic.toolbox.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.StandardCopyOption;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.nio.file.Paths;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;
//import sun.rmi.runtime.Log;


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


    public void setUserRepository(UserRepository userRepository) { this.userRepository = userRepository; }

    public void setPermissionsRepository(PermissionRepository permissionRepository) { this.permissionsRepository = permissionRepository; }

    public void setServerRepository(ServerRepository serverRepository) { this.serverRepository = serverRepository; }

    public void setTransactionRepository(TransactionRepository transactionRepository) { this.transactionRepository = transactionRepository; }

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

    public void addTransaction(String email, Long srcServerId, String filePath, Long dstServerId, int status){
        User user = userRepository.findByEmail(email);
        Server srcServer = serverRepository.findById(srcServerId).get();
        Server dstServer = serverRepository.findById(dstServerId).get();
        String file = filePath.trim(); //will need more string manipulation to only get file
        Transactions transaction = new Transactions(user, srcServer, dstServer, file, status);
        transactionRepository.save(transaction);
    }

    public int scp(String email, Long srcServerId, String fileName, Long dstServerId){
        /*
        SCP from src server to dst server
         */
        return 0;
    }

    public boolean scpFrom(String email, Long srcServerId, String fileName){
        ServerDto srcServer = getServerWithSpecificPerms(email, srcServerId);
        Session session = createSession(srcServer);

        return false;
    }

    public boolean scpTo(String email, Long dstServerId){
        ServerDto dstServer = getServerWithSpecificPerms(email, dstServerId);
        Session session = createSession(dstServer);

        return false;
    }

    public String lsServer(Long serverId){
        ModelMapper m = new ModelMapper();
        ServerDto s = m.map(serverRepository.findById(serverId).get(), ServerDto.class);
        Session session = createSession(s);
        JSch jschSSHChannel = new JSch();

        try{
//            return this.sendCommand("ls", session, jschSSHChannel);
//            return this.sendCommand("ls -lF", session, jschSSHChannel);   //lists files and directories
            return this.sendCommand("ls -l | egrep -v '^d'", session, jschSSHChannel); //lists only files
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Command 'ls' could not be executed! Could not retrieve files!");
        }
    }

    public String sendCommand(String command, Session sesConnection, JSch jschSSHChannel) {
        StringBuilder outputBuffer = new StringBuilder();
        String errorMessage = "";

        try {
            if(!sesConnection.isConnected()){
                throw new JSchException("Connection was broken");
            }
        }
        catch(JSchException jschX)
        {
            errorMessage = jschX.getMessage();
        }

        try
        {
            Channel channel = sesConnection.openChannel("exec");
            ((ChannelExec)channel).setCommand(command);
            InputStream commandOutput = channel.getInputStream();
            channel.connect();
            int readByte = commandOutput.read();

            while(readByte != 0xffffffff)
            {
                outputBuffer.append((char)readByte);
                readByte = commandOutput.read();
            }

        }
        catch(IOException ioX)
        {
            errorMessage = ioX.getMessage();
            return errorMessage;
        }
        catch(JSchException jschX)
        {
            errorMessage = jschX.getMessage();
            return errorMessage;
        }

        return outputBuffer.toString();
    }


    public ServerDto getServerWithSpecificPerms(String email, Long serverId){
        User user = userRepository.findByEmail(email);
        ModelMapper mapper = new ModelMapper();
        ServerDto server = mapper.map(serverRepository.findById(serverId).get(), ServerDto.class);
        Permissions perm = permissionsRepository.findByIds(user.getId(), serverId);
        //check for specific username cred
        if(perm.getUsernameCred()!=null){
            if(!perm.getUsernameCred().equals("")){
                server.setUsernameCred(perm.getUsernameCred());
            }
        }
        //check for specific password cred
        if(perm.getPasswordCred()!=null){
            if(!perm.getPasswordCred().equals("")){
                server.setPasswordCred(perm.getPasswordCred());
            }
        }
        return server;
    }


    public Session createSession(ServerDto server){
        JSch jsch = new JSch();
        jsch.setConfig("StrictHostKeyChecking", "no");
        jsch.setConfig("PreferredAuthentications", "publickey,password,keyboard-interactive");
        Session session = null;
        try {
            if(server.getKeyLocation() != null ) {
                if(!server.getKeyLocation().equals("")) {
                    jsch.addIdentity(System.getProperty("user.dir") + server.getKeyLocation());
                }
            }
            session = jsch.getSession(server.getUsernameCred(), server.getIp(), server.getPort());
            if(server.getKeyLocation() == null) {
                session.setPassword(server.getPasswordCred());
            }
            session.connect();

        } catch (JSchException e) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
        return session;
    }

    public boolean destroySession(Session session) {
        if (session.isConnected()){
            try{
                session.disconnect();
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not destroy session: " + session.toString());
            }
        }
        return true;
    }

    public List<String> makeListOfFiles(String files){
        List<String> list = new LinkedList<>();
        Scanner scanner = new Scanner(files);
        while(scanner!=null && scanner.hasNextLine()){
            list.add(scanner.nextLine());
        }

        return list;
    }


}
