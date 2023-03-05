package com.exercise.treasuremap;

public class Game {
    private Player player;
    private TreasureMap map;

    public Game(InputData data) {
        initGame(data);
    }

    public void initGame(InputData data) {
        initMap(data);
        initPlayer(data);
    }

    private void initMap(InputData data) {
        map = new TreasureMap(data.getMapWidth(), data.getMapHeight());
        map.setMountains(data.getMountains());
        map.setTreasures(data.getTreasures());
    }

    private void initPlayer(InputData data) {
        String name = data.getPlayerName();
        Tile startTile = new Tile(data.getPlayerInitPosX(), data.getPlayerInitPosY());
        Orientation startOrientation = Orientation.valueOf(data.getInitOrientation());
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

    public boolean isOutOfMap(int[] nextPos){
        return nextPos[0] < 0 || nextPos[0] >= map.getWidth() || nextPos[1] < 0 || nextPos[1] >= map.getHeight();
    }

    public boolean canMove(int[] nextPos) {
        if(isOutOfMap(nextPos)){
            return false;
        }
        Tile nextTile = map.getTileFromPos(nextPos[0], nextPos[1]);
        return !nextTile.isMountain();
    }

    public void updateTreasuresInTile(Tile nextTile) {
        int nbTreasures = nextTile.getNbTreasures();
        if (nbTreasures > 0) {
            nextTile.setNbTreasures(nbTreasures - 1);
        }
    }

    public void start() {
        String directionSequence = player.getDirectionSequence();

        for (int i = 0; i < directionSequence.length(); i++) {
            String direction = Character.toString(directionSequence.charAt(i));

            if (Direction.valueOf(direction) != Direction.A) {
                player.rotate(Direction.valueOf(direction));
            } else {
                Tile currentTile = player.getCurrentTile();
                int[] potentialNextPos = getNextPos();

                if (canMove(potentialNextPos)) {
                    Tile nextTile = map.getTileFromPos(potentialNextPos[0], potentialNextPos[1]);
                    player.move(nextTile);
                    updateTreasuresInTile(nextTile);
                }
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public TreasureMap getMap() {
        return map;
    }

    public void setMap(TreasureMap map) {
        this.map = map;
    }
}
