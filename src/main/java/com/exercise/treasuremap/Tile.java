package com.exercise.treasuremap;

public class Tile {
    private int posX;
    private int posY;
    private boolean isMountain = false;
    private int nbrOfTreasure = 0;


    public Tile(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    public boolean isOutOfMap(TreasureMap map){
        return getPosX() < 0 || getPosX() > map.getWidth() || getPosY() < 0 || getPosY() > map.getHeight();
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isMountain() {
        return isMountain;
    }

    public void setMountain(boolean mountain) {
        isMountain = mountain;
    }

    public int getNbrOfTreasure() {
        return nbrOfTreasure;
    }

    public void setNbrOfTreasure(int nbrOfTreasure) {
        this.nbrOfTreasure = nbrOfTreasure;
    }
}
