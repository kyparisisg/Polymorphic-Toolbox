package com.temple.polymorphic.toolbox.dto;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ServerDto {

    private Long id;
    private String name;
    private String ip;
    private int port;
    private String usernameCred;
    private String passwordCred;
    private int health;
    private Date registerDate;

    public ServerDto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public java.util.Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.registerDate = date;
    }
}