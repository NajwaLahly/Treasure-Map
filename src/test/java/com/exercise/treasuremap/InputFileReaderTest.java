package com.exercise.treasuremap;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputFileReaderTest {

    @Test
    void readFile() {
        InputFileReader inputFileReader = new InputFileReader();
        GameData data = inputFileReader.readFile("src/test/resources/treasure_map_input.txt");
        assertEquals(3, data.getMap().getWidth());
        assertEquals(4, data.getMap().getHeight());
        assertEquals(1, data.getPlayers().get(0).getCurrentTile().getPosX());
        assertEquals(1, data.getPlayers().get(0).getCurrentTile().getPosY());
        assertEquals(Orientation.S, data.getPlayers().get(0).getOrientation());
        assertEquals("AADADAGGA", data.getPlayers().get(0).getDirectionSequence());
        assertEquals("Lara", data.getPlayers().get(0).getName());

        assertEquals(2, data.getMountains().size());
        assertEquals(1, data.getMountains().get(0).getPosX());
        assertEquals(0, data.getMountains().get(0).getPosY());
        assertEquals(2, data.getMountains().get(1).getPosX());
        assertEquals(1, data.getMountains().get(1).getPosY());

        assertEquals(2, data.getTreasures().size());
        assertEquals(0, data.getTreasures().get(0).getPosX());
        assertEquals(3, data.getTreasures().get(0).getPosY());
        assertEquals(2, data.getTreasures().get(0).getNbTreasures());
        assertEquals(1, data.getTreasures().get(1).getPosX());
        assertEquals(3, data.getTreasures().get(1).getPosY());
        assertEquals(3, data.getTreasures().get(1).getNbTreasures());
    }

    @Test
    void processLine() {
        InputFileReader inputFileReader = new InputFileReader();
        GameData data = new GameData();

        //line starting with 'C'
        inputFileReader.processLine("C - 3 - 4", data);
        assertEquals(3, data.getMap().getWidth());
        assertEquals(4, data.getMap().getHeight());

        //line starting with 'M'
        inputFileReader.processLine("M - 1 - 0", data);
        assertEquals(1, data.getMountains().get(0).getPosX());
        assertEquals(0, data.getMountains().get(0).getPosY());

        //line starting with 'T'
        inputFileReader.processLine("T - 0 - 3 - 2", data);
        assertEquals(0, data.getTreasures().get(0).getPosX());
        assertEquals(3, data.getTreasures().get(0).getPosY());
        assertEquals(2, data.getTreasures().get(0).getNbTreasures());


        //line starting with 'A'
        inputFileReader.processLine("A - Lara - 1 - 1 - S - AADADAGGA", data);
        assertEquals(1, data.getPlayers().get(0).getCurrentTile().getPosX());
        assertEquals(1, data.getPlayers().get(0).getCurrentTile().getPosY());
        assertEquals(Orientation.S, data.getPlayers().get(0).getOrientation());
        assertEquals("AADADAGGA", data.getPlayers().get(0).getDirectionSequence());
        assertEquals("Lara", data.getPlayers().get(0).getName());
    }
}