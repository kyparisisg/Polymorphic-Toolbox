package com.temple.polymorphic.toolbox.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Permissions")
public class Permissions {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "server_id", nullable = false)
    private Server server;

    @Column(name = "creation_date")
    private Date creationDate;

    public Permissions(){
        //super();
    }

    public Permissions(User user, Server server) {
        this.user = user;
        this.server = server;
        this.setCreationDate(new Date());   //for current datetime

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
