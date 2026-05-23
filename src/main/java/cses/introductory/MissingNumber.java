package cses.introductory;

import java.util.Arrays;
import java.util.Scanner;

public class MissingNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = Long.parseLong(scanner.nextLine());
        String input = scanner.nextLine();

        long expected = getExpectedSum(n);
        long actually = getActuallySum(input);

        System.out.println(expected - actually);
    }

    /**
     * Returns the expected sum of all values from 1 to n.
     *
     * @param length the upper bound n
     */
    public static long getExpectedSum(long length) {
        long calculatedExpected = 0L;

        for (long i = length; i > 0; i--) {
            calculatedExpected += i;
        }

        return calculatedExpected;
    }

    public static long getActuallySum(String input) {
        return Arrays.stream(input.split(" "))
                .map(Long::parseLong)
                .reduce(0L, Long::sum);
    }
}
