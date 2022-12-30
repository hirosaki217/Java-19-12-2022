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

    private Date createdAt = new Date();


    private String groupRole;
    private boolean isDelete =false;
    @Column(columnDefinition = "TINYINT default 1",  nullable = false)
    private boolean active;
    private String password;
    private String rememberToken;
    private Date lastLogin;
    private String ipLastLogin;

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return active;
    }
    public boolean getIsActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public void setIsActive(boolean active) {
        this.active = active;
    }
    public String getIpLastLogin() {
        return ipLastLogin;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public void setIpLastLogin(String ipLastLogin) {
        this.ipLastLogin = ipLastLogin;
    }

    /**
     *
     * @param email
     * @param name
     * @param groups
     * @param active
     * @param password
     */
    public User(String email, String name, String groups, boolean active, String password) {
        this.email = email;
        this.name = name;
        this.groupRole = groups;
        this.active = active;
        this.password = password;
    }

    /**
     *
     * @param email
     * @param name
     * @param groups
     * @param active
     */

    public User(String email, String name, String groups, boolean active) {
        this.email = email;
        this.name = name;
        this.groupRole = groups;
        this.active = active;
    }

    public User() {
    }

    /**
     *
     * @param email
     * @param password
     */
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

    public String getGroupRole() {
        return groupRole;
    }

    public void setGroupRole(String groups) {
        this.groupRole = groups;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
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
                ", groups='" + groupRole + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", isActive=" + active +
                ", is_delete=" + isDelete +
                '}';
    }
}
