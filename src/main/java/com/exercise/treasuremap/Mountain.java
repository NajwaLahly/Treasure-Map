package com.exercise.treasuremap;

public class Mountain {
    private Tile mountainTile;

    public Mountain(Tile mountainTile) {
        this.mountainTile = mountainTile;
    }

    public Tile getMountainTile() {
        return mountainTile;
    }

    public void setMountainTile(Tile mountainTile) {
        this.mountainTile = mountainTile;
    }
}
