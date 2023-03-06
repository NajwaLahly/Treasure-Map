package com.exercise.treasuremap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.exercise.treasuremap.Constants.FILE_SEPARATOR;
import static com.exercise.treasuremap.Constants.LINE_BREAK;

/**
 * Class used to extract the game data at the end and export it into a text output file.
 */
public class OutputFileWriter {
    /**
     * Starts the process of writing the game data to the output file
     * @param gameData Game data being written
     * @param filePath Path to the output file
     */
    public void writeToOutputFile(GameData gameData, String filePath) {
        createFileIfNotExists(filePath);
        writeData(gameData, filePath);
    }

    /**
     * Create an empty if it does not exist on the path, otherwise it overrides it.
     * @param filePath Path to the output file
     */
    private static void createFileIfNotExists(String filePath) {
        try {
            File file = new File(filePath);
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes game data to an output file
     * @param gameData Game data being written
     * @param filePath Path to the output file
     */
    private void writeData(GameData gameData, String filePath) {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write("C" + FILE_SEPARATOR + gameData.getMapWidth() + FILE_SEPARATOR + gameData.getMapHeight()
                    + LINE_BREAK);
            ArrayList<int[]> mountains = gameData.getMountains();
            for (int[] mountain : mountains) {
                fw.write("M" + FILE_SEPARATOR + mountain[0] + FILE_SEPARATOR + mountain[1]
                        + LINE_BREAK);
            }
            ArrayList<int[]> treasures = gameData.getTreasures();
            for (int[] treasure : treasures) {
                if (treasure[2] > 0)
                    fw.write("T" + FILE_SEPARATOR + treasure[0] + FILE_SEPARATOR + treasure[1] +
                            FILE_SEPARATOR + treasure[2] + LINE_BREAK);
            }
            fw.write("A" + FILE_SEPARATOR + gameData.getPlayerName() + FILE_SEPARATOR + gameData.getPlayerPosX() +
                    FILE_SEPARATOR + gameData.getPlayerPosY() + FILE_SEPARATOR + gameData.getOrientation() + FILE_SEPARATOR +
                    gameData.getNbCollectedTreasures());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
