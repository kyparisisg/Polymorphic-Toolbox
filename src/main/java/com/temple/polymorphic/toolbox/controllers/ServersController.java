package com.temple.polymorphic.toolbox.controllers;


import com.temple.polymorphic.toolbox.dto.ServerDto;
import com.temple.polymorphic.toolbox.services.ServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/api/servers")
public class ServersController {

    @Autowired
    private ServerService serverService;


    private static final Logger LOGGER = LoggerFactory.getLogger(ServerService.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index(Model model) {

        return new ModelAndView("servers/manageServer");
    }


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<ServerDto> list = getAllServers();
        model.addAttribute("list", list);

        return "servers/get";
    }

    private List<ServerDto> getAllServers(){

        return serverService.getServers();
    }


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ModelAndView searchForm() {

        return new ModelAndView("servers/search", "command", new ServerDto()); //maybe new UserDto like user()
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public String getServer(@ModelAttribute ServerDto serverDto, Model model)  {
        List list = serverService.getServerByIpList(serverDto.getIp());
        model.addAttribute("list", list);

        return "servers/get";

    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public ModelAndView saveForm(Model model){

        return new ModelAndView("servers/save", "command", new ServerDto()); //maybe new UserDto like user()
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveServer(@ModelAttribute ServerDto serverDto, Model model){
        ServerDto serverDto1 = serverService.addServer(serverDto);
        model.addAttribute("server", serverDto1);
        //OR
        model.addAttribute("id", serverDto1.getId());
        model.addAttribute("name", serverDto1.getName());
        model.addAttribute("ip", serverDto1.getIp());
        model.addAttribute("port", serverDto1.getPort());
        model.addAttribute("usernameCred", serverDto1.getUsernameCred());
        model.addAttribute("keyLocation", serverDto1.getKeyLocation());
        model.addAttribute("request", "Add Server");

        return "servers/requestSuccess";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView updateForm(Model model){

        return new ModelAndView("servers/update", "command", new ServerDto());
    }

    @RequestMapping(value = "/update/{ip}", method = RequestMethod.GET)
    public ModelAndView updateFrom(@PathVariable("ip") String ip, Model model) {
        model.addAttribute("ip",ip);

        return new ModelAndView("servers/update", "command", new ServerDto());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateServer(@ModelAttribute ServerDto serverDto, Model model){
        ServerDto serverDto1 = serverService.updateServer(serverDto);
        model.addAttribute("server", serverDto1);
        //OR
        model.addAttribute("id", serverDto1.getId());
        model.addAttribute("name", serverDto1.getName());
        model.addAttribute("ip", serverDto1.getIp());
        model.addAttribute("port", serverDto1.getPort());
        model.addAttribute("usernameCred", serverDto1.getUsernameCred());
        model.addAttribute("keyLocation", serverDto1.getKeyLocation());
        model.addAttribute("request", "Update Server");

        return "servers/requestSuccess";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteForm(Model model) {

        return new ModelAndView("servers/delete", "command", new ServerDto());
    }

    @RequestMapping(value = "/delete/{ip}", method = RequestMethod.GET)
    public String deleteForm(@PathVariable("ip") String ip, Model model) {
        ServerDto serverDto1 = serverService.deleteServerByIp(ip);
        serverDto1.setPasswordCred("");
        model.addAttribute("server", serverDto1);
        //OR
        model.addAttribute("id", serverDto1.getId());
        model.addAttribute("name", serverDto1.getName());
        model.addAttribute("ip", serverDto1.getIp());
        model.addAttribute("port", serverDto1.getPort());
        model.addAttribute("usernameCred", serverDto1.getUsernameCred());
        model.addAttribute("request", "Delete Server");

        return "servers/requestSuccess";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteServer(@ModelAttribute ServerDto serverDto, Model model){
        ServerDto serverDto1 = serverService.deleteServerByIp(serverDto.getIp());
//        model.addAttribute("server", serverDto1);
        //OR
        model.addAttribute("id", serverDto1.getId());
        model.addAttribute("name", serverDto1.getName());
        model.addAttribute("ip", serverDto1.getIp());
        model.addAttribute("port", serverDto1.getPort());
        model.addAttribute("usernameCred", serverDto1.getUsernameCred());
        model.addAttribute("request", "Delete Server");

        return "servers/requestSuccess";
    }

    @RequestMapping(value = "/check/{id}", method = RequestMethod.GET)
    public String checkServerHealth(@PathVariable("id") Long id, Model model){
        //boolean health;
        try{
            //boolean pkey = false;
            //serverService.checkServerHealth(ip, pkey);
            serverService.checkServerHealthWithUpdate(id);
            //health = serverService.updateHealth(ip, true);
        }catch (Exception e){
            //health = serverService.updateHealth(ip, false);
        }
        //update health in db since I reached this point... means ssh was successful
        int status = 0;
        if(serverService.isHealthy(id))  status = 1;
        ServerDto serverDto1 = serverService.getServerDtoById(id);
        model.addAttribute("id", serverDto1.getId());
        model.addAttribute("name", serverDto1.getName());
        model.addAttribute("ip", serverDto1.getIp());
        model.addAttribute("port", serverDto1.getPort());
        model.addAttribute("usernameCred", serverDto1.getUsernameCred());
        model.addAttribute("request", "Health Check Set to: " + status);

        return "servers/requestSuccess";
    }
}
