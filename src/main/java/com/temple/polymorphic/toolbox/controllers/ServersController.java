package com.temple.polymorphic.toolbox.controllers;


import com.temple.polymorphic.toolbox.dto.ServerDto;
import com.temple.polymorphic.toolbox.services.ServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/servers")
public class ServersController {

    @Autowired
    private ServerService serverService;


    private static final Logger LOGGER = LoggerFactory.getLogger(ServerService.class);

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ModelAndView index(Model model) {
//
//        return new ModelAndView("AdminDashboard");
//    }

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
