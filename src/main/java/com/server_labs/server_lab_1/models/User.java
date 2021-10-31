package com.server_labs.server_lab_1.models;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "user_login", unique = true)
    private String login;
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "user_password")
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "user_active")
    private boolean active;
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
