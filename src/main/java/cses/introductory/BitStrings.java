package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class BitStrings {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        BigDecimal res = BigDecimal.valueOf(2);

        System.out.println(res.pow(n).remainder(BigDecimal.valueOf(1000000007)));
    }
}
