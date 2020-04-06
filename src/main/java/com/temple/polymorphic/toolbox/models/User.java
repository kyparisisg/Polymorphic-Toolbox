package com.temple.polymorphic.toolbox.models;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    @NotNull
    private String lastName;
    @Column
    @NotNull
    private String email;
    @Column
    //@JsonIgnore
    private String password;
    @Column
    @NotNull
    private String role;
    @Column(name="register_date")
    private Date registerDate;
    @Column(name="last_login")
    private Date lastLogin;

//was not working
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<Permissions> userPermissions;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    //private Set<Specific_Creds> userCreds;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<Transactions> userTransactions;

    public User() {

    }

    public User(String firstName, String lastName, String email, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.setRegisterDate(new Date());   //for current datetime
        this.lastLogin = null;
    }

    public User(String firstName, String lastName, String email, String password, String role,
                Set<Permissions> userPermissions, Set<Transactions> userTransactions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.setRegisterDate(new Date());   //for current datetime
        this.lastLogin = null;
        //this.userPermissions = userPermissions;
        //this.userCreds = userCreds;
        //this.userTransactions = userTransactions;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date date) {
        this.registerDate = date;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.registerDate = date;
    }

//was not working
//    public Set<Permissions> getUserPermissions() {
//        return userPermissions;
//    }
//
//    public void setUserPermissions(Set<Permissions> userPermissions) {
//        this.userPermissions = userPermissions;
//    }
//
//    /*public Set<Specific_Creds> getUserCreds() {
//        return userCreds;
//    }
//
//    public void setUserCreds(Set<Specific_Creds> userCreds) {
//        this.userCreds = userCreds;
//    }*/
//
//    public Set<Transactions> getUserTransactions() {
//        return userTransactions;
//    }
//
//    public void setUserTransactions(Set<Transactions> userTransactions) {
//        this.userTransactions = userTransactions;
//    }
}
