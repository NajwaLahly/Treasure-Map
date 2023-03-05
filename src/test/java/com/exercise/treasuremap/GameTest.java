package com.exercise.treasuremap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.exercise.treasuremap.Orientation.*;

class GameTest {

    @Test
    void initGame() {
        InputFileReader inputFileReader = new InputFileReader();
        InputData inputData = inputFileReader.readFile("src/test/resources/treasure_map_input.txt");
        Game game = new Game(inputData);
        Player player = game.getPlayer();
        TreasureMap treasureMap = game.getMap();
        Assertions.assertEquals("Lara", player.getName());
        Assertions.assertEquals(1, player.getCurrentTile().getPosX());
        Assertions.assertEquals(1, player.getCurrentTile().getPosY());
        Assertions.assertEquals(S, player.getOrientation());
        Assertions.assertEquals("AADADAGGA", player.getDirectionSequence());
        Assertions.assertEquals(3, treasureMap.getWidth());
        Assertions.assertEquals(4, treasureMap.getHeight());
        Assertions.assertTrue(treasureMap.getTiles()[0][1].isMountain());
        Assertions.assertTrue(treasureMap.getTiles()[1][2].isMountain());
        Assertions.assertEquals(2, treasureMap.getTiles()[3][0].getNbTreasures());
        Assertions.assertEquals(3, treasureMap.getTiles()[3][1].getNbTreasures());
    }

    @Test
    void getNextPos() {
        InputFileReader inputFileReader = new InputFileReader();
        InputData inputData = inputFileReader.readFile("src/test/resources/treasure_map_input.txt");
        Game game = new Game(inputData);
        Player player = game.getPlayer();

        // Player in pos (1,1) and is oriented to south
        int[] nextTile = game.getNextPos();
        Assertions.assertEquals(1, nextTile[0]);
        Assertions.assertEquals(2, nextTile[1]);

        // Player in pos (1,1) and is oriented to North
        player.setOrientation(N);
        nextTile = game.getNextPos();
        Assertions.assertEquals(1, nextTile[0]);
        Assertions.assertEquals(0, nextTile[1]);

        // Player in pos (1,1) and is oriented to East
        player.setOrientation(E);
        nextTile = game.getNextPos();
        Assertions.assertEquals(2, nextTile[0]);
        Assertions.assertEquals(1, nextTile[1]);

        // Player in pos (1,1) and is oriented to West
        player.setOrientation(O);
        nextTile = game.getNextPos();
        Assertions.assertEquals(0, nextTile[0]);
        Assertions.assertEquals(1, nextTile[1]);
    }

    @Test
    void isOutOfMap() {
        InputFileReader inputFileReader = new InputFileReader();
        InputData inputData = inputFileReader.readFile("src/test/resources/treasure_map_input.txt");
        Game game = new Game(inputData);
        // the position (0,3) is in map of size (3,4)
        Assertions.assertFalse(game.isOutOfMap(new int[]{0, 3}));
        // the position (3,3) is out of map of size (3,4)
        Assertions.assertTrue(game.isOutOfMap(new int[]{3, 3}));
    }

    @Test
    void canMove() {
        InputFileReader inputFileReader = new InputFileReader();
        InputData inputData = inputFileReader.readFile("src/test/resources/treasure_map_input.txt");
        Game game = new Game(inputData);
        // Player can move to position (0,3) : it's in the map, and it's not a mountain
        Assertions.assertTrue(game.canMove(new int[]{0, 3}));
        // Player can't move to position (2,1) : it's a mountain
        Assertions.assertFalse(game.canMove(new int[]{2, 1}));
        // Player can't move to position (3,3) : it's out of the map
        Assertions.assertFalse(game.canMove(new int[]{3, 3}));
    }

    @Test
    void updateTreasuresInTile() {
        InputFileReader inputFileReader = new InputFileReader();
        InputData inputData = inputFileReader.readFile("src/test/resources/treasure_map_input.txt");
        Game game = new Game(inputData);
        Tile treasureTile = game.getMap().getTileFromPos(0,3);
        game.updateTreasuresInTile(treasureTile);
        // Nbre of treasures in (0,3) are initially 2
        Assertions.assertEquals(1, treasureTile.getNbTreasures());
    }
}