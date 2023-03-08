package com.exercise.treasuremap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.exercise.treasuremap.Orientation.*;

class GameTest {

    @Test
    void initGame() {
        InputFileReader inputFileReader = new InputFileReader();
        GameData gameData = inputFileReader.readFile("src/test/resources/treasure_map_input.txt");
        Game game = new Game(gameData);
        List<Player> players = game.getPlayers();
        Player firstPlayer = players.get(0);

        TreasureMap treasureMap = game.getMap();

        Assertions.assertEquals("Lara", firstPlayer.getName());
        Assertions.assertEquals(1, firstPlayer.getCurrentTile().getPosX());
        Assertions.assertEquals(1, firstPlayer.getCurrentTile().getPosY());
        Assertions.assertEquals(S, firstPlayer.getOrientation());
        Assertions.assertEquals("AADADAGGA", firstPlayer.getDirectionSequence());
        Assertions.assertEquals(3, treasureMap.getWidth());
        Assertions.assertEquals(4, treasureMap.getHeight());
        Assertions.assertTrue(treasureMap.getTiles()[0][1].isMountain());
        Assertions.assertTrue(treasureMap.getTiles()[1][2].isMountain());
        Assertions.assertEquals(2, treasureMap.getTiles()[3][0].getTreasure().getNbTreasures());
        Assertions.assertEquals(3, treasureMap.getTiles()[3][1].getTreasure().getNbTreasures());
    }

    @Test
    void getNextPos() {
        InputFileReader inputFileReader = new InputFileReader();
        GameData gameData = inputFileReader.readFile("src/test/resources/treasure_map_input.txt");
        Game game = new Game(gameData);
        List<Player> players = game.getPlayers();
        Player firstPlayer = players.get(0);

        // Player in pos (1,1) and is oriented to south
        int[] nextTile = firstPlayer.getNextPos();
        Assertions.assertEquals(1, nextTile[0]);
        Assertions.assertEquals(2, nextTile[1]);

        // Player in pos (1,1) and is oriented to North
        firstPlayer.setOrientation(N);
        nextTile = firstPlayer.getNextPos();
        Assertions.assertEquals(1, nextTile[0]);
        Assertions.assertEquals(0, nextTile[1]);

        // Player in pos (1,1) and is oriented to East
        firstPlayer.setOrientation(E);
        nextTile = firstPlayer.getNextPos();
        Assertions.assertEquals(2, nextTile[0]);
        Assertions.assertEquals(1, nextTile[1]);

        // Player in pos (1,1) and is oriented to West
        firstPlayer.setOrientation(O);
        nextTile = firstPlayer.getNextPos();
        Assertions.assertEquals(0, nextTile[0]);
        Assertions.assertEquals(1, nextTile[1]);
    }

    @Test
    void isOutOfMap() {
        InputFileReader inputFileReader = new InputFileReader();
        GameData gameData = inputFileReader.readFile("src/test/resources/treasure_map_input.txt");

        Game game = new Game(gameData);

        // the position (0,3) is in map of size (3,4)
        Assertions.assertFalse(game.isOutOfMap(new int[]{0, 3}));
        // the position (3,3) is out of map of size (3,4)
        Assertions.assertTrue(game.isOutOfMap(new int[]{3, 3}));
    }

    @Test
    void canMoveCase1Player() {
        InputFileReader inputFileReader = new InputFileReader();
        GameData gameData = inputFileReader.readFile("src/test/resources/treasure_map_input.txt");

        Game game = new Game(gameData);

        // Player can move to position (0,3) : it's in the map, and it's not a mountain
        Assertions.assertTrue(game.canMove(new int[]{0, 3}));
        // Player can't move to position (2,1) : it's a mountain
        Assertions.assertFalse(game.canMove(new int[]{2, 1}));
        // Player can't move to position (3,3) : it's out of the map
        Assertions.assertFalse(game.canMove(new int[]{3, 3}));
    }

    @Test
    void canMoveCase2Players() {
        InputFileReader inputFileReader = new InputFileReader();
        GameData gameData = inputFileReader.readFile("src/test/resources/treasure_map_input_2players.txt");

        Game game = new Game(gameData);

        // Player Lara can move to position (0,3) : it's in the map, and it's not a mountain and not occupied
        Assertions.assertTrue(game.canMove(new int[]{0, 3}));
        // Player can't move to position (2,1) : it's a mountain
        Assertions.assertFalse(game.canMove(new int[]{2, 1}));
        // Player can't move to position (3,3) : it's occupied by other player
        Assertions.assertFalse(game.canMove(new int[]{1, 1}));
    }

    @Test
    void updateTreasures() {
        InputFileReader inputFileReader = new InputFileReader();
        GameData gameData = inputFileReader.readFile("src/test/resources/treasure_map_input.txt");
        Game game = new Game(gameData);
        List<Player> players = game.getPlayers();
        Player firstPlayer = players.get(0);
        Tile treasureTile = game.getMap().getTileFromPos(0, 3);
        firstPlayer.setCurrentTile(treasureTile);

        game.updateTreasures(firstPlayer);

        // Nb of treasures in (0,3) are initially 2
        Assertions.assertEquals(1, game.getMap().getTileFromPos(0, 3).getTreasure().getNbTreasures());
        // Nb of treasures collected by the player is this tile
        Assertions.assertEquals(1, firstPlayer.getNbCollectedTreasures());
    }

    @Test
    void updateGameDataCase1Player() {
        InputFileReader inputFileReader = new InputFileReader();
        GameData gameData = inputFileReader.readFile("src/test/resources/treasure_map_input.txt");
        Game game = new Game(gameData);
        List<Player> players = game.getPlayers();
        Player firstPlayer = players.get(0);
        game.start();

        game.updateGameData(gameData);

        Assertions.assertEquals(0, firstPlayer.getCurrentTile().getPosX());
        Assertions.assertEquals(3, firstPlayer.getCurrentTile().getPosY());
        Assertions.assertEquals(3, firstPlayer.getNbCollectedTreasures());
        Assertions.assertEquals(S, firstPlayer.getOrientation());
        Assertions.assertEquals(0, gameData.getMap().getTileFromPos(0, 3).getTreasure().getNbTreasures());
        Assertions.assertEquals(2, gameData.getMap().getTileFromPos(1, 3).getTreasure().getNbTreasures());
    }

    @Test
    void updateGameDataCase2Players() {
        InputFileReader inputFileReader = new InputFileReader();
        GameData gameData = inputFileReader.readFile("src/test/resources/treasure_map_input_2players.txt");
        Game game = new Game(gameData);
        List<Player> players = game.getPlayers();
        Player firstPlayer = players.get(0);
        Player secondPlayer = players.get(1);
        game.start();

        game.updateGameData(gameData);

        Assertions.assertEquals(1, firstPlayer.getCurrentTile().getPosX());
        Assertions.assertEquals(1, firstPlayer.getCurrentTile().getPosY());
        Assertions.assertEquals(1, firstPlayer.getNbCollectedTreasures());
        Assertions.assertEquals(E, firstPlayer.getOrientation());
        Assertions.assertEquals(1, secondPlayer.getCurrentTile().getPosX());
        Assertions.assertEquals(3, secondPlayer.getCurrentTile().getPosY());
        Assertions.assertEquals(0, secondPlayer.getNbCollectedTreasures());
        Assertions.assertEquals(S, secondPlayer.getOrientation());
        Assertions.assertEquals(1, gameData.getMap().getTileFromPos(0, 1).getTreasure().getNbTreasures());
    }
}