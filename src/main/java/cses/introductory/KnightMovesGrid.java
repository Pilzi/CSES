package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class KnightMovesGrid {
    public static final int BOARD_EMPTY_INDICATOR = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 4 <= boardDimensions <= 1000
        int boardDimensions = Integer.parseInt(br.readLine().trim());
        StringBuilder res = new StringBuilder();

        long totalTiles = (long) boardDimensions * boardDimensions;
        long tilesAlreadyFilled = 1L; // 1 is the initial value as 0 is already at position (0,0)
        Map<Integer, List<Coord>> coordMap = new HashMap<>();
        int[][] board = new int[boardDimensions][boardDimensions];

        for (int[] ints : board) {
            Arrays.fill(ints, BOARD_EMPTY_INDICATOR);
        }

        coordMap.put(0, new ArrayList<>());
        coordMap.get(0).add(new Coord(0, 0));
        board[0][0] = 0;

        int i = 0;
        while (tilesAlreadyFilled < totalTiles) {
            List<Coord> coords = coordMap.get(i);

            for (Coord coord : coords) {
                List<Coord> possibleJumps = getPossibleJumps(board, coord);

                for (Coord cord : possibleJumps) {
                    // Jumps can be duplicated this if prevents this behavior
                    if (board[cord.x][cord.y] == BOARD_EMPTY_INDICATOR) {
                        board[cord.x][cord.y] = i + 1;
                        coordMap.computeIfAbsent(i + 1, x -> new ArrayList<>()).add(cord);
                        tilesAlreadyFilled++;
                    }
                }
            }
            i++;
        }

        for (int[] row : board) {
            for (int col : row) {
                res.append(col).append(" ");
            }
            res.append("\n");
        }

        System.out.println(res);
    }

    private static List<Coord> getPossibleJumps(int[][] board, Coord coord) {
        List<Coord> possibleJumps = new ArrayList<>();

        if (isInBoardBoundsAndEmpty(board, coord.x + 2, coord.y + 1)) {
            possibleJumps.add(new Coord(coord.x + 2, coord.y + 1));
        }
        if (isInBoardBoundsAndEmpty(board, coord.x + 2, coord.y - 1)) {
            possibleJumps.add(new Coord(coord.x + 2, coord.y - 1));
        }
        if (isInBoardBoundsAndEmpty(board, coord.x - 2, coord.y + 1)) {
            possibleJumps.add(new Coord(coord.x - 2, coord.y + 1));
        }
        if (isInBoardBoundsAndEmpty(board, coord.x - 2, coord.y - 1)) {
            possibleJumps.add(new Coord(coord.x - 2, coord.y - 1));
        }

        if (isInBoardBoundsAndEmpty(board, coord.x - 1, coord.y + 2)) {
            possibleJumps.add(new Coord(coord.x - 1, coord.y + 2));
        }
        if (isInBoardBoundsAndEmpty(board, coord.x + 1, coord.y + 2)) {
            possibleJumps.add(new Coord(coord.x + 1, coord.y + 2));
        }
        if (isInBoardBoundsAndEmpty(board, coord.x - 1, coord.y - 2)) {
            possibleJumps.add(new Coord(coord.x - 1, coord.y - 2));
        }
        if (isInBoardBoundsAndEmpty(board, coord.x + 1, coord.y - 2)) {
            possibleJumps.add(new Coord(coord.x + 1, coord.y - 2));
        }


        return possibleJumps;
    }

    private static boolean isInBoardBoundsAndEmpty(int[][] board, int col, int row) {
        if ((col >= 0 && col <= board.length - 1) && (row >= 0 && row <= board.length - 1)) {
            return board[col][row] == BOARD_EMPTY_INDICATOR;
        }
        return false;
    }

    public static class Coord {
        private final int x;
        private final int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
