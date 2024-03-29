public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {

        int[][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        printBoard(board);

        if (solveBoard(board)) {
            System.out.println("Solved successfully!");
        }
        else {
            System.out.println("Unsolvable board :(");
        }

        printBoard(board);

    }


    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }


    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;

                            if (solveBoard(board)) {
                                return true;
                            }
                            else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }



}



class Solution {

    private char[][] grid;
    private boolean[][] row;
    private boolean[][] col;
    private boolean[][] box;

    private boolean isValid(int x, int i, int j, int k) {
        return !row[i][x] && !col[j][x] && !box[k][x];
    }

    private boolean dfs(int i, int j) {
        if (j == 9) {
            return dfs(i + 1, 0);
        }
        if (i == 9) {
            return true;
        }
        if (grid[i][j] != '.') {
            return dfs(i, j + 1);
        }
        int k = 3 * (i / 3) + (j / 3);
        for (int x = 0; x < 9; x++) {
            if (isValid(x, i, j, k)) {
                row[i][x] = true;
                col[j][x] = true;
                box[k][x] = true;
                grid[i][j] = (char) ('1' + x);
                if (dfs(i, j + 1)) {
                    return true;
                }
                grid[i][j] = '.';
                box[k][x] = false;
                col[j][x] = false;
                row[i][x] = false;
            }
        }
        return false;
    }

    public void solveSudoku(char[][] board) {
        grid = board;
        row = new boolean[9][9];
        col = new boolean[9][9];
        box = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] != '.') {
                    int x = grid[i][j] - '1';
                    int k = 3 * (i / 3) + (j / 3);
                    row[i][x] = true;
                    col[j][x] = true;
                    box[k][x] = true;
                }
            }
        }
        dfs(0, 0);
    }

}