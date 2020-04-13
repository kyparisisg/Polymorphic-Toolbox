package com.temple.polymorphic.toolbox.dto;

import com.temple.polymorphic.toolbox.models.Server;
import com.temple.polymorphic.toolbox.models.User;

import java.util.Date;


public class PermissionsDto {

    private User user;
    private Server server;
    private Date creationDate;
    private String usernameCred;
    private String passwordCred;
    private int valid;

    public PermissionsDto() {
        super();
    }
    public PermissionsDto(User user) {
        this.user = user;
    }

    public PermissionsDto(User user, Server server, Date creationDate, String usernameCred, String passwordCred, int valid) {
        this.user = user;
        this.server = server;
        this.creationDate = creationDate;
        this.usernameCred = usernameCred;
        this.passwordCred = passwordCred;
        this.valid = valid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
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
