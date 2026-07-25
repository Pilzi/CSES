package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GrayCode {

    /**
     * The algorithm starts with two initial values, 0 and 1.
     * Every other possible number is built by reversing the list of numbers already found and adding a 1 to the start of the string.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt( br.readLine().trim());
        StringBuilder res = new StringBuilder();

        BigDecimal base = BigDecimal.valueOf(2);

        List<Integer> outputNumbers = new ArrayList<>();

        // Initial start values for the algorithm
        outputNumbers.add(0);
        res.append(fillBinaryToLength(input, intToBinary(0))).append("\n");
        outputNumbers.add(1);
        res.append(fillBinaryToLength(input, intToBinary(1))).append("\n");

        // Can directly jump to second index as the first one is initialy hardcoded
        for (int i = 1; i < input; i++) {
            BigDecimal binaryValue = base.pow(i);
            for (int j = outputNumbers.size() - 1; j >= 0; j--) {
                int number = outputNumbers.get(j) + binaryValue.intValue();
                outputNumbers.add(number);
                res.append(fillBinaryToLength(input, intToBinary(Math.toIntExact(number)))).append("\n");

            }
        }

        System.out.println(res);
    }

    private static String fillBinaryToLength(int length, String binaryNumber) {
        StringBuilder sb = new StringBuilder(binaryNumber);
        while (sb.length() < length) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    private static String intToBinary(int integer) {
        StringBuilder res = new StringBuilder();

        while (integer > 0) {
            float divisionRest = integer / 2f;
            if (divisionRest % 1 > 0) {
                res.append("1");
            } else {
                res.append("0");
            }
            integer = (int) Math.floor(divisionRest);
        }
        return res.reverse().toString();
    }
}
