package com.exercise.treasuremap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class used to read the input file and convert it into a GameData file.
 */
public class InputFileReader {
    /**
     * Reads the input file and writes data in the GameData file.
     * @param filePath Path to the input file
     * @return Object that represents the game data needed to initialize the game
     */
    public GameData readFile(String filePath) {
        GameData data = new GameData();
        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null)
                processLine(line, data);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return data;
    }

    /**
     * Reads one line in the input file and writes the data extracted in the GameData object
     * @param line Represents one line of the input file
     * @param data Game data being written
     */
    public void processLine(String line, GameData data) {
        if (line.charAt(0) != '#') {
            String[] splitLine = line.split(" - ");
            switch (splitLine[0]) {
                case "C":
                    data.setMap(new TreasureMap(Integer.parseInt((splitLine[1])), Integer.parseInt(splitLine[2])));
                    break;
                case "M":
                    Mountain mountain = new Mountain(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]));
                    data.getMountains().add(mountain);
                    break;
                case "T":
                    Treasure treasure = new Treasure(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3]));
                    data.getTreasures().add(treasure);
                    break;
                case "A":
                    Player player = new Player(splitLine[1],
                            new Tile(Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3])),
                            Orientation.valueOf(splitLine[4]),
                            splitLine[5]);
                    data.getPlayers().add(player);
                    break;
            }
        }
    }
}
