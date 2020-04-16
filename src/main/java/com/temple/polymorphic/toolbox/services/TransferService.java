package com.temple.polymorphic.toolbox.services;

import com.temple.polymorphic.toolbox.TransactionRepository;
import com.temple.polymorphic.toolbox.dto.TransactionDto;
import com.temple.polymorphic.toolbox.PermissionRepository;
import com.temple.polymorphic.toolbox.dto.ServerDto;
import com.temple.polymorphic.toolbox.models.Server;
import com.temple.polymorphic.toolbox.models.Transactions;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransferService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PermissionRepository permissionsRepository;

    public List<TransactionDto> getTransactions(String email) {
        List<Transactions> trans = transactionRepository.findAllByEmail(email);
        ArrayList<TransactionDto> transDto = new ArrayList<TransactionDto>();
        for(Transactions tran : trans) {
            transDto.add(new TransactionDto(tran.getUser(), tran.getSrc_server(), tran.getDst_server(), tran.getFileName(), tran.getCreationDate(), tran.getStatus()));
        }
        return transDto;
    }

    public List<ServerDto> getServersForUser(String email){
        List<Server> servers = permissionsRepository.findAllServersByEmail(email);
        ArrayList<ServerDto> serverList = new ArrayList<ServerDto>();
        for(Server server: servers){
            serverList.add(new ServerDto(server.getId(), server.getName(), server.getIp(), server.getPort(),
                    server.getUsernameCred(), server.getPasswordCred(), server.getHealth(), server.getRegisterDate(), server.getKeyLocation()));
        }
        return serverList;
    }
}
