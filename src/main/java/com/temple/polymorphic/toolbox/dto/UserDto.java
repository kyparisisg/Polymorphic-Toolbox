//package com.temple.polymorphic.toolbox.models;
package com.temple.polymorphic.toolbox.dto;

import org.jetbrains.annotations.NotNull;
import java.util.Date;

public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    @NotNull
    private String email;
    private String role;
    private String password;
    private Date lastLogin;
    private Date regDate;

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String email, String role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public UserDto(String firstName, String lastName, String email, String role, Date regDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.regDate = regDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getRegDate() {
        return regDate;
    }

}
