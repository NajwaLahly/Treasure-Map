package com.exercise.treasuremap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputData {
    private int mapWidth;
    private int mapHeight;
    private String playerName;
    private int playerInitPosX;
    private int playerInitPosY;
    private String directionSequence;
    private String initOrientation;
    private ArrayList<int[]> mountains = new ArrayList<>();
    private ArrayList<int[]> treasures = new ArrayList<>();

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

    public int getPlayerInitPosX() {
        return playerInitPosX;
    }

    public void setPlayerInitPosX(int playerInitPosX) {
        this.playerInitPosX = playerInitPosX;
    }

    public int getPlayerInitPosY() {
        return playerInitPosY;
    }

    public void setPlayerInitPosY(int playerInitPosY) {
        this.playerInitPosY = playerInitPosY;
    }

    public String getDirectionSequence() {
        return directionSequence;
    }

    public void setDirectionSequence(String directionSequence) {
        this.directionSequence = directionSequence;
    }

    public String getInitOrientation() {
        return initOrientation;
    }

    public void setInitOrientation(String initOrientation) {
        this.initOrientation = initOrientation;
    }

    public ArrayList<int[]> getMountains() {
        return mountains;
    }

    public void setMountains(ArrayList<int[]> mountains) {
        this.mountains = mountains;
    }

    public ArrayList<int[]> getTreasures() {
        return treasures;
    }

    public void setTreasures(ArrayList<int[]> treasures) {
        this.treasures = treasures;
    }
}
