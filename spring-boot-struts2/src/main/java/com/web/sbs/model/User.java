package com.web.sbs.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(columnDefinition = "varchar(50)")
    private String email;
    private String name;



    private String groups;
    private boolean status = true;
    private String password;

    private Date lastLogin;
    private String ipLastLogin;

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getIpLastLogin() {
        return ipLastLogin;
    }

    public void setIpLastLogin(String ipLastLogin) {
        this.ipLastLogin = ipLastLogin;
    }

    public User(String email, String name, String groups, boolean status, String password) {
        this.email = email;
        this.name = name;
        this.groups = groups;
        this.status = status;
        this.password = password;
    }

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", groups='" + groups + '\'' +
                ", status=" + status +
                '}';
    }
}
