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
import org.springframework.ui.ModelMap;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Type;
import java.util.LinkedList;
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

    public ServerDto addServer(ServerDto serverdto) {
        ServerRepository serverRepository = applicationContext.getBean(ServerRepository.class);
        Server server;
        if(serverdto.getPort() == 0)
            server = new Server(serverdto.getName(), serverdto.getIp(), serverdto.getUsernameCred(), serverdto.getPasswordCred());
        else
            server = new Server(serverdto.getName(), serverdto.getIp(), serverdto.getUsernameCred(), serverdto.getPasswordCred(), serverdto.getPort());

        // QUESTION FOR GIANNIS: Should I ignore id when given on POST Request?
        if(serverRepository.findByIp(server.getIp()) == null){
            //check server's health, if the credentials and IP match
            try{
                checkServerHealth(server);
                server.setHealth(1);
            }catch (Exception e){
                LOGGER.info("Could not establish SSH Tunnel for " + server.getIp() + ".\n");
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
                //since server could not be authenticated
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

            //then check health once the server has update its information
            try{
                checkServerHealth(server);
                server.setHealth(1);
            }catch (Exception e){
                LOGGER.info("Could not establish SSH Tunnel for " + server.getIp() + ".\n");
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
                //since server could not be authenticated
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
            //delete by IP ---> You have to write a new query on server rep to do that... TO DO...

            //FOR NOW retrieve object and return id to the delete
            serverRepository.deleteById(serverRepository.findByIp(ip).getId());
            ModelMapper mapper = new ModelMapper();
            server.setPasswordCred("******");
            return mapper.map(server, ServerDto.class);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    private void checkServerHealth(Server server) throws Exception{
        String host=server.getIp();
        String user=server.getUsernameCred();
        String password=server.getPasswordCred();
        int port=server.getPort();    //default port

        //For port forwarding, might be used later
//        int tunnelLocalPort=9080;
//        String tunnelRemoteHost="YYY.YYY.YYY.YYY";    //forward to --> IP
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

    public ServerDto getServerById(Long serverId) {
        Server server = serverRepository.findById(serverId).get();
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
