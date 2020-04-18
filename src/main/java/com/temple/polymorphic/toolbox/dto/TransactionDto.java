package com.temple.polymorphic.toolbox.dto;

import com.temple.polymorphic.toolbox.models.Server;
import com.temple.polymorphic.toolbox.models.User;

import java.util.Date;

public class TransactionDto {

    private User user;
    private Server src_server;
    private Server dst_server;
    private String fileName;
    private Date creationDate;
    private int status;
    //private String encryption_token; Is this necessary?

    public TransactionDto() {
    }

    public TransactionDto(User user, Server srcServer, Server dstServer, String fileName, Date creationDate, int status){//, String encryption_token) {
        this.user = user;
        this.src_server = srcServer;
        this.dst_server = dstServer;
        this.fileName = fileName;
        this.creationDate = creationDate;
        this.status = status;
        //this.encryption_token = encryption_token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Server getSrc_server() {
        return src_server;
    }

    public void setSrc_server(Server src_server) {
        this.src_server = src_server;
    }

    public Server getDst_server() {
        return dst_server;
    }

    public void setDst_server(Server dst_server) {
        this.dst_server = dst_server;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
