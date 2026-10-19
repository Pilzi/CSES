package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class DistinctValuesSubarrays {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int inputLength = Integer.parseInt(br.readLine());
        Set<Integer> distinctNumbers = new HashSet<>();
        int[] inputArray = new int[inputLength];

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int index = 0;
        while (tokenizer.hasMoreTokens()) {
            inputArray[index] =  Integer.parseInt(tokenizer.nextToken());
            index++;
        }

        index = 0;
        int windowStart = 0;
        BigDecimal solution = new BigDecimal(0);
        while (index < inputArray.length) {
            int numberAtIndex = inputArray[index];

            if (!distinctNumbers.contains(numberAtIndex)) {
                distinctNumbers.add(numberAtIndex);
                index++;
            } else {
                int lastIndex = index;
                index = findLastAppearance(inputArray, index) + 1;
                solution = solution.add(calcWindows(windowStart, lastIndex).subtract(calcWindows(index, lastIndex)));
                windowStart = index; // already index++
                distinctNumbers = new HashSet<>();
            }

        }

        System.out.println(solution.add(calcWindows(windowStart, index)));
    }

    private static int findLastAppearance(int[] inputArray, int index) {
        int numberAtIndex = inputArray[index];
        int i = index - 1;

        while (i >= 0) {
            if (inputArray[i] == numberAtIndex) {
                return i;
            }
            i--;
        }

        throw new RuntimeException("impossible case");
    }

    private static BigDecimal calcWindows(int windowStart, int lastIndex) {
        int n = lastIndex - windowStart;

        return new BigDecimal(n).multiply(new BigDecimal(n + 1)).divide(new BigDecimal(2));
    }
}
