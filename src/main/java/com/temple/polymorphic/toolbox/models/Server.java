package com.temple.polymorphic.toolbox.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "Servers")
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String ip;
    @Column(columnDefinition = "integer default 22")
    private int port;
    @Column(name="username_cred")
    private String usernameCred;
    @Column(name="password_cred")
//    @JsonIgnore
    private String passwordCred;
    @Column(name="health")
    private int health;
    @Column(name="register_date")
    private Date registerDate;
    @Column(name="key_location")
    private String keyLocation;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "server", cascade = CascadeType.ALL)
    private Set<Permissions> serverPermission;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "server", cascade = CascadeType.ALL)
    private Set<Specific_Creds> serverCreds;

    //@OneToMany(fetch = FetchType.EAGER,mappedBy = "server", cascade = CascadeType.ALL)
    //private Set<Transactions> serverTransactions;

    public Server(){

    }
    public Server(String name, String ip, String usernameCred, String passwordCred, String keyLocation){
        this.name = name;
        this.ip = ip;
        this.port = 22; // Default port value, if port number was not given
        this.usernameCred = usernameCred;
        this.passwordCred = passwordCred;
        this.health = 0; // Default value is 0 as the server has not been tested if it is online yet.
        this.registerDate = new Date();
        this.keyLocation = keyLocation;
    }

    public Server(String name, String ip, String usernameCred, String passwordCred, String keyLocation,
                  Set<Permissions> serverPermission, Set<Specific_Creds> serverCreds){
        this.name = name;
        this.ip = ip;
        this.usernameCred = usernameCred;
        this.passwordCred = passwordCred;
        this.health = 0; // Default value is 0 as the server has not been tested if it is online yet.
        this.registerDate = new Date();
        this.keyLocation = keyLocation;
        this.serverPermission = serverPermission;
        this.serverCreds = serverCreds;
        //this.serverTransactions = serverTransactions;
    }

    public Server(String name, String ip, String usernameCred, String passwordCred, String keyLocation, int port){
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.usernameCred = usernameCred;
        this.passwordCred = passwordCred;
        this.health = 0; // Default value is 0 as the server has not been tested if it is online yet.
        this.registerDate = new Date();
        this.keyLocation = keyLocation;
    }

    public Server(String name, String ip, String usernameCred, String passwordCred, String keyLocation, int port,
                  Set<Permissions> serverPermission, Set<Specific_Creds> serverCreds){
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.usernameCred = usernameCred;
        this.passwordCred = passwordCred;
        this.health = 0; // Default value is 0 as the server has not been tested if it is online yet.
        this.registerDate = new Date();
        this.keyLocation = keyLocation;
        this.serverPermission = serverPermission;
        this.serverCreds = serverCreds;
        //this.serverTransactions = serverTransactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getKeyLocation() { return keyLocation; }

    public void setKeyLocation(String keyLocation) { this.keyLocation = keyLocation; }

    public java.util.Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.registerDate = date;
    }

    public Set<Permissions> getServerPermission() {
        return serverPermission;
    }

    public void setServerPermission(Set<Permissions> serverPermission) {
        this.serverPermission = serverPermission;
    }

    public Set<Specific_Creds> getServerCreds() {
        return serverCreds;
    }

    public void setServerCreds(Set<Specific_Creds> serverCreds) {
        this.serverCreds = serverCreds;
    }

    /*public Set<Transactions> getServerTransactions() {
        return serverTransactions;
    }

    public void setServerTransactions(Set<Transactions> serverTransactions) {
        this.serverTransactions = serverTransactions;
    }*/
}
