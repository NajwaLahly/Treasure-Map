package com.exercise.treasuremap;

import java.util.ArrayList;

public class TreasureMap {
    private int height;
    private int width;
    private Tile[][] tiles;


    public TreasureMap(int width, int height) {
        this.height = height;
        this.width = width;
        initMapTiles();
    }

    public void initMapTiles() {
        this.tiles = new Tile[this.height][this.width];

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                this.tiles[j][i] = new Tile(i, j);
            }
        }
    }

    public void setTreasures(ArrayList<int[]> treasures) {
        for (int[] treasure : treasures) {
            Tile treasureTile = getTileFromPos(treasure[0], treasure[1]);
            treasureTile.setNbTreasures(treasure[2]);
        }
    }

    public void setMountains(ArrayList<int[]> mountains) {
        for (int[] mountain : mountains) {
            Tile tile = getTileFromPos(mountain[0], mountain[1]);
            tile.setMountain(true);
        }
    }

    public Tile getTileFromPos(int posX, int posY) {
        return tiles[posY][posX];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
