package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TwoKnights {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int boardDimensions = Integer.parseInt(br.readLine().trim());

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= boardDimensions; i++) {
            builder.append(getPossiblePlacements(i)).append("\n");
        }

        System.out.println(builder);
    }

    /**
     * To describe the algorithm as simply as possible it can be broken down into two steps
     *
     * 1. Calculate each possible combination of knights for a board this would be for a 3x3 board 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 = 36.
     *
     * 2. Calculate each tile that is covered by the other knight. This is done by going through each tile and subtracting each tile covered by the first tile.
     * Tiles behind the knight are already checked and can be ignored.
     *
     * Everything else done by this function is performance optimization.
     */
    public static long getPossiblePlacements(int boardDimension) {
        long possiblePlacementsForBoard = 0;
        int boardFieldCount = boardDimension * boardDimension;

        // The first row 'll be used as reference as the amount of blocked tiles won't change for most of the rows
        long firstRowBlockedTilesSum = 0;
        long allRowsBlockedTileSum = 0;
        // This row does not have any tile left to jump and each tile before has already been concerned by the algorithm
        int ignoreLastRow = 1;
        for (int row = 0; row < boardDimension - ignoreLastRow; row++) {
            // As long as there are two rows in front of the knight the result of calculating a row is always the same and can be stored.
            if (row == 0) {
                // Calculates all tiles that are covered by the other knight to subtract at the end
                for (int col = 0; col < boardDimension; col++) {
                    allRowsBlockedTileSum += getBlockedTilesBeforePlayer(row, col, boardDimension);
                }
                firstRowBlockedTilesSum = allRowsBlockedTileSum;

                // Calculates all possibilities for two knights to be placed on a chess board
                int tilesBeforeKnight = boardFieldCount - 1;
                for (int i = 0; i < boardDimension; i++) {
                    possiblePlacementsForBoard += tilesBeforeKnight;
                    tilesBeforeKnight--;
                }
                // The second row is the only row that is different from the first as the knight has only one row left instead of two to cover tiles
            } else if (row == boardDimension - 2) {
                for (int col = 0; col < boardDimension; col++) {
                    allRowsBlockedTileSum += getBlockedTilesBeforePlayer(row, col, boardDimension);
                }
            } else {
                allRowsBlockedTileSum += firstRowBlockedTilesSum;
            }
        }

        long lastStep = possiblePlacementsForBoard;
        for (int i = 0; i < boardDimension - 1; i++) {
            possiblePlacementsForBoard += (lastStep - boardFieldCount);
            lastStep = (lastStep - boardFieldCount);
        }

        return possiblePlacementsForBoard - allRowsBlockedTileSum;
    }

    /**
     * While playing around I was able to find out that this function consumes massive amount of memory
     * when using an array instead of writing down all combinations like this version does.
     */
    public static int getBlockedTilesBeforePlayer(int row, int col, int boardDimension) {
        int countedBlockedTiles = 0;

        if (isInBoardBounds(row + 1, boardDimension) && isInBoardBounds(col + 2, boardDimension)) {
            countedBlockedTiles++;
        }

        if (isInBoardBounds(row + 2, boardDimension) && isInBoardBounds(col + 1, boardDimension)) {
            countedBlockedTiles++;
        }

        if (isInBoardBounds(row + 2, boardDimension) && isInBoardBounds(col - 1, boardDimension)) {
            countedBlockedTiles++;
        }

        if (isInBoardBounds(row + 1, boardDimension) && isInBoardBounds(col - 2, boardDimension)) {
            countedBlockedTiles++;
        }

        return countedBlockedTiles;
    }

    private static boolean isInBoardBounds(int indexToCheck, int boardDimension) {
        return indexToCheck >= 0 && indexToCheck < boardDimension;
    }
}
