package com.exercise.treasuremap;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the game data that is red from the input file
 * that can also be exported as an output file.
 */
public class GameData {
    private TreasureMap map;
    private List<Player> players = new ArrayList<>();
    /**
     * Defines a list of positions of mountains. Each element is an int list (int[x,y])
     * where the first elements is the position in the X axis, and the second element
     * is the position in the Y axis
     */
    private ArrayList<Mountain> mountains = new ArrayList<>();
    /**
     * Defines a list of treasures. Each element is an int list with 3 ints (int[x,y,nb]).
     * x: denotes the position in the X axis.
     * y: denotes the position in the Y axis.
     * nb: denotes the number of treasures.
     */
    private ArrayList<Treasure> treasures = new ArrayList<>();

    public TreasureMap getMap() {
        return map;
    }

    public void setMap(TreasureMap map) {
        this.map = map;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public ArrayList<Mountain> getMountains() {
        return mountains;
    }

    public void setMountains(ArrayList<Mountain> mountains) {
        this.mountains = mountains;
    }

    public ArrayList<Treasure> getTreasures() {
        return treasures;
    }

    public void setTreasures(ArrayList<Treasure> treasures) {
        this.treasures = treasures;
    }

}
