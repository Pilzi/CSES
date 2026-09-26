package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaximumSubarraySum  {
    public static long maximumSubArraySum = Long.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputArrayLength = Integer.parseInt(br.readLine());

        long[] inputArray = inputToValueIndexArray(br.readLine(), inputArrayLength);

        findMaximumSubArray(inputArray);

        System.out.println(maximumSubArraySum);
    }

    private static void findMaximumSubArray(long[] inputArray) {
        long sum = 0;

        for (long l : inputArray) {
            sum += l;
            maximumSubArraySum = Math.max(maximumSubArraySum, sum);
            sum = Math.max(0, sum);
        }
    }

    private static long[] inputToValueIndexArray(String input, int length) {
        StringTokenizer tokenizer = new StringTokenizer(input);

        long[] inputArray = new long[length];
        int tokenizerIndex = 0;

        while (tokenizer.hasMoreTokens()) {
            inputArray[tokenizerIndex] = Long.parseLong(tokenizer.nextToken());
            tokenizerIndex++;
        }
        return inputArray;
    }
}
