package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class GridColoring1 {
    public static final String A_CHAR = "A";
    public static final String B_CHAR = "B";
    public static final String C_CHAR = "C";
    public static final String D_CHAR = "D";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String numberOfRowsAndColumns = br.readLine();
        int rowLength = Integer.parseInt(numberOfRowsAndColumns.split(" ")[0]);
        int columnLength = Integer.parseInt(numberOfRowsAndColumns.split(" ")[1]);

        String[][] inputBoard = new String[rowLength][columnLength];
        String[][] outputBoard = new String[rowLength][columnLength];

        for (int i = 0; i < rowLength; i++) {
            inputBoard[i] = br.readLine().split("");
        }

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < inputBoard.length; i++) {
            for (int j = 0; j < inputBoard[i].length; j++) {
                String topInput = (i - 1) >= 0 ? inputBoard[i - 1][j] : null;
                String rightInput = (j + 1) < inputBoard[i].length - 1 ? inputBoard[i][j + 1] : null;
                String bottomInput = (i + 1) < inputBoard.length - 1 ? inputBoard[i + 1][j] : null;
                String leftInput = (j - 1) >= 0 ? inputBoard[i][j - 1] : null;

                String topOutput = (i - 1) >= 0 ? outputBoard[i - 1][j] : null;
                String leftOutput = (j - 1) >= 0 ? outputBoard[i][j - 1] : null;

                String bestMatchingChar = getBestMatchingChar(inputBoard[i][j], new String[]{topInput, rightInput, bottomInput, leftInput}, Arrays.stream(new String[]{topOutput, leftOutput}).collect(Collectors.toList()));
                res.append(bestMatchingChar);
                outputBoard[i][j] = bestMatchingChar;
            }
            res.append("\n");
        }

        System.out.println(res);
    }

    /**
     * Adjacent characters and the current character cannot be selected.
     * Surrounding characters are accounted for as potential swaps, prioritizing the current character as the safest choice.
     */
    private static String getBestMatchingChar(String current, String[] inputCharsAround, List<String> outputCharsAround) {
        Map<String, Integer> charsCount = createCharsCountMap(current, outputCharsAround);

        Arrays.stream(inputCharsAround).forEach(charAround -> {
            if (!Objects.equals(charAround, current) && charAround != null) {
                charsCount.merge(charAround, 1, (curr, notUsed) -> ++curr);
            }
        });
        return Collections.max(charsCount.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    /**
     * Populates the map with initial values for all valid characters.
     * <p>
     * Sets the current character and all neighboring characters to the minimum
     * required value so they can be filtered out in subsequent processing steps.
     */
    private static Map<String, Integer> createCharsCountMap(String current, List<String> outputCharsAround) {
        Map<String, Integer> charsAroundCount = new HashMap<>();

        charsAroundCount.put(A_CHAR, outputCharsAround.contains(A_CHAR) || Objects.equals(current, A_CHAR) ? Integer.MIN_VALUE : 0);
        charsAroundCount.put(B_CHAR, outputCharsAround.contains(B_CHAR) || Objects.equals(current, B_CHAR) ? Integer.MIN_VALUE : 0);
        charsAroundCount.put(C_CHAR, outputCharsAround.contains(C_CHAR) || Objects.equals(current, C_CHAR) ? Integer.MIN_VALUE : 0);
        charsAroundCount.put(D_CHAR, outputCharsAround.contains(D_CHAR) || Objects.equals(current, D_CHAR) ? Integer.MIN_VALUE : 0);
        return charsAroundCount;
    }
}
