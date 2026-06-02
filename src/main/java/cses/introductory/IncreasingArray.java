package cses.introductory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IncreasingArray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String length = scanner.nextLine();
        String input = scanner.nextLine();

        List<Long> longInput = new ArrayList<>();
        Arrays.stream(input.split(" "))
                .forEach(s -> longInput.add(Long.valueOf(s)));

        long stepsToComplete = 0L;
        for (int i = 1; i < longInput.size(); i++) {

            Long last = longInput.get(i - 1);
            Long current = longInput.get(i);

            long subtractionRes = last - current;

            int numberLargerThreshold = 0;
            if (subtractionRes > numberLargerThreshold) {
                stepsToComplete += subtractionRes;
                longInput.set(i, current + subtractionRes);
            }
        }

        System.out.println(stepsToComplete);
    }
}
