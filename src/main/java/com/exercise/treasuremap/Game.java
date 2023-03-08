package com.exercise.treasuremap;

import java.util.ArrayList;
import java.util.List;


/**
 * Main entry point to run the game.
 */
public class Game {
    private List<Player> players;
    private TreasureMap map;

    /**
     * @param data Data red from the input file that is needed to initialize the game
     */
    public Game(GameData data) {
        initGame(data);
    }

    /***
     * Initialize the map layout and the player
     * @param data Data red from the input file that is needed to initialize the game
     */
    public void initGame(GameData data) {
        initMap(data);
        initPlayers(data);
    }

    /**
     * Initialize the map layout and tiles
     *
     * @param data Data red from the input file that is needed to initialize the game
     */
    private void initMap(GameData data) {
        map = data.getMap();
        map.setMountains(data.getMountains());
        map.setTreasures(data.getTreasures());
        map.setPlayers(data.getPlayers());
    }

    private void initPlayers(GameData data) {
        this.players = data.getPlayers();
    }

    /**
     * @param nextPos Next position of the player
     * @return (true) if the next position of the player is out of the map
     */
    public boolean isOutOfMap(int[] nextPos) {
        return nextPos[0] < 0 || nextPos[0] >= map.getWidth() || nextPos[1] < 0 || nextPos[1] >= map.getHeight();
    }

    /**
     * @param nextPos Next position of the player
     * @return (true) if the player can to the next position (tile)
     */
    public boolean canMove(int[] nextPos) {
        if (isOutOfMap(nextPos)) {
            return false;
        }
        Tile nextTile = map.getTileFromPos(nextPos[0], nextPos[1]);
        return !nextTile.isMountain() && !nextTile.isOccupied();
    }

    /**
     * Updates the number of treasures in the current tile of the player
     */
    public void updateTreasures(Player player) {
        Tile currentTile = player.getCurrentTile();
        Treasure treasure = currentTile.getTreasure();
        int nbTreasures = treasure.getNbTreasures();
        if (nbTreasures > 0) {
            treasure.setNbTreasures(nbTreasures - 1);
            player.updateTreasures();
        }
    }

    private int getMaxGameTurns() {
        return this.players.stream()
                .mapToInt(player -> player.getDirectionSequence().length())
                .max()
                .orElse(0);
    }

    /**
     * Main game loop
     */
    public void start() {
        for (int i = 0; i < getMaxGameTurns(); i++) {
            for (Player player: players) {
                String directionSequence = player.getDirectionSequence();

                if (i < directionSequence.length()) {
                    String direction = Character.toString(directionSequence.charAt(i));

                    if (Direction.valueOf(direction) != Direction.A) {
                        player.rotate(Direction.valueOf(direction));
                    } else {
                        int[] potentialNextPos = player.getNextPos();
                        if (canMove(potentialNextPos)) {
                            Tile nextTile = map.getTileFromPos(potentialNextPos[0], potentialNextPos[1]);
                            player.move(nextTile);
                            updateTreasures(player);
                        }
                    }
                }
            }
        }
    }

    /**
     * Updates the game data at the end of the game for exporting it.
     *
     * @param outputGameData Data to be updated
     */
    public void updateGameData(GameData outputGameData) {
        outputGameData.setPlayers(this.players);

        ArrayList<Treasure> treasures = outputGameData.getTreasures();
        for (Treasure treasure : treasures) {
            int nbTreasure = map.getTileFromPos(treasure.getPosX(), treasure.getPosY())
                    .getTreasure()
                    .getNbTreasures();
            treasure.setNbTreasures(nbTreasure);
        }
        outputGameData.setTreasures(treasures);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public TreasureMap getMap() {
        return map;
    }
}
