import com.exercise.treasuremap.Game;
import com.exercise.treasuremap.GameData;
import com.exercise.treasuremap.InputFileReader;
import com.exercise.treasuremap.OutputFileWriter;

public class Main {
    public static void main(String[] args) {
        InputFileReader inputFileReader = new InputFileReader();
        GameData gameData = inputFileReader.readFile("src/test/resources/treasure_map_input.txt");
        Game game = new Game(gameData);
        game.start();
        game.updateGameData(gameData);
        OutputFileWriter outputFileWriter = new OutputFileWriter();
        outputFileWriter.writeToOutputFile(gameData, "src/main/resources/treasure_map_output.txt");
    }
}