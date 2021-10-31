package com.server_labs.server_lab_1.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ord")
public class Ord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ord")
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
    @Column(name = "ord_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Ord() {
    }

    public Ord(User user) {
        this.user = user;
    }
}
