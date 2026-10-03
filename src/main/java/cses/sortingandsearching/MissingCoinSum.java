package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MissingCoinSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputArrayLength = Integer.parseInt(br.readLine());

        int[] inputArray = tokenizeToArray(br.readLine(), inputArrayLength);
        Arrays.sort(inputArray);

        long coinValue = 1;

        for (int i = 0; i < inputArrayLength; i++) {
            if (coinValue < inputArray[i]) {
                break;
            } else if (inputArray[i] <= coinValue) {
                coinValue = coinValue + inputArray[i];
            }
        }

        System.out.println(coinValue);
    }

    private static int[] tokenizeToArray(String input, int length) {
        StringTokenizer tokenizer = new StringTokenizer(input);

        int[] inputArray = new int[length];
        int tokenizerIndex = 0;

        while (tokenizer.hasMoreTokens()) {
            inputArray[tokenizerIndex] = Integer.parseInt(tokenizer.nextToken());
            tokenizerIndex++;
        }
        return inputArray;
    }
}
