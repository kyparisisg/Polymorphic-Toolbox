package com.temple.polymorphic.toolbox.controllers;


//import com.temple.polymorphic.toolbox.dto.ServerDto;
import com.temple.polymorphic.toolbox.dto.ServerDto;
import com.temple.polymorphic.toolbox.services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServersController {

    @Autowired
    private ServerService serverService;

    @GetMapping("/servers")
    public List<ServerDto> getServers() {
        return serverService.getServers();
    }

    @PostMapping("/servers")
    public void addServer(@RequestBody ServerDto serverdto){
        serverService.addServer(serverdto);
    }

}
