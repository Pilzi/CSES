package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ChessboardAndQueens {
    public static final int FREE_TILE = -1;
    public static final int QUEEN = 1;
    public static final List<Coord> alreadyPlacedQueens = new ArrayList<>();
    public static long possibleSolutionsCount = 0L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[8][8];

        for (int i = 0; i < board.length; i++) {
            String[] boardTiles = br.readLine().split("");
            for (int j = 0; j < boardTiles.length; j++) {
                if (boardTiles[j].equals(".")) {
                    board[i][j] = -1;
                } else {
                    board[i][j] = -2;
                }
            }
        }

        solveEightQueens(board, 0, 0);
        System.out.println(possibleSolutionsCount);
    }

    public static void solveEightQueens(int[][] board, int row, int col) {
        if (alreadyPlacedQueens.size() == 8) {
            possibleSolutionsCount++;
            return;
        }

        if (row >= board.length || col >= board.length) {
            return;
        }

        if (board[row][col] == FREE_TILE && !isTileCoveredByQueen(board, row, col)) {
            board[row][col] = QUEEN;
            Coord placedQueen = new Coord(row, col);
            alreadyPlacedQueens.add(placedQueen);
            solveEightQueens(board, row + 1, 0);
            board[row][col] = FREE_TILE;
            alreadyPlacedQueens.remove(placedQueen);
        }
        if (alreadyPlacedQueens.size() != 1 || alreadyPlacedQueens.get(0).col == 0) {
            if (col + 1 < board.length) {
                solveEightQueens(board, row, col + 1);
            } else if (row + 1 < board.length) {
                solveEightQueens(board, row + 1, 0);
            }
        }
    }

    private static boolean isTileCoveredByQueen(int[][] board, int col, int row) {
        for (Coord queen : alreadyPlacedQueens) {

            if (queen.col == col || queen.row == row) {
                return true;
            }

            for (int i = 0; queen.col + i < board.length && queen.row + i < board.length; i++) {
                if (queen.col + i == col && queen.row + i == row) {
                    return true;
                }
            }

            for (int i = 0; queen.col - i > 0 && queen.row - i >= 0; i++) {
                if (queen.col - i == col && queen.row - i == row) {
                    return true;
                }
            }

            for (int i = 0; queen.col + i < board.length && queen.row - i >= 0; i++) {
                if (queen.col + i == col && queen.row - i == row) {
                    return true;
                }
            }

            for (int i = 0; queen.col - i >= 0 && queen.row - i < board.length; i++) {
                if (queen.col - i == col && queen.row + i == row) {
                    return true;
                }
            }
        }

        return false;
    }

    public static class Coord {
        public int col;
        public int row;

        public Coord(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }
}
