package com.exercise.treasuremap;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OutputFileWriterTest {

    @Test
    void writeToOutputFile1Player() throws IOException {
        GameData data = new GameData();
        TreasureMap map = new TreasureMap(3, 4);
        ArrayList<Treasure> treasures = new ArrayList<>();
        Treasure treasure = new Treasure(1, 3, 2);
        treasures.add(treasure);
        ArrayList<Mountain> mountains = new ArrayList<>();
        Mountain mountain = new Mountain(1, 0);
        Mountain mountain2 = new Mountain(2, 1);
        mountains.add(mountain);
        mountains.add(mountain2);
        data.setMountains(mountains);
        List<Player> players = new ArrayList<>();
        Player player = new Player("Lara",
                new Tile(0, 3),
                Orientation.S,
                "AADGA");
        player.setNbCollectedTreasures(3);
        players.add(player);
        data.setMap(map);
        data.setTreasures(treasures);
        data.setMountains(mountains);
        data.setPlayers(players);

        OutputFileWriter fileWriter = new OutputFileWriter();
        fileWriter.writeToOutputFile(data, "src/test/resources/treasure_map_output.txt");

        byte[] encoded = Files.readAllBytes(Paths.get("src/test/resources/treasure_map_output.txt"));
        // Convert the byte array to a string
        String fileContent = new String(encoded);

        assertEquals("C - 3 - 4\nM - 1 - 0\nM - 2 - 1\nT - 1 - 3 - 2\nA - Lara - 0 - 3 - S - 3\n", fileContent);
    }

    @Test
    void writeToOutputFile2Players() throws IOException {
        GameData data = new GameData();
        TreasureMap map = new TreasureMap(3, 4);
        ArrayList<Treasure> treasures = new ArrayList<>();
        Treasure treasure = new Treasure(0, 1, 2);
        treasures.add(treasure);
        ArrayList<Mountain> mountains = new ArrayList<>();
        Mountain mountain = new Mountain(2, 1);
        mountains.add(mountain);
        data.setMountains(mountains);
        List<Player> players = new ArrayList<>();
        Player player1 = new Player("Lara",
                new Tile(0, 0),
                Orientation.E,
                "AGAA");
        Player player2 = new Player("Xena",
                new Tile(1, 1),
                Orientation.S,
                "AGAAAA");
        player1.setNbCollectedTreasures(1);
        player2.setNbCollectedTreasures(0);
        players.add(player1);
        players.add(player2);
        data.setMap(map);
        data.setTreasures(treasures);
        data.setMountains(mountains);
        data.setPlayers(players);

        OutputFileWriter fileWriter = new OutputFileWriter();
        fileWriter.writeToOutputFile(data, "src/test/resources/treasure_map_output_2players.txt");

        byte[] encoded = Files.readAllBytes(Paths.get("src/test/resources/treasure_map_output_2players.txt"));
        // Convert the byte array to a string
        String fileContent = new String(encoded);

        assertEquals("C - 3 - 4\nM - 2 - 1\nT - 0 - 1 - 2\nA - Lara - 0 - 0 - E - 1\nA - Xena - 1 - 1 - S - 0\n", fileContent);
    }
}