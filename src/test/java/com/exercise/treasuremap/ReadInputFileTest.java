package com.exercise.treasuremap;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.exercise.treasuremap.Orientation.S;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ReadInputFileTest {

    @Test
    void readFile() {
        ReadInputFile readInputFile = new ReadInputFile();
        readInputFile.readFile("src/test/resources/treasure_map_input.txt");
        assertEquals(3, readInputFile.getMap().getWidth());
        assertEquals(4, readInputFile.getMap().getHeight());
        assertEquals(1, readInputFile.getPlayer().getCurrentTile().getPosX());
        assertEquals(1, readInputFile.getPlayer().getCurrentTile().getPosY());
        assertEquals(S, readInputFile.getPlayer().getOrientation());
        assertEquals("AADADAGGA", readInputFile.getPlayer().getDirectionSequence());
        assertEquals(true, readInputFile.getMap().getTileList().get(0).get(1).isMountain());
        assertEquals(2, readInputFile.getMap().getTileList().get(3).get(0).getNbrOfTreasure());
    }

    @Test
    void processLine() {
        ReadInputFile readInputFile = new ReadInputFile();

        //case1:  line sarting with 'C'
        readInputFile.processLine("C - 3 - 4");
        assertEquals(3, readInputFile.getMap().getWidth());
        assertEquals(4, readInputFile.getMap().getHeight());

        //case2:  line sarting with 'M'
        readInputFile.processLine("M - 1 - 0");
        assertEquals(1, readInputFile.getMountainList().get(0).getMountainTile().getPosX());
        assertEquals(0, readInputFile.getMountainList().get(0).getMountainTile().getPosY());

        //case3:  line sarting with 'T'
        readInputFile.processLine("T - 0 - 3 - 2");
        Map.Entry<Treasure, Integer> firstEntry = readInputFile.getTreasureMap().entrySet().iterator().next();
        assertEquals(0, firstEntry.getKey().getTreasureTile().getPosX());
        assertEquals(3, firstEntry.getKey().getTreasureTile().getPosY());
        assertEquals(2, firstEntry.getValue());

        //case4:  line sarting with 'A'
        readInputFile.processLine("A - Lara - 1 - 1 - S - AADADAGGA");
        assertEquals("Lara", readInputFile.getPlayer().getName());
        assertEquals(1, readInputFile.getPlayer().getCurrentTile().getPosX());
        assertEquals(1, readInputFile.getPlayer().getCurrentTile().getPosY());
        assertEquals(S, readInputFile.getPlayer().getOrientation());
        assertEquals("AADADAGGA", readInputFile.getPlayer().getDirectionSequence());
    }

    @Test
    void setMapTileList() {
        TreasureMap map = new TreasureMap(2, 3 );
        ReadInputFile readInputFile = new ReadInputFile();
        readInputFile.setMap(map);
        ArrayList<ArrayList<Tile>> tileList = readInputFile.setMapTileList();
        assertEquals(0, tileList.get(0).get(0).getPosX());
        assertEquals(1, tileList.get(0).get(1).getPosX());
        assertEquals(2, tileList.get(0).get(2).getPosX());

        assertEquals(0, tileList.get(0).get(0).getPosY());
        assertEquals(0, tileList.get(0).get(1).getPosY());
        assertEquals(0, tileList.get(0).get(2).getPosY());

        assertEquals(0, tileList.get(1).get(0).getPosX());
        assertEquals(1, tileList.get(1).get(1).getPosX());
        assertEquals(2, tileList.get(1).get(2).getPosX());

        assertEquals(1, tileList.get(1).get(0).getPosY());
        assertEquals(1, tileList.get(1).get(1).getPosY());
        assertEquals(1, tileList.get(1).get(2).getPosY());

        assertEquals(0, tileList.get(2).get(0).getPosX());
        assertEquals(1, tileList.get(2).get(1).getPosX());
        assertEquals(2, tileList.get(2).get(2).getPosX());

        assertEquals(2, tileList.get(2).get(0).getPosY());
        assertEquals(2, tileList.get(2).get(1).getPosY());
        assertEquals(2, tileList.get(2).get(2).getPosY());

        assertEquals(0, tileList.get(3).get(0).getPosX());
        assertEquals(1, tileList.get(3).get(1).getPosX());
        assertEquals(2, tileList.get(3).get(2).getPosX());

        assertEquals(3, tileList.get(3).get(0).getPosY());
        assertEquals(3, tileList.get(3).get(1).getPosY());
        assertEquals(3, tileList.get(3).get(2).getPosY());

    }

    @Test
    void setMountainInTiles() {
        TreasureMap map = new TreasureMap(2, 3 );
        ReadInputFile readInputFile = new ReadInputFile();
        readInputFile.setMap(map);
        ArrayList<ArrayList<Tile>> tileList = readInputFile.setMapTileList();
        Mountain mountain = new Mountain(new Tile(0,3));
        readInputFile.getMountainList().add(mountain);
        readInputFile.setMountainInTiles(tileList);
        assertEquals(true, tileList.get(3).get(0).isMountain());
        assertEquals(false, tileList.get(2).get(1).isMountain());
    }

    @Test
    void setTreasuresInTiles(){
        TreasureMap map = new TreasureMap(2, 3 );
        ReadInputFile readInputFile = new ReadInputFile();
        readInputFile.setMap(map);
        ArrayList<ArrayList<Tile>> tileList = readInputFile.setMapTileList();
        Map<Treasure,Integer> treasureMap = new HashMap<>();
        Treasure treasure = new Treasure(new Tile(0,1));
        treasureMap.put(treasure, 2);
        readInputFile.setTreasureMap(treasureMap);
        readInputFile.setTreasuresInTiles(tileList);
        assertEquals(2, tileList.get(1).get(0).getNbrOfTreasure());
        assertEquals(0, tileList.get(2).get(1).getNbrOfTreasure());
    }


}