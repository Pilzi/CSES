package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DigitQueries {
    public static BigDecimal[] numberOrder = new BigDecimal[19];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputLength = Integer.parseInt(br.readLine());
        numberOrder[0] = BigDecimal.valueOf(0);

        for (int i = 0; i < numberOrder.length - 1; i++) {
            BigDecimal sum = BigDecimal.valueOf(9 * Math.pow(10, i) * (i + 1));

            sum = sum.add(numberOrder[i]);

            numberOrder[i + 1] = sum;
        }

        for (int i = 0; i < inputLength; i++) {
            long searchedLong = Long.parseLong(br.readLine());

            int searchedNumberIndex = findMatchingNumber(searchedLong);

            BigDecimal sum = BigDecimal.valueOf(10).pow(searchedNumberIndex).subtract(BigDecimal.valueOf(1));

            BigDecimal missing = BigDecimal.valueOf(searchedLong).subtract(numberOrder[searchedNumberIndex]);

            BigDecimal digitsPerNumber = missing.setScale(2, RoundingMode.FLOOR)
                    .divide(BigDecimal.valueOf(searchedNumberIndex + 1), RoundingMode.FLOOR);

            sum = sum.add(digitsPerNumber);

            int numIndex = missing.remainder(BigDecimal.valueOf(searchedNumberIndex + 1)).intValue();

            String sumString = sum.toString().split("\\.")[0];

            if (numIndex > 0) {
                sum = sum.add(BigDecimal.valueOf(1));
                sumString = sum.toString().split("\\.")[0];
                numIndex--;
                System.out.println(sumString.charAt(Math.max(numIndex, 0)));
            } else {
                System.out.println(sumString.charAt(sumString.length() - 1));
            }
        }
    }

    private static int findMatchingNumber(long searchedLong) {
        for (int i = numberOrder.length - 1; i >= 0 ; i--) {
            if (numberOrder[i].compareTo(BigDecimal.valueOf(searchedLong)) <= 0) {
                return i;
            }
        }

        return 0;
    }
}