import com.exercise.treasuremap.Movement;
import com.exercise.treasuremap.ReadInputFile;

public class Main {
    public static void main(String[] args) {
        ReadInputFile readInputFile = new ReadInputFile();
        readInputFile.readFile("src/test/resources/treasure_map_input.txt");
        Movement movement = new Movement();
        movement.startGame(readInputFile.getPlayer(), readInputFile.getMap());
        System.out.println(readInputFile.getPlayer().getCurrentTile().getPosY());
        System.out.println("tr" + readInputFile.getMap().getTileList().get(3).get(1).getNbrOfTreasure());

    }
}