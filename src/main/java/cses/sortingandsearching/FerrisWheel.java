package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FerrisWheel {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] firstLine = br.readLine().split(" ");
        long maxWeight = Long.parseLong(firstLine[1]);
        List<Long> childrenWeights = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        long totalResult = 0;

        int startIndex = 0;
        int endIndex = childrenWeights.size() - 1;

        while (startIndex <= endIndex) {
            long heaviestWeight = childrenWeights.get(endIndex);
            long lightestWeight = childrenWeights.get(startIndex);

            endIndex--;
            if (heaviestWeight + lightestWeight <= maxWeight) {
                startIndex++;
            }

            totalResult++;
        }

        System.out.println(totalResult);
    }
}