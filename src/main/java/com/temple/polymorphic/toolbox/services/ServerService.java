package com.temple.polymorphic.toolbox.services;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import com.temple.polymorphic.toolbox.ServerRepository;
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
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Type;
import java.util.List;


@Service
public class ServerService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ServerRepository serverRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerService.class);

    public void setServerRepository(ServerRepository serverRepository) { this.serverRepository = serverRepository; }

    public List<ServerDto> getServers() {
        Type listType = new TypeToken<List<ServerDto>>() {}.getType();
        return new ModelMapper().map(serverRepository.findAll(), listType);
    }

    public void addServer(ServerDto serverdto) {
        ServerRepository serverRepository = applicationContext.getBean(ServerRepository.class);
        Server server;
        if(serverdto.getPort() == 0)
            server = new Server(serverdto.getName(), serverdto.getIp(), serverdto.getUsernameCred(), serverdto.getPasswordCred());
        else
            server = new Server(serverdto.getName(), serverdto.getIp(), serverdto.getUsernameCred(), serverdto.getPasswordCred(), serverdto.getPort());

        if(serverRepository.findByName(server.getName()) == null){
            //check server's health is the credentials and IP match
            try{
                checkServerHealth(server);
                server.setHealth(1);
            }catch (Exception e){
                LOGGER.info("Could not establish SSH Tunnel for " + server.getIp() + ".\n");
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
                //since server could not be authenticated
            }
            serverRepository.save(server);
            return;
        }
        //since server found then display conflict error
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    public void checkServerHealth(Server server) throws Exception{
        String host=server.getIp();
        String user=server.getUsernameCred();
        String password=server.getPasswordCred();
        int port=server.getPort();    //default port

//        int tunnelLocalPort=9080;
//        String tunnelRemoteHost="YYY.YYY.YYY.YYY";
//        int tunnelRemotePort=80;

        JSch jsch=new JSch();
        Session session = jsch.getSession(user,host,port);
        session.setPassword(password);
        localUserInfo lui=new localUserInfo();
        session.setUserInfo(lui);
        session.connect();
        session.disconnect();
        //session.setPortForwardingL(tunnelLocalPort,tunnelRemoteHost,tunnelRemotePort);
        //System.out.println("Connected");
        //set server.health equal to 1 since no exception were thrown
    }
    class localUserInfo implements UserInfo {
        String passwd;
        @Override
        public String getPassword(){
            return passwd;
        }
        @Override
        public boolean promptYesNo(String str){
            return true;
        }
        @Override
        public String getPassphrase(){
            return null;
        }
        @Override
        public boolean promptPassphrase(String message){
            return true;
        }
        @Override
        public boolean promptPassword(String message){
            return true;
        }
        @Override
        public void showMessage(String message){

        }
    }
}
