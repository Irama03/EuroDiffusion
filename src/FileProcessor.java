import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileProcessor {

    public static ArrayList<int[][]> readMatrixesFromFile(String fileName) {
        try{
            BufferedReader rd = new BufferedReader(new FileReader(fileName));
            String quantity = rd.readLine();
            int testCasesQuantity = Integer.parseInt(quantity);
            ArrayList<int[][]> boards = new ArrayList<>(testCasesQuantity);
            for (int i = 0; i < testCasesQuantity; i++) {
                int[][] board = new int[Constants.BOARD_DIMENSION][Constants.BOARD_DIMENSION];
                for (int j = 0; j < Constants.BOARD_DIMENSION; j++) {
                    board[j] = Utils.getIntArray(rd.readLine());
                }
                boards.add(board);
                if (i < testCasesQuantity - 1)
                    rd.readLine();
            }
            return boards;
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
