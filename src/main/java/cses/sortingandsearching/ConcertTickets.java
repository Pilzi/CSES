package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ConcertTickets {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder res = new StringBuilder();

        String firstInputLine = br.readLine();
        int[] tickets = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int[] ticketsParent = new int[tickets.length];

        for (int i = 0; i < ticketsParent.length; i++) {
            ticketsParent[i] = i;
        }
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        while (tokenizer.hasMoreElements()) {
            int customerMaxPrice = Integer.parseInt(tokenizer.nextToken());

            int bestMatchingTicketIndex = getMostExpensiveBestMatchingPrice(customerMaxPrice, tickets);

            if (bestMatchingTicketIndex >= 0) {

                int i = find(ticketsParent, bestMatchingTicketIndex);

                ticketsParent[bestMatchingTicketIndex] = i - 1;

                if (i >= 0) {
                    int bestTicketForCustomer = tickets[i];
                    ticketsParent[i] = i - 1;
                    res.append(bestTicketForCustomer).append("\n");
                } else {
                    res.append(-1).append("\n");
                }
            } else {
                res.append(-1).append("\n");
            }

        }

        System.out.println(res);
    }

    private static int getMostExpensiveBestMatchingPrice(long customerMaxPrice, int[] tickets) {
        int max = tickets.length - 1;
        int min = 0;
        while (min < max) {
            int mid = min + (max - min) / 2;

            int ticket = tickets[mid];

            if (ticket <= customerMaxPrice && tickets[mid + 1] > customerMaxPrice) {
                return mid;
            }
            if (ticket > customerMaxPrice) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        return tickets[min] > customerMaxPrice ? -1 : min;
    }


    private static int find(int[] ticketsParent, int index) {
        if (index < 0) return -1;

        int root = index;
        while (root >= 0 && ticketsParent[root] != root) {
            root = ticketsParent[root];
        }

        int curr = index;
        while (curr >= 0 && curr != root) {
            int next = ticketsParent[curr];
            ticketsParent[curr] = root;
            curr = next;
        }

        return root;
    }
}