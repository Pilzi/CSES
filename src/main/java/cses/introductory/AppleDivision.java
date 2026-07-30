package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class AppleDivision {
    private static final List<Long> sumHistory = new ArrayList<>();

    /**
     * Collects all possible apple combinations and records them in the history.
     * Returns the combination from the history whose sum is closest to half of the total sum.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long appleListSize = Long.parseLong(br.readLine().trim());
        Long[] apples = Arrays.stream(br.readLine().trim().split(" ")).map(Long::parseLong).sorted(Comparator.reverseOrder()).toArray(Long[]::new);

        getPossibleCombinations(0, apples, 0L);
        long sumOfApples = Arrays.stream(apples).mapToLong(Long::intValue).sum();

        System.out.println((long) (getBestMatchingSum(sumOfApples / 2d) * 2));
    }

    private static double getBestMatchingSum(double targetSum) {
        double bestMatching = Long.MAX_VALUE;
        for (double historySum : sumHistory) {
            double diff = (historySum - targetSum);
            if (diff < 0) {
                diff *= -1;
            }

            if (diff < bestMatching) {
                bestMatching = diff;
            }
        }
        return bestMatching;
    }

    public static void getPossibleCombinations (long index, Long[] array, long sum) {
        if (index >= array.length) {
            return;
        }

        for (long i = index; i < array.length; i++) {
            long valueAtIndex = array[Math.toIntExact(i)];
            sumHistory.add(sum + valueAtIndex);
            getPossibleCombinations(i + 1, array, sum + valueAtIndex);
        }
    }
}
