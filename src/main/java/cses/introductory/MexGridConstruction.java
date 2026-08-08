package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MexGridConstruction {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int gridDimension = Integer.parseInt(br.readLine().trim());
        StringBuilder res = new StringBuilder();
        List<List<Integer>> columnHistory = createListWithEmptyArrayLists(gridDimension);

        for (int i = 0; i < gridDimension; i++) {
            List<Integer> rowList = new ArrayList<>();
            for (int j = 0; j < gridDimension; j++) {
                List<Integer> currentColumnList = columnHistory.get(j);
                int smallestPossible = getSmallestPossibleValue(rowList, currentColumnList);
                columnHistory.get(j).add(smallestPossible);
                rowList.add(smallestPossible);
                res.append(smallestPossible).append(" ");
            }
            res.append("\n");
        }

        System.out.println(res);
    }

    private static int getSmallestPossibleValue(List<Integer> rowList, List<Integer> currentColumnList) {
        int smallestPositiveNumberFound = -1;
        int i = 0;
        while(smallestPositiveNumberFound < 0) {
            if (!rowList.contains(i) && !currentColumnList.contains(i)) {
                smallestPositiveNumberFound = i;
            }

            i++;
        }

        return smallestPositiveNumberFound;
    }

    private static List<List<Integer>> createListWithEmptyArrayLists(int gridDimension) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < gridDimension; i++) {
            list.add(new ArrayList<>());
        }

        return list;
    }
}
