package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class DistinctNumbers  {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputLength = br.readLine();

        Set<String> characters = Arrays.stream(br.readLine().split(" ")).collect(Collectors.toSet());

        System.out.println(characters.size());
    }
}