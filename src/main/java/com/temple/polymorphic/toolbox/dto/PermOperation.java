package com.temple.polymorphic.toolbox.dto;

public class PermOperation {

    private String email;
    private String ip;
    private Long serverId;
    private String username;
    private String password;

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

    public PermOperation(String email, String ip, String username, String password) {
        this.email = email;
        this.ip = ip;
        this.username = username;
        this.password = password;
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

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
