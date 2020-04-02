package com.temple.polymorphic.toolbox.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Permissions")
public class Permissions implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "server_id", nullable = false)
    private Server server;

    @Column(name = "creation_date")
    private Date creationDate;

    public Permissions(){
    }

    public Permissions(User user, Server server) {
        this.user = user;
        this.server = server;
        this.setCreationDate(new Date());   //for current datetime
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
}
