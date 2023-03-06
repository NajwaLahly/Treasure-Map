package com.exercise.treasuremap;

/**
 * Class that represents a tile of the map. A tile is the base unit of the map (1 square).
 */
public class Tile {
    private int posX;
    private int posY;
    private boolean isMountain = false;
    private int nbTreasures = 0;


    public Tile(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean isMountain() {
        return isMountain;
    }

    public void setMountain(boolean mountain) {
        isMountain = mountain;
    }

    public int getNbTreasures() {
        return nbTreasures;
    }

    public void setNbTreasures(int nbTreasures) {
        this.nbTreasures = nbTreasures;
    }
}
