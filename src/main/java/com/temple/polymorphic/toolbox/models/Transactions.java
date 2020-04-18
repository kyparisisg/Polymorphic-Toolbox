package com.temple.polymorphic.toolbox.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "src_server", nullable = false)
    private Server src_server;

    @ManyToOne
    @JoinColumn(name = "dst_server", nullable = false)
    private Server dst_server;

    @Column(name="file_name")
    private String fileName;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name="status")
    private int status;

    public Transactions(){
    }

    public Transactions(User user, Server src_server, Server dst_server, String fileName, int status){
        this.user = user;
        this.src_server = src_server;
        this.dst_server = dst_server;
        this.fileName = fileName;
        this.setCreationDate(new Date());   //for current datetime
        this.status = status;
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
