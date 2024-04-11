import java.util.ArrayList;

public class Tester {

    public static void main(String[] args) {
        String fileName = "src/testCases/test.txt";
        ArrayList<int[][]> boards = FileProcessor.readMatrixesFromFile(fileName);
        if (boards != null) {
            VictoryCalculator victoryCalculator = new VictoryCalculator();
            victoryCalculator.calculateVictoryForBoards(boards);
        }
    }
}
