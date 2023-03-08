package com.exercise.treasuremap;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreasureMapTest {

    @Test
    void setTreasures() {
        TreasureMap map = new TreasureMap(2, 3);
        ArrayList<Treasure> treasures = new ArrayList<>();
        Treasure treasure = new Treasure(0, 1, 1);
        treasures.add(treasure);
        map.setTreasures(treasures);
        // Test tile in pos (0,1) with one treasure
        assertEquals(1, map.getTileFromPos(0, 1).getTreasure().getNbTreasures());
        // Test tile in pos (1,2) with no treasure
        assertEquals(0, map.getTileFromPos(1, 2).getTreasure().getNbTreasures());
    }

    @Test
    void setMountains() {
        TreasureMap map = new TreasureMap(2, 3);
        ArrayList<Mountain> mountains = new ArrayList<Mountain>();
        Mountain mountainPos = new Mountain(0, 1);
        mountains.add(mountainPos);
        map.setMountains(mountains);
        // Test tile in pos (0,1) with a mountain
        assertTrue(map.getTileFromPos(0, 1).isMountain());
        // Test tile in pos (1,2) with no mountain
        assertFalse(map.getTileFromPos(1, 2).isMountain());
    }

    @Test
    void setPlayers() {
        TreasureMap map = new TreasureMap(2, 3);
        List<Player> players = new ArrayList<Player>();
        Player player = new Player("Lara",
                new Tile(0, 1),
                Orientation.O,
                "AAD");
        players.add(player);
        map.setPlayers(players);
        // Test tile in pos (0,1) with a player
        assertTrue(map.getTileFromPos(0, 1).isOccupied());
        // Test tile in pos (1,2) with no player
        assertFalse(map.getTileFromPos(1, 2).isOccupied());
    }
}