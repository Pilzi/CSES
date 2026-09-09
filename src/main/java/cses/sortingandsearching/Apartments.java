package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PrimitiveIterator;

public class Apartments {
    private static long solutionsCount = 0L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstInputLine = br.readLine().split(" ");

        long allowedDeviation = Long.parseLong(firstInputLine[2]);

        long[] applicantSizes = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).sorted().toArray();
        long[] apartmentSizes = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).sorted().toArray();

        PrimitiveIterator.OfLong iterator = Arrays.stream(applicantSizes).iterator();
        int removed = 0;
        while (iterator.hasNext()) {
            long applicant = iterator.next();
            for (int j = removed; j < apartmentSizes.length; j++) {
                long apartment = apartmentSizes[j];

                if (apartment - allowedDeviation > applicant) {
                    break;
                }
                if (apartment + allowedDeviation < applicant) {
                    removed = j;
                }
                if (apartment + allowedDeviation >= applicant && apartment - allowedDeviation <= applicant) {
                    solutionsCount++;
                    removed = removed > j ? removed : j + 1;
                    break;
                }
            }
        }

        System.out.println(solutionsCount);
    }
}