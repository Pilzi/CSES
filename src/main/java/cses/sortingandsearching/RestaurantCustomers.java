package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class RestaurantCustomers {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputLength = Integer.parseInt(br.readLine());

        long[] arrivalTimes = new long[inputLength];
        long[] leavingTimes = new long[inputLength];

        for (int i = 0; i < inputLength; i++) {
            String[] line = br.readLine().split(" ");

            arrivalTimes[i] = Long.parseLong(line[0]);
            leavingTimes[i] = Long.parseLong(line[1]);
        }

        arrivalTimes = Arrays.stream(arrivalTimes).sorted().toArray();
        leavingTimes = Arrays.stream(leavingTimes).sorted().toArray();

        long maxCustomerAmount = getMaxCustomerAmount(leavingTimes, arrivalTimes);
        System.out.println(maxCustomerAmount);
    }

    private static long getMaxCustomerAmount(long[] leavingTimes, long[] arrivalTimes) {
        int leavingIndex = 0;
        int arrivalIndex = 0;
        long maxCustomerAmount = 0;
        long currentCustomerAmount = 0;
        while (leavingIndex < leavingTimes.length) {
            long nextLeavingTime = leavingTimes[leavingIndex];
            while (arrivalIndex < arrivalTimes.length && nextLeavingTime >= arrivalTimes[arrivalIndex] ) {
                currentCustomerAmount++;
                arrivalIndex++;
                maxCustomerAmount = Math.max(currentCustomerAmount, maxCustomerAmount);

            }
            leavingIndex++;
            currentCustomerAmount--;
        }
        return maxCustomerAmount;
    }
}
