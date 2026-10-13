package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Playlist {
    public static long solution = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputLength = Integer.parseInt(br.readLine());
        int[] array = new int[inputLength];
        LinkedHashSet<Integer> numbersAlreadyFound = new LinkedHashSet<>();

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int index = 0;
        while (tokenizer.hasMoreTokens()) {
            int token = Integer.parseInt(tokenizer.nextToken());
            array[index] = token;
            index++;
        }

        for (int i : array) {
            if (!numbersAlreadyFound.contains(i)) {
                numbersAlreadyFound.add(i);
                solution = Math.max(numbersAlreadyFound.size(), solution);
            } else {
                Iterator<Integer> iterator = numbersAlreadyFound.iterator();
                while (iterator.hasNext()) {
                    Integer current = iterator.next();
                    if (current.equals(i)) {
                        break;
                    }
                    iterator.remove();
                }
                iterator.remove();
                numbersAlreadyFound.add(i);
            }
        }

        System.out.println(solution);
    }
}