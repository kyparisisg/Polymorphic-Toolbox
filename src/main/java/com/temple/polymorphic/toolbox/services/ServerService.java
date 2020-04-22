package com.temple.polymorphic.toolbox.services;

import com.jcraft.jsch.*;
import com.temple.polymorphic.toolbox.PermissionRepository;
import com.temple.polymorphic.toolbox.ServerRepository;
import com.temple.polymorphic.toolbox.TransactionRepository;
import com.temple.polymorphic.toolbox.models.Server;
import com.temple.polymorphic.toolbox.dto.ServerDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class ServerService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerService.class);

    public void setServerRepository(ServerRepository serverRepository) { this.serverRepository = serverRepository; }

    public void setPermissionRepository(PermissionRepository permissionRepository) { this.permissionRepository = permissionRepository; }

    public List<ServerDto> getServers() {
        Type listType = new TypeToken<List<ServerDto>>() {}.getType();
        return new ModelMapper().map(serverRepository.findAll(), listType);
    }

    public ServerDto addServer(ServerDto serverdto){
        ServerRepository serverRepository = applicationContext.getBean(ServerRepository.class);
        Server server;
        if(serverdto.getPort() == 0)
            server = new Server(serverdto.getName(), serverdto.getIp(), serverdto.getUsernameCred(), serverdto.getPasswordCred(), serverdto.getKeyLocation());
        else
            server = new Server(serverdto.getName(), serverdto.getIp(), serverdto.getUsernameCred(), serverdto.getPasswordCred(), serverdto.getKeyLocation(), serverdto.getPort());

        if(serverRepository.findByIp(server.getIp()) == null){
            //check server's health, if the credentials and IP match
            try{
                checkServerHealthWithoutUpdate(server.getId());
                server.setHealth(1);
            }catch (Exception e){
                server.setHealth(0);
                LOGGER.info("Could not establish SSH Tunnel for " + server.getIp() + ".\n");
                //throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
                //allow unauthenticated servers
            }
            serverRepository.save(server);
            server.setPasswordCred("********"); //to hide it from the view
            ModelMapper mapper = new ModelMapper();
            ServerDto ss = mapper.map(server, ServerDto.class);

            return ss;
        }
        //since server found then display conflict http status
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    public ServerDto updateServer(ServerDto serverDto) {
        ServerRepository serverRepository = applicationContext.getBean(ServerRepository.class);
        if(serverDto.getIp().isEmpty() || serverDto.getIp().length() < 7)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "IPv4 Address does not exist!");
        Server server = serverRepository.findByIp(serverDto.getIp());
        if(server != null){
            if(serverDto.getPort()!=0)
                server.setPort(serverDto.getPort());

            if(serverDto.getUsernameCred() != null)
                server.setUsernameCred(serverDto.getUsernameCred());

            if(serverDto.getPasswordCred() != null)
                server.setPasswordCred(serverDto.getPasswordCred());

            if(serverDto.getName() != null)
                server.setName(serverDto.getName());

            if(serverDto.getKeyLocation() != null)
                server.setKeyLocation(serverDto.getKeyLocation());

            //then check health once the server has update its information
            try{
                checkServerHealthWithoutUpdate(server.getId());
                server.setHealth(1);
            }catch (Exception e){
                server.setHealth(0);
                LOGGER.info("Could not establish SSH Tunnel for " + server.getIp() + ".\n");
                //throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
                //allow unauthenticated servers
            }
            serverRepository.save(server);
            ModelMapper mapper = new ModelMapper();
            server.setPasswordCred("********"); //to hide it from the view
            ServerDto ss = mapper.map(server, ServerDto.class);

            return ss;
        }

        //server not found
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public ServerDto deleteServerByIp(String ip) {
        Server server = serverRepository.findByIp(ip);
        if(server != null){
            if(permissionRepository.findServerById(server.getId()) != null ){ //find foreign keys in permissions
                permissionRepository.deleteByServer(server);
            }
            if(transactionRepository.findSrcServerById(server.getId()) != null ){ //find foreign keys in transaction src_server
                transactionRepository.deleteByServer(server);
            }
            if(transactionRepository.findDstServerById(server.getId()) != null ){ //find foreign keys in transaction dst_server
                transactionRepository.deleteByServer(server);
            }

            //FOR NOW retrieve object and return id to the delete
            serverRepository.delete(server);
            ModelMapper mapper = new ModelMapper();
            server.setPasswordCred("******");
            return mapper.map(server, ServerDto.class);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void checkServerHealthWithoutUpdate(Long serverId) throws Exception{
        Server server = serverRepository.findById(serverId).get();
        //For port forwarding, might be used later
        //int tunnelLocalPort=9080;
        //String tunnelRemoteHost="YYY.YYY.YYY.YYY";    //forward to --> IP
        //int tunnelRemotePort=80;

        JSch jsch = new JSch();
        jsch.setConfig("StrictHostKeyChecking", "no");
        jsch.setConfig("PreferredAuthentications", "publickey,password,keyboard-interactive");
        Session session = null;
        try {
            if(server.getKeyLocation() != null && !server.getKeyLocation().equals("")) {
                jsch.addIdentity(System.getProperty("user.dir") + server.getKeyLocation());
            }
            session = jsch.getSession(server.getUsernameCred(), server.getIp(), server.getPort());
            if(server.getKeyLocation() == null || server.getKeyLocation().equals("")) {
                session.setPassword(server.getPasswordCred());
            }
            //session.setPortForwardingL(tunnelLocalPort,tunnelRemoteHost,tunnelRemotePort);
            session.connect();
            session.disconnect();
        } catch (JSchException e) {
            LOGGER.info(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }

    public void checkServerHealthWithUpdate(Long serverId) throws Exception{
        Server server = serverRepository.findById(serverId).get();
        JSch jsch = new JSch();
        jsch.setConfig("StrictHostKeyChecking", "no");
        jsch.setConfig("PreferredAuthentications", "publickey,password,keyboard-interactive");
        Session session = null;
        try {
            if(server.getKeyLocation() != null && !server.getKeyLocation().equals("")) {
                jsch.addIdentity(System.getProperty("user.dir") + server.getKeyLocation());
            }
            session = jsch.getSession(server.getUsernameCred(), server.getIp(), server.getPort());
            if(server.getKeyLocation() == null || server.getKeyLocation().equals("")) {
                session.setPassword(server.getPasswordCred());
            }
            session.connect();
            session.disconnect();
            serverRepository.updateServerHealth(1, serverId);
        } catch (JSchException e) {
            serverRepository.updateServerHealth(0, serverId);
            LOGGER.info(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
        }
    }

//    public boolean checkServerHealth(String ip, boolean pkey) throws Exception{
//        Server server = this.getServerByIp(ip);
//        JSch jsch = new JSch();
//        jsch.setConfig("StrictHostKeyChecking", "no");
//        jsch.setConfig("PreferredAuthentications", "publickey,password,keyboard-interactive");
//        Session session = null;
//        try {
//            if(server.getKeyLocation() != null && pkey) {
//                jsch.addIdentity(System.getProperty("user.dir") + server.getKeyLocation());
//            }
//            session = jsch.getSession(server.getUsernameCred(), server.getIp(), server.getPort());
//            if(server.getKeyLocation() != null ) { //this change to != breaks my ec2 connection
//                session.setPassword(server.getPasswordCred());
//            }
//            session.connect();
//            session.disconnect();
//            return true;
//        } catch (JSchException e) {
//            LOGGER.info(String.valueOf(e));
//            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
//        }
//    }

    public ServerDto getServerById(Long serverId) {
        Optional<Server> checkServer = serverRepository.findById(serverId);
        if(!checkServer.isPresent()){
            return null;
        }
        Server server = checkServer.get();
        ModelMapper mm = new ModelMapper();
        ServerDto serverDto = mm.map(server, ServerDto.class);
        if (serverDto == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return serverDto;
    }

    public List<ServerDto> getServerByIpList(String ip) {
        ModelMapper mapper = new ModelMapper();
        ServerDto ss = mapper.map(serverRepository.findByIp(ip), ServerDto.class);
        LinkedList<ServerDto> list = new LinkedList<ServerDto>();
        list.add(ss);

        return (List<ServerDto>)list;
    }

    public String getServerNameFromId(Long serverId){
        Server server = serverRepository.findById(serverId).get();
        return server.getName();
    }

    private Server getServerByIp(String ip){
        //do the function to throw exception if not found
        ip = ip.replace(" ", ""); //replace spaces
        return serverRepository.findByIp(ip);
    }

    public ServerDto getServerDtoById(Long id){
        //do the function to throw exception if not found
        ModelMapper mapper = new ModelMapper();
        ServerDto s = mapper.map(serverRepository.findById(id).get(), ServerDto.class);
        return s;
    }

//    public boolean updateHealth(String ip, boolean h){
//        Long serverId = this.getServerByIp(ip).getId();
//        if(h){//means true then set 1
//            serverRepository.updateServerHealth(1, serverId);
//            return true;
//        }
//        serverRepository.updateServerHealth(0, serverId);
//        return false;
//    }

    public boolean isHealthy(Long serverId){
        Server server;
        if(serverRepository.findById(serverId)!=null){
            server = serverRepository.findById(serverId).get();
            if(server.getHealth()==1){ //server is healthy
                return true;
            } else { //server is unhealthy
                return false;
            }
        } else{ //server cant be found
            return false;
        }
    }
}
