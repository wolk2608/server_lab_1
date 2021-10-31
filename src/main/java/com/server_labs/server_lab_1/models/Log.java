package com.server_labs.server_lab_1.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "log", uniqueConstraints = { @UniqueConstraint(columnNames = {"id_log", "id_user"} ) } )
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_log")
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    @Basic(optional = false)
    @Column(name = "log_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Log() {
    }

    public Log(User user) {
        this.user = user;
    }
}
