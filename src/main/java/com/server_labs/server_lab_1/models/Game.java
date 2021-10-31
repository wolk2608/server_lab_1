package com.server_labs.server_lab_1.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_game")
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "game_name", unique = true)
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "game_cost")
    private double cost;
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    public Game() {
    }

    public Game(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}
