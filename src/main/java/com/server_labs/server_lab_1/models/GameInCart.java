package com.server_labs.server_lab_1.models;

import com.server_labs.server_lab_1.models.Game;

public class GameInCart {
    private Game game;
    private int count;

    public GameInCart(Game game, int count) {
        this.game = game;
        this.count = count;
    }

    public GameInCart(int id, String name, Double cost, int count) {
        this.game.setId(id);
        this.game.setName(name);
        this.game.setCost(cost);
        this.count = count;
    }
    public GameInCart() {

    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
