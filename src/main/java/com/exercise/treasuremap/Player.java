package com.exercise.treasuremap;

public class Player {
    private Tile currentTile;
    private String name;
    private Orientation orientation;
    private String directionSequence;

    public Player(String name, Tile currentTile, Orientation orientation, String directionSequence) {
        this.currentTile = currentTile;
        this.name = name;
        this.orientation = orientation;
        this.directionSequence = directionSequence;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public String getDirectionSequence() {
        return directionSequence;
    }

    public void setDirectionSequence(String directionSequence) {
        this.directionSequence = directionSequence;
    }
}
