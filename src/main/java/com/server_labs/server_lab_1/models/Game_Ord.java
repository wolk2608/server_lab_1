package com.server_labs.server_lab_1.models;

import javax.persistence.*;

@Entity
@Table(name = "game_ord", uniqueConstraints = { @UniqueConstraint(columnNames = {"id_game", "id_ord"} ) } )
public class Game_Ord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_game_ord")
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name="id_game")
    private Game game;
    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }

    @ManyToOne
    @JoinColumn(name="id_ord")
    private Ord ord;
    public Ord getOrder() {
        return ord;
    }
    public void setOrder(Ord ord) {
        this.ord = ord;
    }

    @Column(name = "count")
    private int count;
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public Game_Ord() {
    }

    public Game_Ord(Game game, Ord ord, int count) {
        this.game = game;
        this.ord = ord;
        this.count = count;
    }
}