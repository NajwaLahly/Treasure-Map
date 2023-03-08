package com.exercise.treasuremap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            fw.write("C" + FILE_SEPARATOR + gameData.getMap().getWidth() + FILE_SEPARATOR + gameData.getMap().getHeight()
                    + LINE_BREAK);
            ArrayList<Mountain> mountains = gameData.getMountains();
            for (Mountain mountain : mountains) {
                fw.write("M" + FILE_SEPARATOR + mountain.getPosX() + FILE_SEPARATOR + mountain.getPosY()
                        + LINE_BREAK);
            }
            ArrayList<Treasure> treasures = gameData.getTreasures();
            for (Treasure treasure : treasures) {
                if (treasure.getNbTreasures() > 0)
                    fw.write("T" + FILE_SEPARATOR + treasure.getPosX() + FILE_SEPARATOR + treasure.getPosY() +
                            FILE_SEPARATOR + treasure.getNbTreasures() + LINE_BREAK);
            }
            List<Player> players = gameData.getPlayers();
            for (Player player: players) {
                fw.write("A" + FILE_SEPARATOR + player.getName() +
                        FILE_SEPARATOR + player.getCurrentTile().getPosX() +
                        FILE_SEPARATOR + player.getCurrentTile().getPosY() +
                        FILE_SEPARATOR + player.getOrientation() + FILE_SEPARATOR +
                        player.getNbCollectedTreasures() + LINE_BREAK);
            };


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
