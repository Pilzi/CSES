package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CoinPiles {

    /**
     * Determines whether it is possible to reduce both piles to zero.
     * <p>
     * At each step, two items must be removed from one pile and one item from the other.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long length = Long.parseLong(br.readLine().trim());
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < length; i++) {
            String[] lineParts = br.readLine().trim().split(" ");

            long pileA = Long.parseLong(lineParts[0]);
            long pileB = Long.parseLong(lineParts[1]);

            long moduloPileA = pileA % 3;
            long moduloPileB = pileB % 3;

            boolean isPossibleToEqualizeBothPiles = (moduloPileA == 1 && moduloPileB == 2) || (moduloPileA == 2 && moduloPileB == 1) || (moduloPileA == 0 && moduloPileB == 0);

            long smallerPile = Math.min(pileA, pileB);
            long biggerPile = Math.max(pileA, pileB);
            // If the smaller pile is not at least half of the bigger is not possible to equalize them at 0
            boolean canEqualizeAtZero = (smallerPile * 2) >= (biggerPile);

            if (isPossibleToEqualizeBothPiles && canEqualizeAtZero) {
                res.append("YES");
            } else {
                res.append("NO");
            }
            res.append("\n");
        }
        System.out.println(res);
    }
}