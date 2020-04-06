package com.temple.polymorphic.toolbox.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Permissions")
public class Permissions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permission_id;

//    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    @Id
    @ManyToOne
    @JoinColumn(name = "server_id", nullable = false)
    private Server server;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name="username_cred")
    private String usernameCred;

    @Column(name="password_cred")
    private String passwordCred;

    @Column(name="valid")
    private int valid;

    public Permissions(){
    }

    public Permissions(User user, Server server) {
        this.user = user;
        this.server = server;
        this.setCreationDate(new Date());   //for current datetime
    }

    public Permissions(User user, Server server,  String usernameCred, String passwordCred) {
        this.user = user;
        this.server = server;
        this.setCreationDate(new Date());   //for current datetime
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
