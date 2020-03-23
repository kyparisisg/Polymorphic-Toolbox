package com.temple.polymorphic.toolbox.controllers;


import com.temple.polymorphic.toolbox.dto.ServerDto;
import com.temple.polymorphic.toolbox.dto.UserDto;
import com.temple.polymorphic.toolbox.services.ServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/api/servers")
public class ServersController {

    @Autowired
    private ServerService serverService;


    private static final Logger LOGGER = LoggerFactory.getLogger(ServerService.class);

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ModelAndView index(Model model) {
//
//        return new ModelAndView("manageServer");
//    }
//
//
//    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    public String getUserFrom(Model model) {
//        LinkedList<ServerDto> list = getAllServers();
//        model.addAttribute("list", list);
//
//        return "allServers";
//    }
//
//    private LinkedList<ServerDto> getAllServers(){
//
//        return serverService.getServers();
//    }

//
//    @RequestMapping(value = "/get", method = RequestMethod.GET)
//    public ModelAndView getServerForm() {
//
//        return new ModelAndView("searchServer", "command", new ServerDto()); //maybe new UserDto like user()
//    }
//
//    @RequestMapping(value = "/get", method = RequestMethod.POST)
//    public ModelAndView getUser(@ModelAttribute ServerDto serverDto, Model model)  {
//        ServerDto ss = serverService.getServerByIp(serverDto.getIp());
//
//        model.addAttribute("id", ss.getId());
//        model.addAttribute("ip", ss.getIp());
//        model.addAttribute("username",ss.getUsernameCred());
//        model.addAttribute("name",ss.getName());
//        model.addAttribute("port",ss.getPort());
//        model.addAttribute("health",ss.getHealth());
//
//        return new ModelAndView("getServer");
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
