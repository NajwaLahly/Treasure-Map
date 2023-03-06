package com.exercise.treasuremap;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputFileReaderTest {

    @Test
    void readFile() {
        InputFileReader inputFileReader = new InputFileReader();
        GameData data = inputFileReader.readFile("src/test/resources/treasure_map_input.txt");
        assertEquals(3, data.getMapWidth());
        assertEquals(4, data.getMapHeight());
        assertEquals(1, data.getPlayerPosX());
        assertEquals(1, data.getPlayerPosY());
        assertEquals("S", data.getOrientation());
        assertEquals("AADADAGGA", data.getDirectionSequence());
        assertEquals("Lara", data.getPlayerName());

        assertEquals(2, data.getMountains().size());
        assertEquals(1, data.getMountains().get(0)[0]);
        assertEquals(0, data.getMountains().get(0)[1]);
        assertEquals(2, data.getMountains().get(1)[0]);
        assertEquals(1, data.getMountains().get(1)[1]);

        assertEquals(2, data.getTreasures().size());
        assertEquals(0, data.getTreasures().get(0)[0]);
        assertEquals(3, data.getTreasures().get(0)[1]);
        assertEquals(2, data.getTreasures().get(0)[2]);
        assertEquals(1, data.getTreasures().get(1)[0]);
        assertEquals(3, data.getTreasures().get(1)[1]);
        assertEquals(3, data.getTreasures().get(1)[2]);
    }

    @Test
    void processLine() {
        InputFileReader inputFileReader = new InputFileReader();
        GameData data = new GameData();

        //line starting with 'C'
        inputFileReader.processLine("C - 3 - 4", data);
        assertEquals(3, data.getMapWidth());
        assertEquals(4, data.getMapHeight());

        //line starting with 'M'
        inputFileReader.processLine("M - 1 - 0", data);
        assertEquals(1, data.getMountains().get(0)[0]);
        assertEquals(0, data.getMountains().get(0)[1]);

        //line starting with 'T'
        inputFileReader.processLine("T - 0 - 3 - 2", data);
        assertEquals(0, data.getTreasures().get(0)[0]);
        assertEquals(3, data.getTreasures().get(0)[1]);
        assertEquals(2, data.getTreasures().get(0)[2]);


        //line starting with 'A'
        inputFileReader.processLine("A - Lara - 1 - 1 - S - AADADAGGA", data);
        assertEquals("Lara", data.getPlayerName());
        assertEquals(1, data.getPlayerPosX());
        assertEquals(1, data.getPlayerPosY());
        assertEquals("S", data.getOrientation());
        assertEquals("AADADAGGA", data.getDirectionSequence());
    }
}