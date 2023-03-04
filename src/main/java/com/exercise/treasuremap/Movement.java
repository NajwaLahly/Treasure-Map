package com.exercise.treasuremap;

public class Movement {

    public Orientation rotate(Orientation currentOrientation, Direction direction) {
        if (direction == Direction.A) {
            return currentOrientation;
        } else {
            int directionFactor = direction == Direction.G ? -1 : 1;
            int newOrientationIndex = (currentOrientation.ordinal() + directionFactor + Orientation.values().length) % Orientation.values().length;
            return Orientation.values()[newOrientationIndex];
        }
    }

    public Tile getNextTile(Orientation orientation, Tile currentTile) {
        Tile nextTile = new Tile(currentTile.getPosX(), currentTile.getPosY());
        switch (orientation) {
            case E:
                nextTile.setPosX(nextTile.getPosX() + 1);
                break;
            case N:
                nextTile.setPosY(nextTile.getPosY() - 1);
                break;
            case O:
                nextTile.setPosX(nextTile.getPosX() - 1);
                break;
            case S:
                nextTile.setPosY(nextTile.getPosY() + 1);
                break;
        }
        return nextTile;
    }

    public boolean canMove(Tile nextTile, TreasureMap map) {
        return !nextTile.isMountain() && !nextTile.isOutOfMap(map);
    }

    public void updateTreasuresInMap(Tile nextTile, TreasureMap map) {
        Tile tileInMapTileList = map.getTileList().get(nextTile.getPosY()).get(nextTile.getPosX());
        if (tileInMapTileList.getNbrOfTreasure() > 0) {
            System.out.println(tileInMapTileList.getPosX());
            tileInMapTileList.setNbrOfTreasure(tileInMapTileList.getNbrOfTreasure() - 1);
        }
    }

    public void startGame(Player player, TreasureMap map) {
        String directionSequence = player.getDirectionSequence();
        Tile currentTile = player.getCurrentTile();
        for (int i=0; i<directionSequence.length(); i++){
            Orientation newOrientation = rotate(player.getOrientation(),
                    Direction.valueOf(String.valueOf(directionSequence.charAt(i))));
            player.setOrientation(newOrientation);
            Tile potentialNextTile = getNextTile(newOrientation, currentTile);
            if (canMove(potentialNextTile, map)){
                updateTreasuresInMap(potentialNextTile, map);
                player.setCurrentTile(potentialNextTile);
                currentTile = potentialNextTile;
            }
        }
    }
}
