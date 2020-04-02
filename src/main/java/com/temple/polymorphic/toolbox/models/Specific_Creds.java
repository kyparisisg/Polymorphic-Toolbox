package com.temple.polymorphic.toolbox.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Specific_Creds")
public class Specific_Creds implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "server_id", nullable = false)
    private Server server;

    @Column(name="username_cred")
    private String usernameCred;

    @Column(name="password_cred")
    private String passwordCred;

    @Column(name="valid")
    private int valid;

    public Specific_Creds(){
    }

    public Specific_Creds(User user, Server server, String usernameCred, String passwordCred) {
        this.user = user;
        this.server = server;
        this.usernameCred = usernameCred;
        this.passwordCred = passwordCred;
        this.valid = 0; //not tested if valid yet
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
