package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Towers {
    public static final int MINIMAL_TOWER_TOP_ELEMENT = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int inputLength = Integer.parseInt(br.readLine());
        TreeMap<Long /* top element */, Integer /* towers with same top element */> towers = new TreeMap<>();
        long solution = 0;

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        while (tokenizer.hasMoreTokens()) {
            long token = Long.parseLong(tokenizer.nextToken());
            Map.Entry<Long, Integer> entry = towers.higherEntry(token);

            if (entry == null) {
                solution++;
            } else {
                if(entry.getValue() == MINIMAL_TOWER_TOP_ELEMENT) {
                    towers.remove(entry.getKey());
                } else {
                    towers.put(entry.getKey(), entry.getValue() - 1);
                }
            }

            towers.put(token, towers.getOrDefault(token, 0)+1);
        }

        System.out.println(solution);
    }
}
