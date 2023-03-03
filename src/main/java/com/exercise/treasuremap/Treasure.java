package com.exercise.treasuremap;

public class Treasure {
    private Tile treasureTile;

    public Treasure(Tile treasureTile) {
        this.treasureTile = treasureTile;
    }

    public Tile getTreasureTile() {
        return treasureTile;
    }

    public void setTreasureTile(Tile treasureTile) {
        this.treasureTile = treasureTile;
    }
}
