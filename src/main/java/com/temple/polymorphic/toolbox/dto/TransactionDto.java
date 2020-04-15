package com.temple.polymorphic.toolbox.dto;

import com.temple.polymorphic.toolbox.models.Server;
import com.temple.polymorphic.toolbox.models.User;

import java.util.Date;

public class TransactionDto {

    private User user;
    private Server srcServer;
    private Server dstServer;
    private String file_name;
    private Date creationDate;
    private String usernameCred;
    private String passwordCred;
    private int valid;
    //private String encryption_token; Is this necessary?

    public TransactionDto() {
    }

    public TransactionDto(User user, Server srcServer, Server dstServer, String file_name, Date creationDate, String usernameCred, String passwordCred, int valid){//, String encryption_token) {
        this.user = user;
        this.srcServer = srcServer;
        this.dstServer = dstServer;
        this.file_name = file_name;
        this.creationDate = creationDate;
        this.usernameCred = usernameCred;
        this.passwordCred = passwordCred;
        this.valid = valid;
        //this.encryption_token = encryption_token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Server getSrcServer() {
        return srcServer;
    }

    public void setSrcServer(Server srcServer) {
        this.srcServer = srcServer;
    }

    public Server getDstServer() {
        return dstServer;
    }

    public void setDstServer(Server dstServer) {
        this.dstServer = dstServer;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getUsernameCred() {
        return usernameCred;
    }

    public void setUsernameCred(String usernameCred) {
        this.usernameCred = usernameCred;
    }

    public String getPasswordCred() {
        return passwordCred;
    }

    public void setPasswordCred(String passwordCred) {
        this.passwordCred = passwordCred;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }
}
