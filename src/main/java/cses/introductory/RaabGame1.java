package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RaabGame1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long testAmount = Long.parseLong(br.readLine().trim());

        for (int i = 0; i < testAmount; i++) {
            StringBuilder resA = new StringBuilder();
            StringBuilder resB = new StringBuilder();
            String test = br.readLine();
            String[] testParts = test.split(" ");

            int n = Integer.parseInt(testParts[0]);
            int a = Integer.parseInt(testParts[1]);
            int b = Integer.parseInt(testParts[2]);

            List<Integer> aCards = new ArrayList<>();
            List<Integer> bCards = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                aCards.add(j + 1);
                bCards.add(j + 1);
            }
            boolean isNo = false;
            try {
                while (a > 0 || b > 0) {
                    if (a >= b) {
                        while (a >= b && a != 0) {
                            // Player a must win
                            generateGameResult(bCards, aCards, resA, resB);
                            a--;
                        }
                    } else {
                        while (b >= a && b != 0) {
                            // Player b must win
                            generateGameResult(aCards, bCards, resB, resA);
                            b--;

                        }
                    }
                }
                // In this case only cards that should be equal are left append them to the result
                for (int j = 0; j < aCards.size(); j++) {
                    Integer aCard = aCards.get(j);
                    Integer bCard = bCards.get(j);

                    // If the cards are not equal as expected it's the game is not possible
                    if (!Objects.equals(aCard, bCard)) {
                        isNo = true;
                        break;
                    }
                    resA.append(aCard).append(" ");
                    resB.append(bCard).append(" ");
                }
            } catch (RuntimeException e) {
                isNo = true;
            }

            if (isNo) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
                System.out.println(resA);
                System.out.println(resB);
            }
        }
    }

    private static void generateGameResult(List<Integer> losingPlayerCards, List<Integer> winningPlayerCards, StringBuilder resA, StringBuilder resB) {
        int smallestCard = losingPlayerCards.get(0);
        losingPlayerCards.remove(0);
        int winningCardIndex = findFirstNumberAboveMin(smallestCard, winningPlayerCards);
        Integer aCard = winningPlayerCards.get(winningCardIndex);
        winningPlayerCards.remove(winningCardIndex);
        resA.append(aCard).append(" ");
        resB.append(smallestCard).append(" ");
    }

    private static int findFirstNumberAboveMin(int min, List<Integer> bCards) {
        for (int i = 0; i < bCards.size(); i++) {
            if (bCards.get(i) > min) {
                return i;
            }
        }
        throw new RuntimeException("Number does not exist");
    }
}
