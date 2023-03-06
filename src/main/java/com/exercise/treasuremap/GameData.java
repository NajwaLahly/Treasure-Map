package com.exercise.treasuremap;

import java.util.ArrayList;

/**
 * Class that represents the game data that is red from the input file
 * that can also be exported as an output file.
 */
public class GameData {
    private int mapWidth;
    private int mapHeight;
    private String playerName;
    private int playerPosX;
    private int playerPosY;
    private String directionSequence;
    private String orientation;
    /**
     * Defines a list of positions of mountains. Each element is an int list (int[x,y])
     * where the first elements is the position in the X axis, and the second element
     * is the position in the Y axis
     */
    private ArrayList<int[]> mountains = new ArrayList<>();
    /**
     * Defines a list of treasures. Each element is an int list with 3 ints (int[x,y,nb]).
     * x: denotes the position in the X axis.
     * y: denotes the position in the Y axis.
     * nb: denotes the number of treasures.
     */
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
