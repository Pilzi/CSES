package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JosephusProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int circleLength = Integer.parseInt(br.readLine());
        List<Integer> inputList = new ArrayList<>();

        for (int i = 1; i <= circleLength; i++) {
            if (i % 2 == 0) {
                sb.append(i).append(" ");
            } else {
                inputList.add(i);
            }
        }

        int index = circleLength % 2 == 0 ? 0 : -1;

        while (!inputList.isEmpty()) {
            index = (index + 1) % (inputList.size());

            sb.append(inputList.get(index)).append(" ");
            inputList.remove(index);
        }

        System.out.println(sb);
    }
}
