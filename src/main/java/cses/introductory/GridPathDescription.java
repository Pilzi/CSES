package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GridPathDescription {
    public static final int VISITED = -1;
    public static final int NOT_VISITED = 0;
    public static long possibleSolutionsCount = 0L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[9][9];
        String input = br.readLine();

        for (int i = 0; i < board.length; i++) {
            board[0][i] = VISITED;
            board[board.length - 1][i] = VISITED;
            board[i][0] = VISITED;
            board[i][board.length - 1] = VISITED;
        }

        board[1][1] = VISITED;
        gridPathDescription(board, 1, 1, input.toCharArray(), 0);

        System.out.println(possibleSolutionsCount);
    }

    private static void gridPathDescription(int[][] board, int lastRow, int lastCol, char[] input, int inputIndex) {
        if (lastRow == 7 && lastCol == 1) {
            if (inputIndex == 48) {
                possibleSolutionsCount++;
            }
            return;
        }

        if (inputIndex == 48) {
            return;
        }

        char direction = input[inputIndex];
        boolean rightAndLeftVisited = board[lastRow][lastCol - 1] == VISITED && board[lastRow][lastCol + 1] == VISITED;
        boolean topAndBottomVisited = board[lastRow - 1][lastCol] == VISITED && board[lastRow + 1][lastCol] == VISITED;

        if (rightAndLeftVisited && board[lastRow - 1][lastCol] == NOT_VISITED && board[lastRow + 1][lastCol] == NOT_VISITED) {
            return;
        }

        if (topAndBottomVisited && board[lastRow][lastCol - 1] == NOT_VISITED && board[lastRow][lastCol + 1] == NOT_VISITED) {
            return;
        }

        int colR = lastCol + 1;
        if ((direction == 'R' || direction == '?') && !tileAlreadyVisited(board, lastRow, colR)) {
            board[lastRow][colR] = VISITED;
            gridPathDescription(board, lastRow, colR, input, inputIndex + 1);
            board[lastRow][colR] = NOT_VISITED;
        }
        int rowU = lastRow - 1;
        if ((direction == 'U' || direction == '?') && !tileAlreadyVisited(board, rowU, lastCol)) {
            board[rowU][lastCol] = VISITED;
            gridPathDescription(board, rowU, lastCol, input, inputIndex + 1);
            board[rowU][lastCol] = NOT_VISITED;
        }

        int rowD = lastRow + 1;
        if ((direction == 'D' || direction == '?') && !tileAlreadyVisited(board, rowD, lastCol)) {
            board[rowD][lastCol] = VISITED;
            gridPathDescription(board, rowD, lastCol, input, inputIndex + 1);
            board[rowD][lastCol] = NOT_VISITED;
        }
        int colL = lastCol - 1;
        if ((direction == 'L' || direction == '?') && !tileAlreadyVisited(board, lastRow, colL)) {
            board[lastRow][colL] = VISITED;
            gridPathDescription(board, lastRow, colL, input, inputIndex + 1);
            board[lastRow][colL] = NOT_VISITED;
        }
    }

    private static boolean tileAlreadyVisited(int[][] board, int row, int col) {
        return board[row][col] == VISITED;
    }
}
