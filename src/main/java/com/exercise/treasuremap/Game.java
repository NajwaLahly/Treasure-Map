package com.exercise.treasuremap;

import java.util.ArrayList;


/**
 * Main entry point to run the game.
 */
public class Game {
    private Player player;
    private TreasureMap map;

    /**
     *
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
        initPlayer(data);
    }

    /**
     * Initialize the map layout and tiles
     * @param data Data red from the input file that is needed to initialize the game
     */
    private void initMap(GameData data) {
        map = new TreasureMap(data.getMapWidth(), data.getMapHeight());
        map.setMountains(data.getMountains());
        map.setTreasures(data.getTreasures());
    }

    /**
     * Initialize the player: start orientation, start position and direction sequence
     * @param data Data red from the input file that is needed to initialize the game
     */
    private void initPlayer(GameData data) {
        String name = data.getPlayerName();
        Tile startTile = new Tile(data.getPlayerPosX(), data.getPlayerPosY());
        Orientation startOrientation = Orientation.valueOf(data.getOrientation());
        String directionSequence = data.getDirectionSequence();
        player = new Player(name, startTile, startOrientation, directionSequence);
    }

    /**
     *
     * @return the next position of the player
     */
    public int[] getNextPos() {
        Tile currentTile = player.getCurrentTile();
        int[] nextPos = new int[]{currentTile.getPosX(), currentTile.getPosY()};
        switch (player.getOrientation()) {
            case E:
                nextPos[0] += 1;
                break;
            case N:
                nextPos[1] -= 1;
                break;
            case O:
                nextPos[0] -= 1;
                break;
            case S:
                nextPos[1] += 1;
                break;
        }
        return nextPos;
    }

    /**
     *
     * @param nextPos Next position of the player
     * @return (true) if the next position of the player is out of the map
     */
    public boolean isOutOfMap(int[] nextPos) {
        return nextPos[0] < 0 || nextPos[0] >= map.getWidth() || nextPos[1] < 0 || nextPos[1] >= map.getHeight();
    }

    /**
     *
     * @param nextPos Next position of the player
     * @return (true) if the player can to the next position (tile)
     */
    public boolean canMove(int[] nextPos) {
        if (isOutOfMap(nextPos)) {
            return false;
        }
        Tile nextTile = map.getTileFromPos(nextPos[0], nextPos[1]);
        return !nextTile.isMountain();
    }

    /**
     * Updates the number of treasures in the current tile of the player
     */
    public void updateTreasures() {
        Tile currentTile = player.getCurrentTile();
        int nbTreasures = currentTile.getNbTreasures();
        if (nbTreasures > 0) {
            currentTile.setNbTreasures(nbTreasures - 1);
            updatePlayerTreasures();
        }
    }

    /**
     * Updates the number of treasures held by the player
     */
    public void updatePlayerTreasures() {
        int currentNbTreasure = this.player.getNbCollectedTreasures();
        this.player.setNbCollectedTreasures(currentNbTreasure + 1);
    }

    /**
     * Main game loop
     */
    public void start() {
        String directionSequence = player.getDirectionSequence();

        for (int i = 0; i < directionSequence.length(); i++) {
            String direction = Character.toString(directionSequence.charAt(i));

            if (Direction.valueOf(direction) != Direction.A) {
                player.rotate(Direction.valueOf(direction));
            } else {
                int[] potentialNextPos = getNextPos();
                if (canMove(potentialNextPos)) {
                    Tile nextTile = map.getTileFromPos(potentialNextPos[0], potentialNextPos[1]);
                    player.move(nextTile);
                    updateTreasures();
                }
            }
        }
    }

    /**
     * Updates the game data at the end of the game for exporting it.
     * @param outputGameData Data to be updated
     */
    public void updateGameData(GameData outputGameData) {
        outputGameData.setPlayerPosX(this.player.getCurrentTile().getPosX());
        outputGameData.setPlayerPosY(this.player.getCurrentTile().getPosY());
        outputGameData.setNbCollectedTreasures(player.getNbCollectedTreasures());
        outputGameData.setOrientation(this.player.getOrientation().toString());

        ArrayList<int[]> treasures = outputGameData.getTreasures();
        for (int[] treasure : treasures) {
            int nbTreasure = map.getTileFromPos(treasure[0], treasure[1]).getNbTreasures();
            treasure[2] = nbTreasure;
        }
        outputGameData.setTreasures(treasures);
    }

    public Player getPlayer() {
        return player;
    }

    public TreasureMap getMap() {
        return map;
    }
}
