package com.temple.polymorphic.toolbox.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Permissions")
public class Permissions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = true, updatable = true, nullable = true)
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "server_id", nullable = false)
//    private Server server;
    @Column(name = "server_id")
    private Long serverId;

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

    public Permissions(User user, Long serverId) {
        this.user = user;
        this.serverId = serverId;
        this.setCreationDate(new Date());   //for current datetime
    }

    public Permissions(User user, Long serverId,  String usernameCred, String passwordCred) {
        this.user = user;
        this.serverId = serverId;
        this.setCreationDate(new Date());   //for current datetime
        this.usernameCred = usernameCred;
        this.passwordCred = passwordCred;
        this.valid = 0; //not tested if valid yet
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
