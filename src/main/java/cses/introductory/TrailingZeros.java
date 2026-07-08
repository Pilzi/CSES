package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TrailingZeros {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine().trim());
        BigDecimal factorial = factorial(BigDecimal.valueOf(n));
        System.out.println(factorial);
    }

    /**
     * Calculating the trailing zeros thanks to Legendre's Formula
     * Zeros are created by 2 * 5 pairs.
     * For each 5 x 5 multiple there is another 5 to consider
     */
    public static BigDecimal factorial(BigDecimal n) {
        BigDecimal traillingZeros = BigDecimal.ZERO;
        while (n.compareTo(BigDecimal.valueOf(5)) >= 0) {
            n = n.divide(BigDecimal.valueOf(5), RoundingMode.DOWN);
            traillingZeros = traillingZeros.add(n);
        }

        return traillingZeros;
    }
}
