package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringReorder {
    private static long remainingCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder res = new StringBuilder();

        long[] charCount = new long[26];

        String inputString = br.readLine();
        for (int i = 0; i < inputString.length(); i++) {
            char theChar = inputString.charAt(i);
            charCount[theChar - 65]++;
        }
        boolean impossibleToFindSolution = false;
        remainingCount = inputString.length();

        int lastChar = -1;

        try {
            while (remainingCount > 0) {
                int bestMatchingCharIndex = getBestMatchingNextChar(charCount, lastChar);
                charCount[bestMatchingCharIndex]--;
                res.append((char) (65 + bestMatchingCharIndex));
                lastChar = bestMatchingCharIndex;
                remainingCount--;
            }
        } catch (RuntimeException e) {
            impossibleToFindSolution = true;
        }


        if (impossibleToFindSolution) {
            System.out.println(-1);
        } else {
            System.out.println(res);
        }
    }

    private static int getBestMatchingNextChar(long[] charCount, int lastChar) {
        long maxIndex = -1;
        long maxCount = 0;

        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] > maxCount) {
                maxCount = charCount[i];
                maxIndex = i;
            }
        }

        if (maxCount > (remainingCount) / 2) {
            if (maxIndex != lastChar) {
                return Math.toIntExact(maxIndex);
            } else {
                throw new RuntimeException("impossible");
            }
        }

        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] > 0 && lastChar != i) {
                return i;
            }

        }
        throw new RuntimeException("impossible");
    }
}