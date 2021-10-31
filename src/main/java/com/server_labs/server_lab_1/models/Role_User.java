package com.server_labs.server_lab_1.models;

import javax.persistence.*;

@Entity
@Table(name = "role_user", uniqueConstraints = { @UniqueConstraint(columnNames = {"id_role", "id_user"} ) } )
public class Role_User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_role_user")
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name="id_role")
    private Role role;
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToOne
    @JoinColumn(name="id_user")
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Role_User() {
    }

    public Role_User(Role role, User user) {
        this.role = role;
        this.user = user;
    }

}
