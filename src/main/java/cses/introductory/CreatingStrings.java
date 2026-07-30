package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class CreatingStrings {
    private static final Set<String> solutionsFound = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputSequence = br.readLine().trim();

        // Initial sort so the recursive 'll automatically be in alphabetical order
        List<String> inputInAlphabeticalOrder = Arrays.stream(inputSequence.split("")).sorted().collect(Collectors.toList());

        creatingStrings("", inputInAlphabeticalOrder);

        System.out.println(solutionsFound.size());
        System.out.println(String.join("\n", solutionsFound));
    }

    public static void creatingStrings(String currentString, List<String> availableChars) {
        for (String currentChar : availableChars) {
            List<String> availableCharsCopy = new ArrayList<>(availableChars);
            availableCharsCopy.remove(currentChar);
            creatingStrings(currentString + currentChar, availableCharsCopy);
        }

        if (availableChars.isEmpty()) {
            solutionsFound.add(currentString);
        }
    }
}
