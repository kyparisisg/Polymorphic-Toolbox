package com.temple.polymorphic.toolbox.controllers;


//import com.temple.polymorphic.toolbox.dto.ServerDto;
import com.temple.polymorphic.toolbox.dto.ServerDto;
import com.temple.polymorphic.toolbox.models.Server;
import com.temple.polymorphic.toolbox.services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servers")
public class ServersController {

    @Autowired
    private ServerService serverService;

    @GetMapping()
    public List<ServerDto> getServers() {
        return serverService.getServers();
    }

    @GetMapping("{serverId}")
    public ServerDto getServerById(@PathVariable Long serverId){
        return serverService.getServerById(serverId);
    }

    @PostMapping()
    public void addServer(@RequestBody ServerDto serverdto){
        serverService.addServer(serverdto);
    }

    @PutMapping()
    public void updateServer(@RequestBody ServerDto serverDto){
        serverService.updateServer(serverDto);
    }

    @DeleteMapping("{ip}")
    public void deleteServer(@PathVariable String ip){
        serverService.deleteServerByIp(ip);
    }

}
