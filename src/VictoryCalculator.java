import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class VictoryCalculator {

    public void calculateVictoryForBoards(ArrayList<int[][]> boards) {
        for (int[][] board: boards) {
            calculateVictoryForBoard(board);
            System.out.println();
        }
    }

    public void calculateVictoryForBoard(int[][] board) {
        HashMap<Point, LinkedList<RowDirection>> processedPoints = new HashMap<>();
        int victory = Constants.NO_COLOUR;
        for (int i = 0; i < Constants.BOARD_DIMENSION; i++) {
            for (int j = 0; j < Constants.BOARD_DIMENSION; j++) {
                Point currentPoint = new Point(j, i, board[i][j]);
                if (currentPoint.getColour() != Constants.NO_COLOUR) {
                    victory = processDirectionForPoint(RowDirection.ROW, currentPoint, board, processedPoints);
                    if (noVictoryYet(victory))
                        victory = processDirectionForPoint(RowDirection.COLUMN, currentPoint, board, processedPoints);
                    else return;
                    if (noVictoryYet(victory))
                        victory = processDirectionForPoint(RowDirection.RIGHT_DIAGONAL, currentPoint, board, processedPoints);
                    else return;
                    if (noVictoryYet(victory))
                        victory = processDirectionForPoint(RowDirection.LEFT_DIAGONAL, currentPoint, board, processedPoints);
                    else return;
                    if (!noVictoryYet(victory))
                        return;
                }
            }
        }
        System.out.println(victory);
    }

    private int processDirectionForPoint(RowDirection direction, Point currentPoint, int[][] board, HashMap<Point, LinkedList<RowDirection>> processedPoints) {
        if (!processedPoints.containsKey(currentPoint) || !processedPoints.get(currentPoint).contains(direction)) {
            LinkedList<Point> row = getRowForDirection(direction, currentPoint, board);
            if (row.size() == Constants.WIN_STONE_QUANTITY) {
                printVictory(currentPoint, currentPoint.getColour());
                return currentPoint.getColour();
            }
            else {
                for (Point p: row) {
                    if (!processedPoints.containsKey(p))
                        processedPoints.put(p, new LinkedList<>());
                    processedPoints.get(p).add(direction);
                }
            }
        }
        return Constants.NO_COLOUR;
    }

    private LinkedList<Point> getRowForDirection(RowDirection direction, Point p, int[][] board) {
        LinkedList<Point> row = new LinkedList<>();
        row.add(p);
        switch (direction) {
            case ROW:
                processRightRow(p, board, row);
                break;
            case COLUMN:
                processLowerColumn(p, board, row);
                break;
            case RIGHT_DIAGONAL:
                processRightLowerDiagonal(p, board, row);
                break;
            case LEFT_DIAGONAL:
                processLeftLowerDiagonal(p, board, row);
                break;
            default:
                throw new IllegalArgumentException("Unknown row direction");
        }
        return row;
    }

    private void processRightRow(Point p, int[][] board, LinkedList<Point> row) {
        for (int i = p.getX() + 1; i < Constants.BOARD_DIMENSION; i++) {
            if (board[p.getY()][i] == p.getColour())
                row.add(new Point(i, p.getY(), p.getColour()));
            else break;
        }
    }

    private void processLowerColumn(Point p, int[][] board, LinkedList<Point> column) {
        for (int i = p.getY() + 1; i < Constants.BOARD_DIMENSION; i++) {
            if (board[i][p.getX()] == p.getColour())
                column.add(new Point(p.getX(), i, p.getColour()));
            else break;
        }
    }

    private void processRightLowerDiagonal(Point p, int[][] board, LinkedList<Point> diagonal) {
        int y = p.getY();
        for (int i = p.getX() + 1; i < Constants.BOARD_DIMENSION; i++) {
            y++;
            if (y < Constants.BOARD_DIMENSION && board[y][i] == p.getColour())
                diagonal.add(new Point(i, y, p.getColour()));
            else break;
        }
    }

    private void processLeftLowerDiagonal(Point p, int[][] board, LinkedList<Point> diagonal) {
        int y = p.getY();
        for (int i = p.getX() - 1; i >= 0; i--) {
            y++;
            if (y < Constants.BOARD_DIMENSION && board[y][i] == p.getColour())
                diagonal.add(new Point(i, y, p.getColour()));
            else break;
        }
    }

    private boolean noVictoryYet(int colour) {
        return colour == Constants.NO_COLOUR;
    }

    private void printVictory(Point p, int colour) {
        System.out.println(colour);
        System.out.println(p);
    }

}
