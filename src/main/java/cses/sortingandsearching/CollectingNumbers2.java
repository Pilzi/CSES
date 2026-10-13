package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CollectingNumbers2 {
    // As the challenge start with 1 the index 0 in the array is ignored
    public static final int FIRST_POSSIBLE_POSITION = 1;
    public static long roundCount = 1;
    static int[] updatedPairs = new int[4];
    static int pairsCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstInputLine = br.readLine().split(" ");
        int inputArrayLength = Integer.parseInt(firstInputLine[0]);
        int operations = Integer.parseInt(firstInputLine[1]);
        StringBuilder sb = new StringBuilder();

        int[] positions = new int[inputArrayLength + 1];
        int[] values = new int[inputArrayLength + 1];

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int tokenizerIndex = 0;

        while (tokenizer.hasMoreTokens()) {
            int token = Integer.parseInt(tokenizer.nextToken());
            positions[token] = tokenizerIndex;
            values[tokenizerIndex] = token;
            tokenizerIndex++;
        }

        // Calculate the row count once for the initial array
        for (int i = 1; i < inputArrayLength; i++) {
            if (positions[i + 1] < positions[i]) {
                roundCount++;
            }
        }

        for (int i = 0; i < operations; i++) {
            tokenizer = new StringTokenizer(br.readLine());

            int left = Integer.parseInt(tokenizer.nextToken()) - 1;
            int right = Integer.parseInt(tokenizer.nextToken()) - 1;

            handleAndCountSwapOperation(positions, values, left, right, inputArrayLength);
            sb.append(roundCount).append("\n");
        }

        System.out.println(sb);
    }

    private static void handleAndCountSwapOperation(int[] positions,
                                                    int[] values,
                                                    int left,
                                                    int right,
                                                    int inputLength) {
        // Check only value-neighbors to determine if the round count changed
        if (values[left] + 1 <= inputLength) {
            putPair(values[left]);
        }
        if (values[left] - 1 >= FIRST_POSSIBLE_POSITION) {
            putPair(values[left] - 1);
        }
        if (values[right] + 1 <= inputLength) {
            putPair(values[right]);
        }
        if (values[right] - 1 >= FIRST_POSSIBLE_POSITION) {
            putPair(values[right] - 1);
        }

        // Remove the round count contribution of these pairs before the swap
        for (int i = 0; i < pairsCount; i++) {
            int key = updatedPairs[i];
            roundCount -= positions[key] > positions[key + 1] ? 1 : 0;
        }

        swap(values, left, right);
        positions[values[left]] = left;
        positions[values[right]] = right;

        // Add the new round count contribution of these pairs after the swap
        for (int i = 0; i < pairsCount; i++) {
            int key = updatedPairs[i];
            roundCount += positions[key] > positions[key + 1] ? 1 : 0;
        }

        pairsCount = 0;
    }

    static void putPair(int key) {
        for (int i = 0; i < pairsCount; i++) {
            if (updatedPairs[i] == key) return;
        }
        updatedPairs[pairsCount++] = key;
    }

    private static void swap(int[] inputArray, int firstSwapIndex, int secondSwapIndex) {
        int temp = inputArray[firstSwapIndex];
        inputArray[firstSwapIndex] = inputArray[secondSwapIndex];
        inputArray[secondSwapIndex] = temp;
    }
}
