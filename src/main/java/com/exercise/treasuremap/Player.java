package com.exercise.treasuremap;

public class Player {
    private Tile currentTile;
    private String name;
    private Orientation orientation;
    private String directionSequence;

    private int nbCollectedTreasures = 0;

    public Player(String name, Tile currentTile, Orientation orientation, String directionSequence) {
        this.currentTile = currentTile;
        this.name = name;
        this.orientation = orientation;
        this.directionSequence = directionSequence;
    }

    public void rotate(Direction direction) {
        int directionFactor = direction == Direction.G ? -1 : 1;
        int newOrientationIndex = (this.orientation.ordinal() + directionFactor + Orientation.values().length) % Orientation.values().length;
        this.orientation = Orientation.values()[newOrientationIndex];
    }

    public void move(Tile nextTile) {
        this.currentTile = nextTile;
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

    public int getNbCollectedTreasures() {
        return nbCollectedTreasures;
    }

    public void setNbCollectedTreasures(int nbCollectedTreasures) {
        this.nbCollectedTreasures = nbCollectedTreasures;
    }
}
