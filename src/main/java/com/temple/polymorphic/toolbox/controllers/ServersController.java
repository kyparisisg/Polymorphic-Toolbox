package com.temple.polymorphic.toolbox.controllers;


import com.amazonaws.services.s3.model.Bucket;
import com.temple.polymorphic.toolbox.dto.ServerDto;
import com.temple.polymorphic.toolbox.models.BucketCred;
import com.temple.polymorphic.toolbox.services.BucketTools;
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

    @Autowired
    private BucketTools bucketTools;

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
        try{
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
        }catch (Exception e){
            String msg = "Could not add new server, please try again!";
            model.addAttribute("msg", msg);
            return "500";
        }
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
        try {
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
        }catch (Exception e){
            String msg = "Could not update existing server, please make sure you completed the form correctly and try again!";
            model.addAttribute("msg", msg);
            return "500";
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteForm(Model model) {

        return new ModelAndView("servers/delete", "command", new ServerDto());
    }

    @RequestMapping(value = "/delete/{ip}", method = RequestMethod.GET)
    public String deleteForm(@PathVariable("ip") String ip, Model model) {

        try{
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
        }catch (Exception e){
            String msg = "Could not delete server, please try again!";
            model.addAttribute("msg", msg);
            return "500";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteServer(@ModelAttribute ServerDto serverDto, Model model){
        try{
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
        }catch (Exception e){
            String msg = "Could not delete server, please try again!";
            model.addAttribute("msg", msg);
            return "500";
        }
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

    @RequestMapping(value = "/s3bucket", method = RequestMethod.GET)
    public ModelAndView getBucketForm(){

        return new ModelAndView("servers/bucket", "command",new BucketCred());
    }

    @RequestMapping(value = "/s3bucket", method = RequestMethod.POST)
    public String setBucket(@ModelAttribute BucketCred bucketCred,Model model) {
        try{
            bucketTools.setBucketCred(bucketCred);
        }catch (Exception e){
            //error handler
            String msg = "Could not set S3 Bucket Credentials, please make sure the information is correct and try again!";
            model.addAttribute("msg", msg);
            return "500";
        }

        //Crafting custom entry for S3 Bucket
        ServerDto serverDto = new ServerDto("Amazon S3 Bucket", "aws.amazon.com",
                0, bucketCred.getBucketName(), "Undefined/Encrypted", 1);

        ServerDto serverDto1 = null;
        Long id = Long.valueOf(0);
        try{
            serverDto1 = serverService.addServer(serverDto);
            id = serverDto1.getId();
        }catch (Exception e){
            //error handler
            String msg = "Could not set S3 Bucket Credentials, please make sure the information is correct and try again!";
            model.addAttribute("msg", msg);
            return "500";
        }
        //OR
        model.addAttribute("id", id);
        model.addAttribute("name", "Amazon S3 Bucket");
        model.addAttribute("ip", "aws.amazon.com");
        model.addAttribute("port", "Undefined");
        model.addAttribute("usernameCred", bucketCred.getBucketName());
        model.addAttribute("keyLocation", "Hidden/Encrypted");
        model.addAttribute("request", "Add S3 Bucket Server");

        return "servers/requestSuccess";
    }
}
