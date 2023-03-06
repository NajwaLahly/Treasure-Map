package com.exercise.treasuremap;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OutputFileWriterTest {

    @Test
    void writeToOutputFile() throws IOException {
        GameData data = new GameData();
        data.setMapHeight(3);
        data.setMapWidth(4);
        ArrayList<int[]> treasures = new ArrayList<>();
        int[] treasureData = new int[]{1, 1, 2};
        treasures.add(treasureData);
        data.setTreasures(treasures);
        ArrayList<int[]> mountains = new ArrayList<>();
        mountains.add(new int[]{2, 0});
        data.setMountains(mountains);
        data.setOrientation(Orientation.O.toString());
        data.setPlayerName("Lara");
        data.setPlayerPosX(1);
        data.setPlayerPosY(2);
        data.setDirectionSequence("AADGA");
        data.setNbCollectedTreasures(1);

        OutputFileWriter fileWriter = new OutputFileWriter();
        fileWriter.writeToOutputFile(data, "src/test/resources/treasure_map_output.txt");

        byte[] encoded = Files.readAllBytes(Paths.get("src/test/resources/treasure_map_output.txt"));
        // Convert the byte array to a string
        String fileContent = new String(encoded);
        
        assertEquals("C - 4 - 3\nM - 2 - 0\nT - 1 - 1 - 2\nA - Lara - 1 - 2 - O - 1", fileContent);
    }
}