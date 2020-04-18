package com.temple.polymorphic.toolbox.controllers;

import com.temple.polymorphic.toolbox.dto.ServerDto;
import com.temple.polymorphic.toolbox.dto.TransactionDto;
import com.temple.polymorphic.toolbox.dto.TransferOperation;
import com.temple.polymorphic.toolbox.services.ServerService;
import com.temple.polymorphic.toolbox.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/client/transfer/")
public class TransferController {

    @Autowired
    private ServerService serverService;

    @Autowired
    private TransferService transferService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TransferService.class);

    /*@RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView uploadFile(){

        return new ModelAndView("client/transfer/scp","command", new TransactionDto());
    }*/

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public ModelAndView serverList(@CookieValue(value = "username", defaultValue = "NOT_FOUND") String email, Model model) {
        //get server list for user
        model.addAttribute("serverList", getServers(email));
        //cascade transfer info
        model.addAttribute("email", email);

        return new ModelAndView("client/transfer/src","command", new TransferOperation());
    }

    public List<ServerDto> getServers(String email){
        return transferService.getServersForUser(email);
    }

    @RequestMapping(value = "src", method = RequestMethod.POST)
    public ModelAndView srcServer(@ModelAttribute TransferOperation tran, Model model) {
        //verify src server
        try{
//            if(!tran.getSrcServerId().getClass().getName().equals("java.lang.Long")){
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
//            }
            if(serverService.getServerById(tran.getSrcServerId())==null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            if(!transferService.hasPermission(tran.getEmail(), tran.getSrcServerId())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        } catch (ResponseStatusException e) {
            return new ModelAndView("403","command", new TransferOperation());
        }
        //get directory for server
        model.addAttribute("directory", transferService.getDirectory(tran.getSrcServerId()));
        //cascade transfer info
        model.addAttribute("email", tran.getEmail());
        model.addAttribute("srcServerId", tran.getSrcServerId());

        return new ModelAndView("client/transfer/file","command", new TransferOperation());
    }

    @RequestMapping(value="file", method = RequestMethod.POST)
    public ModelAndView srcFile(@ModelAttribute TransferOperation tran, Model model) {
        //verify filePath?
        /*
        List<String> directory = transferService.getDirectory(tran.getSrcServerId());
        for(String path: directory){
            comparison
        }
        */
        //get server list for user
        model.addAttribute("serverList", getServers(tran.getEmail()));
        //cascade transfer info
        model.addAttribute("email", tran.getEmail());
        model.addAttribute("srcServerId", tran.getSrcServerId());
        model.addAttribute("filePath", tran.getFilePath());

        return new ModelAndView("client/transfer/dst","command", new TransferOperation());
    }

    @RequestMapping(value="dst", method = RequestMethod.POST)
    public ModelAndView dstServer(@ModelAttribute TransferOperation tran, Model model) {
        //verify dst server
        try{
            if(serverService.getServerById(tran.getDstServerId())==null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            if(!transferService.hasPermission(tran.getEmail(), tran.getDstServerId())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        } catch (ResponseStatusException e) {
            return new ModelAndView("403","command", new TransferOperation());
        }
        //get directory for server
        model.addAttribute("directory", transferService.getDirectory(tran.getDstServerId()));
        //cascade transfer info
        model.addAttribute("email", tran.getEmail());
        model.addAttribute("srcServerId", tran.getSrcServerId());
        model.addAttribute("filePath", tran.getFilePath());
        model.addAttribute("dstServerId", tran.getDstServerId());

        return new ModelAndView("client/transfer/target","command", new TransferOperation());
    }

    @RequestMapping(value="scp", method = RequestMethod.POST)
    public String scp(@ModelAttribute TransferOperation tran, Model model) {
        //verify targetPath?
        /*
        List<String> directory = transferService.getDirectory(tran.getDstServerId());
        for(String path: directory){
            comparison
        }
        */
        int status = transferService.scp(tran.getEmail(), tran.getSrcServerId(), tran.getFilePath(), tran.getDstServerId(), tran.getTargetPath());
        transferService.addTransaction(tran.getEmail(), tran.getSrcServerId(), tran.getFilePath(), tran.getDstServerId(), status);

        String srcServerName  = serverService.getServerNameFromId(tran.getSrcServerId());
        String dstServerName  = serverService.getServerNameFromId(tran.getDstServerId());

        model.addAttribute("email", tran.getEmail());
        model.addAttribute("src", srcServerName);
        model.addAttribute("dst", dstServerName);
        model.addAttribute("file", tran.getFilePath().trim()); //duplicate concatenation from add transaction when available
        model.addAttribute("status", status);
        model.addAttribute("request", "Transaction completed from: " + srcServerName + " to " + dstServerName);


        return "client/transferSuccess";
    }

}
