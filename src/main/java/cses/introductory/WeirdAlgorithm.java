package cses.introductory;

import java.util.Scanner;

public class WeirdAlgorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = Long.parseLong(scanner.next());
        System.out.print(n + " ");
        while (n != 1) {
            boolean isEven = n % 2 == 0;
            if (isEven) {
                // If n is even, the algorithm divides it by two
                n /= 2;
            } else {
                // if n is odd, the algorithm multiplies it by three and adds one
                n = n * 3 + 1;
            }
            System.out.print(n + " "); // Left the " " at the end for readability, and it does not count as wrong answer
        }
    }
}
