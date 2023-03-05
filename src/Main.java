import com.exercise.treasuremap.Game;
import com.exercise.treasuremap.InputData;
import com.exercise.treasuremap.InputFileReader;

public class Main {
    public static void main(String[] args) {
        InputFileReader inputFileReader = new InputFileReader();
        InputData inputData = inputFileReader.readFile("src/test/resources/treasure_map_input.txt");
        Game game = new Game(inputData);
        game.start();
        System.out.println(game.getPlayer().getCurrentTile().getPosX());
        System.out.println(game.getPlayer().getCurrentTile().getPosY());
        System.out.println(game.getMap().getTileFromPos(0, 3).getNbTreasures());
        System.out.println(game.getMap().getTileFromPos(1, 3).getNbTreasures());

    }
}