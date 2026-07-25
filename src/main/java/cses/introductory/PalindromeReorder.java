package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PalindromeReorder {

    public static String NO_SOLUTION_TEXT = "NO SOLUTION";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().trim();
        StringBuilder resLeft = new StringBuilder();
        StringBuilder resRight = new StringBuilder();
        // The mid is the number that only appears once and cannot be mirrored
        StringBuilder resMid = new StringBuilder();

        // Preprocess building map
        Map<Character, Long> characterAppearance = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            characterAppearance.merge(input.charAt(i), 1L, Long::sum);
        }

        // Analyze preprocessed map to check if its even possible to build a palindrome
        List<Long> allCharactersWithEvenAppearance = characterAppearance.values().stream().filter(appearance -> appearance % 2 == 0).collect(Collectors.toList());

        boolean isNoSolutionFound = false;
        if (input.length() % 2 == 0) {
            // All numbers must be even if the total length of the string is even
            if (allCharactersWithEvenAppearance.size() != characterAppearance.size()) {
                isNoSolutionFound = true;
            }
        } else {
            if (allCharactersWithEvenAppearance.size() != characterAppearance.size() - 1) {
                isNoSolutionFound = true;
            }
        }

        if (isNoSolutionFound) {
            System.out.println(NO_SOLUTION_TEXT);
        } else {

            List<Map.Entry<Character, Long>> sortedMap = characterAppearance.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
            for (int i = sortedMap.size() - 1; i >= 0; i--) {
                for (int j = 0; j < sortedMap.get(i).getValue(); j++) {
                    if (sortedMap.get(i).getValue() % 2 != 0) {
                        resMid.append(sortedMap.get(i).getKey());
                    } else if (j % 2 == 0) {
                        resLeft.append(sortedMap.get(i).getKey());
                    } else {
                        resRight.append(sortedMap.get(i).getKey());
                    }
                }
            }
            System.out.println(resLeft.reverse().toString() + resMid + resRight);
        }
    }
}
