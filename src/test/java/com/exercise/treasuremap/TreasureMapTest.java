package com.exercise.treasuremap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TreasureMapTest {

    @Test
    void setTreasures() {
        TreasureMap map = new TreasureMap(2, 3 );
        ArrayList<int[]> treasures = new ArrayList<>();
        int[] treasure = new int[]{0,1,1};
        treasures.add(treasure);
        map.setTreasures(treasures);
        // Test tile in pos (0,1) with one treasure
        assertEquals(1, map.getTileFromPos(0,1).getNbTreasures());
        // Test tile in pos (1,2) with no treasure
        assertEquals(0, map.getTileFromPos(1,2).getNbTreasures());
    }

    @Test
    void setMountains() {
        TreasureMap map = new TreasureMap(2, 3 );
        ArrayList<int[]> mountains = new ArrayList<int[]>();
        int[] mountainPos = new int[]{0,1};
        mountains.add(mountainPos);
        map.setMountains(mountains);
        // Test tile in pos (0,1) with a mountain
        assertEquals(true, map.getTileFromPos(0,1).isMountain());
        // Test tile in pos (1,2) with no mountain
        assertEquals(false, map.getTileFromPos(1,2).isMountain());
    }
}