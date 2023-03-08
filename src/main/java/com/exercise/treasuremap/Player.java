package com.exercise.treasuremap;

/**
 * Class that represents the player.
 */
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

    /**
     * Rotates the player from his current orientation depending on the given direction
     * @param direction Direction in which the player will rotate
     */
    public void rotate(Direction direction) {
        int directionFactor = direction == Direction.G ? -1 : 1;
        int newOrientationIndex = (this.orientation.ordinal() + directionFactor + Orientation.values().length) % Orientation.values().length;
        this.orientation = Orientation.values()[newOrientationIndex];
    }

    /**
     * Moves the player to the next tile
     * @param nextTile Next tile to be moved into
     */
    public void move(Tile nextTile) {
        this.currentTile.setOccupied(false);
        this.currentTile = nextTile;
        this.currentTile.setOccupied(true);
    }

    /**
     * @return the next position of the player
     */
    public int[] getNextPos() {
        int[] nextPos = new int[]{this.currentTile.getPosX(), this.currentTile.getPosY()};
        switch (this.orientation) {
            case E:
                nextPos[0] += 1;
                break;
            case N:
                nextPos[1] -= 1;
                break;
            case O:
                nextPos[0] -= 1;
                break;
            case S:
                nextPos[1] += 1;
                break;
        }
        return nextPos;
    }

    /**
     * Updates the number of treasures held by the player
     */
    public void updateTreasures() {
        this.nbCollectedTreasures += 1 ;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public String getName() {
        return name;
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

    public void setCurrentTile(Tile currentTile) {
        this.currentTile = currentTile;
    }
}
