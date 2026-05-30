package cses.introductory;

import java.util.Scanner;

public class Repetitions {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Character lastChar = null;
        long currentCharCount = 0L;
        long highestCharCount = 0L;

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            // The first char can be ignored as there is no last char to prevent a NullPointerException
            if (i != 0) {
                if (currentChar != lastChar) {
                    if (currentCharCount > highestCharCount) {
                        highestCharCount = currentCharCount;
                    }
                    currentCharCount = 0;
                }
            }
            currentCharCount++;

            lastChar = currentChar;
        }

        System.out.println(Math.max(currentCharCount, highestCharCount));
    }
}
