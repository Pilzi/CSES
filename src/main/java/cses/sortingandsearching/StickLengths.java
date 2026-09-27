package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StickLengths  {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputArrayLength = Integer.parseInt(br.readLine());

        long[] inputArray = inputToValueIndexArray(br.readLine(), inputArrayLength);
        Arrays.sort(inputArray);

        long solution = getMinSteps(inputArray);

        System.out.println(solution);
    }

    /**
     * The algorithm is as simple as it looks: the middle value of the sorted array is the best target to align every other value to.
     *
     * @param sortedArray sorted array
     * @return minimal number of steps needed to equalize all values
     */
    private static long getMinSteps(long[] sortedArray) {
        long middle = sortedArray[sortedArray.length / 2];

        long sum = 0;
        for (long l : sortedArray) {
            sum += Math.abs(l - middle);
        }

        return sum;
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
