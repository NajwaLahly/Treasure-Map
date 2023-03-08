package com.exercise.treasuremap;

public class Application {
    public static void main(String[] args) {
        InputFileReader inputFileReader = new InputFileReader();
        GameData gameData = inputFileReader.readFile("src/main/resources/treasure_map_input.txt");
        Game game = new Game(gameData);
        game.start();
        game.updateGameData(gameData);
        OutputFileWriter outputFileWriter = new OutputFileWriter();
        outputFileWriter.writeToOutputFile(gameData, "src/main/resources/treasure_map_output.txt");
    }
}