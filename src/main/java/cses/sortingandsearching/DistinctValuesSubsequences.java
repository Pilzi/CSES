package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicReference;

public class DistinctValuesSubsequences {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int inputLength = Integer.parseInt(br.readLine());
        Map<Integer, Long> frequencyMap = new HashMap<>();
        AtomicReference<BigDecimal> solution = new AtomicReference<>(new BigDecimal(1));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        while (tokenizer.hasMoreTokens()) {
            int value = Integer.parseInt(tokenizer.nextToken());
            frequencyMap.merge(value, 1L, (a, b) -> a + 1);
        }

        frequencyMap.forEach((integer, freq) -> {
            solution.set(solution.get().multiply(BigDecimal.valueOf(freq + 1)).remainder(BigDecimal.valueOf(1_000_000_007)));
        });

        System.out.println(solution.get().subtract(BigDecimal.valueOf(1)));
    }
}
