package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NumberSpiral {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int amountOfTestCases = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < amountOfTestCases; i++) {

            String[] inputLine = br.readLine().trim().split(" ");

            int row = Integer.parseInt(inputLine[0]);
            int column = Integer.parseInt(inputLine[1]);

            long spiralValue;
            if (row > column) {
                spiralValue = getSpiralValue(row, column - 1, true);
            } else {
                spiralValue = getSpiralValue(column, row - 1, false);
            }

            sb.append(spiralValue).append('\n');
        }

        System.out.print(sb);
    }

    private static long getSpiralValue(long i, long j, boolean isRow) {
        boolean isEven = i % 2 == 0;

        if (isEven && isRow) {
            return (i * i) - j;
        }

        if (!isEven && !isRow) {
            return (i * i) - j;
        }

        long lastSpiralMax = (i - 1) * (i - 1);
        return lastSpiralMax + 1 + j;
    }
}
