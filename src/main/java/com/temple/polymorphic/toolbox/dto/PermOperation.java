package com.temple.polymorphic.toolbox.dto;

public class PermOperation {

    private String email;
    private String ip;
    private String operation;
    private Long serverId;

    public PermOperation() {
        super();
    }

    public PermOperation(String email, String ip) {
        this.email = email;
        this.ip = ip;
    }

    public PermOperation(String email, Long serverId) {
        this.email = email;
        this.serverId = serverId;
    }

    public PermOperation(String email, Long serverId, String ip) {
        this.email = email;
        this.serverId = serverId;
        this.ip =  ip;
    }


    public PermOperation(String email, String ip, String operation) {
        this.email = email;
        this.ip = ip;
        this.operation = operation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
