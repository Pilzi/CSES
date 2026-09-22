package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SumOfTwoValues {
    public static final String IMPOSSIBLE_OUTPUT = "IMPOSSIBLE";
    public static final int NUMBER_NOT_FOUND = -1;
    public static final int SORTED_VALUE_INDEX = 0;
    public static final int UNSORTED_POSITION_INDEX = 1;
    public static final int NO_FORBIDDEN_INDEX = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int inputArrayLength = Integer.parseInt(firstLine[0]);
        int target = Integer.parseInt(firstLine[1]);

        long[][] inputArray = inputToValueIndexArray(br.readLine(), inputArrayLength);

        Arrays.sort(inputArray, (o1, o2) -> Math.toIntExact(o1[0] - o2[0]));

        // One of both elements must be at least half of the target
        double targetHalf = Math.floor(target / 2);

        int lowerSearchedNumberStartIndex = lowerBound(inputArray, targetHalf, NO_FORBIDDEN_INDEX);
        int upperSearchedNumberIndex = lowerBound(inputArray, targetHalf, lowerSearchedNumberStartIndex);

        boolean solutionFound = false;
        if (lowerSearchedNumberStartIndex == NUMBER_NOT_FOUND) {
            System.out.println(IMPOSSIBLE_OUTPUT);
        } else{
            // Shift the indexes to the left if there is no available index for upperSearchedNumberIndex
            if (upperSearchedNumberIndex == NUMBER_NOT_FOUND) {
                upperSearchedNumberIndex = lowerSearchedNumberStartIndex;
                lowerSearchedNumberStartIndex = upperSearchedNumberIndex - 1;
            }

            for (int upper = upperSearchedNumberIndex; upper < inputArray.length; upper++) {
                for (int lower = lowerSearchedNumberStartIndex; lower >= 0; lower--) {
                    if (lower < upper) {
                        if (inputArray[lower][SORTED_VALUE_INDEX] + inputArray[upper][SORTED_VALUE_INDEX] == target) {
                            System.out.printf("%s %s", inputArray[lower][UNSORTED_POSITION_INDEX] + 1, inputArray[upper][UNSORTED_POSITION_INDEX] + 1);
                            solutionFound = true;
                            break;
                        }
                        // As the upper number 'll increase this is a great checkpoint to continue the search
                        if (inputArray[lower][SORTED_VALUE_INDEX] + inputArray[upper][SORTED_VALUE_INDEX] < target) {
                            lowerSearchedNumberStartIndex = lower;
                            lower = -1;
                        }
                    }
                }

                if (solutionFound) {
                    break;
                }
            }

            if (!solutionFound) {
                System.out.println(IMPOSSIBLE_OUTPUT);
            }
        }
    }

    private static long[][] inputToValueIndexArray(String input, int length) {
        StringTokenizer tokenizer = new StringTokenizer(input);

        long[][] inputArray = new long[length][2];
        int tokenizerIndex = 0;

        while (tokenizer.hasMoreTokens()) {
            inputArray[tokenizerIndex][SORTED_VALUE_INDEX] = Long.parseLong(tokenizer.nextToken());
            inputArray[tokenizerIndex][UNSORTED_POSITION_INDEX] = tokenizerIndex;
            tokenizerIndex++;
        }
        return inputArray;
    }

    /**
     * Iterate through a sorted array and find the first value that is >= the target
     *
     * @param array          sorted array
     * @param target         searched lower bound
     * @param forbiddenIndex one index that is excluded
     * @return index or -1 if the number does not exist
     */
    private static int lowerBound(long[][] array, double target, int forbiddenIndex) {
        for (int i = 0; i < array.length; i++) {
            if (array[i][SORTED_VALUE_INDEX] >= target && i != forbiddenIndex) {
                return i;
            }
        }

        return NUMBER_NOT_FOUND;
    }
}
