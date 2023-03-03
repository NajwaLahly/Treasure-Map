package com.exercise.treasuremap;

import java.util.ArrayList;

public class TreasureMap {
    private int height;
    private int width;

    ArrayList<ArrayList<Tile>> tileList;


    public TreasureMap(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<ArrayList<Tile>> getTileList() {
        return tileList;
    }

    public void setTileList(ArrayList<ArrayList<Tile>> tileList) {
        this.tileList = tileList;
    }
}
