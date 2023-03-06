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
                    data.setMapWidth(Integer.parseInt((splitLine[1])));
                    data.setMapHeight(Integer.parseInt(splitLine[2]));
                    break;
                case "M":
                    int[] mountainPos = new int[]{Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2])};
                    data.getMountains().add(mountainPos);
                    break;
                case "T":
                    int[] treasure = new int[]{Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[3])};
                    data.getTreasures().add(treasure);
                    break;
                case "A":
                    data.setPlayerName(splitLine[1]);
                    data.setPlayerPosX(Integer.parseInt(splitLine[2]));
                    data.setPlayerPosY(Integer.parseInt(splitLine[3]));
                    data.setOrientation(splitLine[4]);
                    data.setDirectionSequence(splitLine[5]);
                    break;
            }
        }
    }
}
