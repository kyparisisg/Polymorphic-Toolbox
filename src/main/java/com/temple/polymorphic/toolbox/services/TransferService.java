package com.temple.polymorphic.toolbox.services;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.jcraft.jsch.*;
import com.temple.polymorphic.toolbox.*;
import com.temple.polymorphic.toolbox.dto.TransactionDto;
import com.temple.polymorphic.toolbox.dto.ServerDto;
import com.temple.polymorphic.toolbox.models.*;
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

    @Autowired
    private BucketCredRepository bucketCredRepository;

    private final Long defaultBucket = Long.valueOf(1);

    private static BucketCred bucketCred = new BucketCred("","","");

    public void setUserRepository(UserRepository userRepository) { this.userRepository = userRepository; }

    public void setPermissionsRepository(PermissionRepository permissionRepository) { this.permissionsRepository = permissionRepository; }

    public void setServerRepository(ServerRepository serverRepository) { this.serverRepository = serverRepository; }

    public void setTransactionRepository(TransactionRepository transactionRepository) { this.transactionRepository = transactionRepository; }

    public void setBucketCredRepository(BucketCredRepository bucketCredRepository) { this.bucketCredRepository = bucketCredRepository; }


    /*----------------------------------------------------------------------------------------------------------------
    S3 OPERATIONS
     ----------------------------------------------------------------------------------------------------------------*/

    public BucketCred setBucketWithCred(){
        return new BucketCred(bucketCredRepository.findById(defaultBucket).get().getBucketName()
                ,bucketCredRepository.findById(defaultBucket).get().getPrivateKey()
                ,bucketCredRepository.findById(defaultBucket).get().getPublicKey());
    }

    // for creating new buckets
    //public static void createS3b(String bcknm){
    public static void createS3b(String bcknm){
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(Credentials.access_key_id, Credentials.secret_access_key);
        //BasicAWSCredentials awsCreds = new BasicAWSCredentials(bucketCredRepository.findById(defaultBucket).get().getPublicKey(), bucketCredRepository.findById(defaultBucket).get().getPrivateKey());

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-2").withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
        //AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-2").withCredentials(new AWSCredentialsProviderChain((AWSCredentialsProvider)awsCreds)).build();


        String newBucketName = ""+bcknm;

        if(s3Client.doesBucketExistV2(newBucketName)){

        }else{
            s3Client.createBucket(newBucketName);
        }

    }

    //Use This to Delete 'Servers' aka aws buckets from lists for admins
    public static void deleteRequest(String bcknm) throws IOException {
        AmazonS3 s3Client = setUpclient();
        BucketTools.deleteBucket(bcknm,s3Client);
    }

    public boolean doesObjectExist(String fileName, String bucketName, String S3Dir){
        AmazonS3  s3client =  setUpclient();


        try{
            boolean exists =  s3client.doesObjectExist(bucketName, S3Dir+"/"+fileName);
            if(exists){
                System.out.println("files: "+fileName+"exist");
                return true;

            }else{
                System.out.println("file not found on s3");
                return false;
            }

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

        return false;
    }

    public static void fileUpload(String bcktnm, String dir, String fileName) throws IOException{

        AmazonS3 s3Client = setUpclient();
        String localPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "tempFileStorage" + File.separator + fileName;

            if(s3Client.doesBucketExistV2(bcktnm)){
            try {
                s3Client.putObject(
                        bcktnm,
                        dir + "/" + fileName,
                        new File(localPath)
                );
            }
//            catch(AmazonServiceException ase){
//                System.out.println("Caught an AmazonServiceException, which " +
//                        "means your request made it " +
//                        "to Amazon S3, but was rejected with an error response" +
//                        " for some reason.");
//                System.out.println("Error Message:    " + ase.getMessage());
//                System.out.println("HTTP Status Code: " + ase.getStatusCode());
//                System.out.println("AWS Error Code:   " + ase.getErrorCode());
//                System.out.println("Error Type:       " + ase.getErrorType());
//                System.out.println("Request ID:       " + ase.getRequestId());
//            }
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

        String localPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "tempFileStorage" + File.separator + fileName;

        java.nio.file.Files.copy(objectdata, Paths.get(localPath),StandardCopyOption.REPLACE_EXISTING);

    }

    public void fileDelete(String bucketName, String fileName, String s3Dir)throws IOException{
        AmazonS3 s3Client = setUpclient();

        try{
            s3Client.deleteObject(bucketName,s3Dir+"/"+fileName);
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
    }

    //Method that takes the credentials for S3 access and returns amazon s3 client object
    public static AmazonS3 setUpclient() {
//        BasicAWSCredentials awsCreds = new BasicAWSCredentials(bucketCredRepository.findById(defaultBucket).get().getPublicKey()
//                , bucketCredRepository.findById(defaultBucket).get().getPrivateKey());

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(Credentials.access_key_id, Credentials.secret_access_key);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-2").withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
        //AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-2").withCredentials(new AWSCredentialsProviderChain((AWSCredentialsProvider)awsCreds)).build();

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
        if(userRepository.findByEmail(email).getRole().equals("ROLE_ADMIN")){
            return true;
        }
        Permissions perm = permissionsRepository.findByIds(userRepository.findByEmail(email).getId(), serverId);
        return perm != null;
    }

    public List<ServerDto> getServersForUser(String email){
        List<Server> servers;
        ArrayList<ServerDto> serverList = new ArrayList<>();
        if(userRepository.findByEmail(email) != null){
            if(userRepository.findByEmail(email).getRole().equals("ROLE_ADMIN")){
                servers = serverRepository.findAll();
            }
            else{
                servers = permissionsRepository.findAllServersByEmail(email);
            }
            for(Server server: servers){
                serverList.add(new ServerDto(server.getId(), server.getName(), server.getIp(), server.getPort(),
                        server.getUsernameCred(), server.getPasswordCred(), server.getHealth(), server.getRegisterDate(), server.getKeyLocation()));
            }
        }
        return serverList;
    }

    public void addTransaction(String email, Long srcServerId, String fileName, Long dstServerId, int status){
        User user = userRepository.findByEmail(email);
        Server srcServer = serverRepository.findById(srcServerId).get();
        Server dstServer = serverRepository.findById(dstServerId).get();
        Transactions transaction = new Transactions(user, srcServer, dstServer, fileName, status);
        transactionRepository.save(transaction);
    }

    public int scp(String email, Long srcServerId, String fileName, Long dstServerId){
        if(scpFrom(email, srcServerId, fileName)){
            if(scpTo(email, dstServerId, fileName)){
                deleteTempFile(fileName);
                return 1;
            }
        }
        return 0;
    }

    public boolean scpFrom(String email, Long srcServerId, String fileName){
        ServerDto srcServer = getServerWithSpecificPerms(email, srcServerId);
        Session session = createSession(srcServer);
        String remoteFile = "";
        String os = determineOS(session);
        if(os.equals("ubuntu")){
            remoteFile = "/home/" + srcServer.getUsernameCred() + "/" + fileName;
        }
        else if(os.equals("mac")){
            remoteFile = "/Users/" + srcServer.getUsernameCred() + "/" + fileName;
        } else {
            return false;
        }
        String localDir = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "tempFileStorage" + File.separator;
        String prefix = null;
        if (new File(localDir).isDirectory()) {
            prefix = localDir + File.separator;
        }

        try{
            // exec 'scp -f rfile' remotely
            String command = "scp -f " + remoteFile;
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);

            OutputStream out = channel.getOutputStream();
            InputStream in = channel.getInputStream();
            channel.connect();

            byte[] buf = new byte[1024];

            // send '\0'
            buf[0] = 0;
            out.write(buf, 0, 1);
            out.flush();

            while (true){
                int c = checkAck(in);
                if (c != 'C') {
                    break;
                }
                // read '0644'
                in.read(buf, 0, 5);

                long filesize = 0L;
                while (true) {
                    if (in.read(buf, 0, 1) < 0) {
                        // error
                        break;
                    }
                    if (buf[0] == ' ') break;
                    filesize = filesize * 10L + (long) (buf[0] - '0');
                }

                String file = null;
                for (int i = 0; ; i++) {
                    in.read(buf, i, 1);
                    if (buf[i] == (byte) 0x0a) {
                        file = new String(buf, 0, i);
                        break;
                    }
                }

                // send '\0'
                buf[0] = 0;
                out.write(buf, 0, 1);
                out.flush();

                // read a content of lfile
                FileOutputStream fos = new FileOutputStream(prefix == null ? localDir : prefix + file);
                int foo;
                while (true) {
                    if (buf.length < filesize) foo = buf.length;
                    else foo = (int) filesize;
                    foo = in.read(buf, 0, foo);
                    if (foo < 0) {
                        // error
                        break;
                    }
                    fos.write(buf, 0, foo);
                    filesize -= foo;
                    if (filesize == 0L) break;
                }

                if (checkAck(in) != 0) {
                    channel.disconnect();
                    destroySession(session);
                    return false;
                }

                // send '\0'
                buf[0] = 0;
                out.write(buf, 0, 1);
                out.flush();

                try {
                    if (fos != null) fos.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            channel.disconnect();
            destroySession(session);
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            destroySession(session);
            return false;
        }
    }

    public boolean scpTo(String email, Long dstServerId, String fileName){
        ServerDto dstServer = getServerWithSpecificPerms(email, dstServerId);
        Session session = createSession(dstServer);
        boolean ptimestamp = true;
        String remoteDir = "";
        String os = determineOS(session);
        if(os.equals("ubuntu")){
            remoteDir = "/home/" + dstServer.getUsernameCred() + "/";
        }
        else if(os.equals("mac")){
            remoteDir = "/Users/" + dstServer.getUsernameCred() + "/";
        } else {
            return false;
        }
        String localFile = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "tempFileStorage" + File.separator + fileName;
        try{
            // exec 'scp -t rfile' remotely
            String command = "scp " + (ptimestamp ? "-p" : "") + " -t " + remoteDir;
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);

            // get I/O streams for remote scp
            OutputStream out = channel.getOutputStream();
            InputStream in = channel.getInputStream();

            channel.connect();

            if (checkAck(in) != 0) {
                channel.disconnect();
                destroySession(session);
                return false;
            }

            File _lfile = new File(localFile);

            if (ptimestamp) {
                command = "T" + (_lfile.lastModified() / 1000) + " 0";
                // The access time should be sent here,
                // but it is not accessible with JavaAPI ;-<
                command += (" " + (_lfile.lastModified() / 1000) + " 0\n");
                out.write(command.getBytes());
                out.flush();
                if (checkAck(in) != 0) {
                    channel.disconnect();
                    destroySession(session);
                    return false;
                }
            }

            // send "C0644 filesize filename", where filename should not include file separator
            long filesize = _lfile.length();
            command = "C0644 " + filesize + " ";
            if (localFile.lastIndexOf(File.separator) > 0) {
                command += localFile.substring(localFile.lastIndexOf(File.separator) + 1);
            } else {
                command += localFile;
            }

            command += "\n";
            out.write(command.getBytes());
            out.flush();

            if (checkAck(in) != 0) {
                channel.disconnect();
                destroySession(session);
                return false;
            }

            // send a content of lfile
            FileInputStream fis = new FileInputStream(localFile);
            byte[] buf = new byte[1024];
            while (true) {
                int len = fis.read(buf, 0, buf.length);
                if (len <= 0) break;
                out.write(buf, 0, len); //out.flush();
            }

            // send '\0'
            buf[0] = 0;
            out.write(buf, 0, 1);
            out.flush();

            if (checkAck(in) != 0) {
                channel.disconnect();
                destroySession(session);
                return false;
            }
            out.close();

            try {
                if (fis != null) fis.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
            channel.disconnect();
            destroySession(session);
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            destroySession(session);
            return false;
        }
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
            channel.disconnect();
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
        if(perm == null){ //user is admin without explicit permission
            return server;
        }
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
                    String keyLocation = server.getKeyLocation();
                    keyLocation = keyLocation.replace('/', '&');
                    keyLocation = keyLocation.replace('\\', '&');
                    keyLocation = keyLocation.replace('&', File.separatorChar);
                    jsch.addIdentity(System.getProperty("user.dir") + keyLocation);
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
        if(scanner == null){
            return list;
        }
        scanner.nextLine(); //kill first line
        while(scanner.hasNextLine()){
            String nextLine = scanner.nextLine();
            String fileName = nextLine.substring(nextLine.lastIndexOf(" ") + 1);
            list.add(fileName);
        }
        return list;
    }

    public int checkAck(InputStream in) throws IOException {
        int b = in.read();
        // b may be 0 for success,
        //          1 for error,
        //          2 for fatal error,
        //         -1
        if (b == 0) return b;
        if (b == -1) return b;

        if (b == 1 || b == 2) {
            StringBuffer sb = new StringBuffer();
            int c;
            do {
                c = in.read();
                sb.append((char) c);
            }
            while (c != '\n');
            if (b == 1) { // error
                System.out.print(sb.toString());
            }
            if (b == 2) { // fatal error
                System.out.print(sb.toString());
            }
        }
        return b;
    }

    public boolean deleteTempFile(String fileName){
        String localPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "tempFileStorage" + File.separator + fileName;
        File localFile = new File(localPath);
        if(localFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    public String determineOS(Session session){
        JSch jschSSHChannel = new JSch();
        try{
            String homeDir =  this.sendCommand("pwd", session, jschSSHChannel);
            if(homeDir == null){
                return "unknown";
            }
            if(homeDir.contains("home")){
                return "ubuntu";
            }
            if(homeDir.contains("Users")){
                return "mac";
            }
            return "unknown";
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Command 'ls' could not be executed! Could determine os!");
        }
    }
}
