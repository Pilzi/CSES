package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class CollectingNumbers {
    private static long roundCount = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputArrayLength = Integer.parseInt(br.readLine());

        int[][] inputArray = tokenizeToArray(br.readLine(), inputArrayLength);

        Arrays.sort(inputArray, Comparator.comparingInt(o -> o[0]));

        int lastIndex = -1;
        for (int i = 0; i < inputArrayLength; i++) {
            if (inputArray[i][1] < lastIndex) {
                roundCount++;
            }
            lastIndex = inputArray[i][1];
        }

        System.out.println(roundCount);
    }

    private static int[][] tokenizeToArray(String input, int length) {
        StringTokenizer tokenizer = new StringTokenizer(input);

        int[][] inputArray = new int[length][2];
        int tokenizerIndex = 0;

        while (tokenizer.hasMoreTokens()) {
            inputArray[tokenizerIndex][0] = Integer.parseInt(tokenizer.nextToken());
            inputArray[tokenizerIndex][1] = tokenizerIndex;

            tokenizerIndex++;
        }
        return inputArray;
    }
}