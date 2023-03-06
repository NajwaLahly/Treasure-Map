package com.exercise.treasuremap;

import java.util.ArrayList;

public class Game {
    private Player player;
    private TreasureMap map;

    public Game(GameData data) {
        initGame(data);
    }

    public void initGame(GameData data) {
        initMap(data);
        initPlayer(data);
    }

    private void initMap(GameData data) {
        map = new TreasureMap(data.getMapWidth(), data.getMapHeight());
        map.setMountains(data.getMountains());
        map.setTreasures(data.getTreasures());
    }

    private void initPlayer(GameData data) {
        String name = data.getPlayerName();
        Tile startTile = new Tile(data.getPlayerPosX(), data.getPlayerPosY());
        Orientation startOrientation = Orientation.valueOf(data.getOrientation());
        String directionSequence = data.getDirectionSequence();
        player = new Player(name, startTile, startOrientation, directionSequence);
    }

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

    public boolean isOutOfMap(int[] nextPos) {
        return nextPos[0] < 0 || nextPos[0] >= map.getWidth() || nextPos[1] < 0 || nextPos[1] >= map.getHeight();
    }

    public boolean canMove(int[] nextPos) {
        if (isOutOfMap(nextPos)) {
            return false;
        }
        Tile nextTile = map.getTileFromPos(nextPos[0], nextPos[1]);
        return !nextTile.isMountain();
    }

    public void updateTreasures(Tile nextTile) {
        int nbTreasures = nextTile.getNbTreasures();
        if (nbTreasures > 0) {
            nextTile.setNbTreasures(nbTreasures - 1);
            updatePlayerTreasures();
        }
    }

    public void updatePlayerTreasures() {
        int currentNbTreasure = this.player.getNbCollectedTreasures();
        this.player.setNbCollectedTreasures(currentNbTreasure + 1);
    }

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
                    updateTreasures(nextTile);
                }
            }
        }
    }

    public GameData updateGameData(GameData data) {
        GameData outputGameData = data;
        outputGameData.setPlayerPosX(this.player.getCurrentTile().getPosX());
        outputGameData.setPlayerPosY(this.player.getCurrentTile().getPosY());
        outputGameData.setNbCollectedTreasures(player.getNbCollectedTreasures());
        outputGameData.setOrientation(this.player.getOrientation().toString());

        ArrayList<int[]> treasures = outputGameData.getTreasures();
        for (int i = 0; i < treasures.size(); i++) {
            int nbTreasure = map.getTileFromPos(treasures.get(i)[0], treasures.get(i)[1]).getNbTreasures();
            treasures.get(i)[2] = nbTreasure;
        }
        outputGameData.setTreasures(treasures);
        return outputGameData;
    }

    public Player getPlayer() {
        return player;
    }

    public TreasureMap getMap() {
        return map;
    }
}
