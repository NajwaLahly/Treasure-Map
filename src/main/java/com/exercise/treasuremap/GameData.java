package com.exercise.treasuremap;

import java.util.ArrayList;

public class GameData {
    private int mapWidth;
    private int mapHeight;
    private String playerName;
    private int playerPosX;
    private int playerPosY;
    private String directionSequence;
    private String orientation;
    private ArrayList<int[]> mountains = new ArrayList<>();
    private ArrayList<int[]> treasures = new ArrayList<>();

    private int nbCollectedTreasures = 0;

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerPosX() {
        return playerPosX;
    }

    public void setPlayerPosX(int playerPosX) {
        this.playerPosX = playerPosX;
    }

    public int getPlayerPosY() {
        return playerPosY;
    }

    public void setPlayerPosY(int playerPosY) {
        this.playerPosY = playerPosY;
    }

    public String getDirectionSequence() {
        return directionSequence;
    }

    public void setDirectionSequence(String directionSequence) {
        this.directionSequence = directionSequence;
    }

    public void setMountains(ArrayList<int[]> mountains) {
        this.mountains = mountains;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public ArrayList<int[]> getMountains() {
        return mountains;
    }

    public ArrayList<int[]> getTreasures() {
        return treasures;
    }

    public void setTreasures(ArrayList<int[]> treasures) {
        this.treasures = treasures;
    }

    public int getNbCollectedTreasures() {
        return nbCollectedTreasures;
    }

    public void setNbCollectedTreasures(int nbCollectedTreasures) {
        this.nbCollectedTreasures = nbCollectedTreasures;
    }
}
