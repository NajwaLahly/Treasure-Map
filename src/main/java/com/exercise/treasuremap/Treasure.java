package com.exercise.treasuremap;

/**
 * Represents a treasure
 */
public class Treasure {
    private int posX;
    private int posY;
    private int nbTreasures;

    public Treasure(int posX, int posY, int nbTreasures) {
        this.posX = posX;
        this.posY = posY;
        this.nbTreasures = nbTreasures;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getNbTreasures() {
        return nbTreasures;
    }

    public void setNbTreasures(int nbTreasures) {
        this.nbTreasures = nbTreasures;
    }
}
