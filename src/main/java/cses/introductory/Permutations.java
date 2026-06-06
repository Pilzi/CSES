package cses.introductory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Permutations {
    private static final int START = 1;
    private static final String NO_SOLUTION_TEXT = "NO SOLUTION";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ceiling = Integer.parseInt(scanner.nextLine());

        ArrayList<Integer> evenNumbers = new ArrayList<>();
        ArrayList<Integer> oddNumbers = new ArrayList<>();

        for (int i = START; i <= ceiling; i++) {
            if (i % 2 == 0) {
                evenNumbers.add(i);
            } else {
                oddNumbers.add(i);
            }
        }

        boolean solutionFound = isValidPermutation(Stream.concat(evenNumbers.stream(), oddNumbers.stream()).collect(Collectors.toList()), ceiling);

        System.out.println(solutionFound ? intArrayToString(evenNumbers) + intArrayToString(oddNumbers) : NO_SOLUTION_TEXT);
    }


    private static String intArrayToString(List<Integer> res) {
        StringBuilder stringBuilder = new StringBuilder();

        res.forEach(l -> stringBuilder.append(l).append(" "));

        return stringBuilder.toString();
    }

    public static boolean isValidPermutation(List<Integer> res, long n) {
        if (res.size() != n) {
            return false;
        }
        for (int i = 1; i < res.size(); i++) {
            long last = res.get(i - 1);
            long current = res.get(i);
            long difference = last - current;

            if (difference <= 1 && difference >= -1) {
                return false;
            }
        }
        return true;
    }

}
