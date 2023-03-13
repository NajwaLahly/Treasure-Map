package com.exercise.treasuremap;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the map of the game
 */
class TreasureMap {
    private int height;
    private int width;
    private Tile[][] tiles;


    public TreasureMap(int width, int height) {
        this.height = height;
        this.width = width;
        initMapTiles();
    }

    /**
     * Initializes the map layout
     */
    public void initMapTiles() {
        this.tiles = new Tile[this.height][this.width];

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                this.tiles[j][i] = new Tile(i, j);
            }
        }
    }

    /**
     * Initializes the treasures on the map
     * @param treasures List of treasures data to be used for the initialization
     */
    public void setTreasures(ArrayList<Treasure> treasures) {
        for (Treasure treasure : treasures) {
            Tile treasureTile = getTileFromPos(treasure.getPosX(), treasure.getPosY());
            treasureTile.setTreasure(treasure);
        }
    }

    /**
     * Initializes the mountains on the map
     * @param mountains List of mountains positions
     */
    public void setMountains(ArrayList<Mountain> mountains) {
        for (Mountain mountain : mountains) {
            Tile tile = getTileFromPos(mountain.getPosX(), mountain.getPosY());
            tile.setMountain(true);
        }
    }

    /**
     * Marks players' tiles as occupied
     * @param players
     */
    public void setPlayers(List<Player> players) {
        for (Player player : players) {
            Tile tile = getTileFromPos(player.getCurrentTile().getPosX(), player.getCurrentTile().getPosY());
            tile.setOccupied(true);
            player.setCurrentTile(tile);
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
