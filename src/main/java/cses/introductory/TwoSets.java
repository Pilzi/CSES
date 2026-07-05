package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TwoSets {
    public static String NOT_POSSIBLE_OUTPUT = "NO";
    public static String IS_POSSIBLE_OUTPUT = "YES";

    /**
     * The algorithm first divides the input set into two separate lists (odd and even) to limit gap between them.
     * In the next step, the gap between the sums of the two lists is determined.
     *  This must be an even number, since division by two would always result in a floating point number divided by 2.
     *
     * If the number is in the bigger list, it can simply be moved to the smaller list
     * otherwise, the first value of the bigger list is moved to the smaller list, and the algorithm continues.
     *
     * Since the list is already sorted, the number must be somewhere in the list if it is greater than the smallest number in the list.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        long listOddSum = 0L;
        List<Integer> listOdd = new ArrayList<>();
        StringBuilder oddListResult = new StringBuilder();

        long listEvenSum = 0L;
        List<Integer> listEven = new ArrayList<>();
        StringBuilder evenListResult = new StringBuilder();

        // Split the lists in odd and even to have a result to have a list you can work with
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0) {
                listOdd.add(i);
                listOddSum += i;
                oddListResult.append(i).append(" ");
            } else {
                listEven.add(i);
                listEvenSum += i;
                evenListResult.append(i).append(" ");
            }
        }

        long gapBetweenOddAndEvenList = getGapBetween(listOddSum, listEvenSum);

        while (listOddSum != listEvenSum && gapBetweenOddAndEvenList % 2 == 0) {
            // To balance the two numbers, half of the larger list must be added to the smaller list
            long numberSearched = gapBetweenOddAndEvenList / 2;

            if (listEvenSum > listOddSum) {
                Integer firstEvenNumber = listEven.get(0);

                boolean isEven = numberSearched % 2 == 0;
                if (numberSearched >= firstEvenNumber && isEven) {
                    String searchedValueAsString = String.valueOf(numberSearched);
                    int start = evenListResult.indexOf(searchedValueAsString);
                    evenListResult.delete(start, start + searchedValueAsString.length() + 1);
                    oddListResult.append(searchedValueAsString);
                    listEvenSum -= numberSearched;
                    listOddSum += numberSearched;
                    listOdd.add((int) numberSearched);
                    listEven.remove(0);
                } else {
                    listEven.remove(0);
                    evenListResult.delete(0, evenListResult.indexOf(" ") + 1);
                    listOdd.add(firstEvenNumber);
                    oddListResult.append(firstEvenNumber).append(" ");
                    listOddSum += firstEvenNumber;
                    listEvenSum -= firstEvenNumber;
                }

            } else {
                Integer firstOddNumber = listOdd.get(0);

                boolean isOdd = numberSearched % 2 != 0;
                if (numberSearched > firstOddNumber && isOdd) {
                    String searchedValueAsString = String.valueOf(numberSearched);
                    int start = oddListResult.indexOf(searchedValueAsString);
                    oddListResult.delete(start, start + searchedValueAsString.length() + 1);
                    evenListResult.append(searchedValueAsString);
                    listEvenSum += numberSearched;
                    listOddSum -= numberSearched;
                    listEven.add((int) numberSearched);
                    listOdd.remove(0);
                } else {
                    listOdd.remove(0);
                    oddListResult.delete(0, oddListResult.indexOf(" ") + 1);
                    listEven.add(firstOddNumber);
                    evenListResult.append(firstOddNumber).append(" ");
                    listEvenSum += firstOddNumber;
                    listOddSum -= firstOddNumber;
                }
            }

            gapBetweenOddAndEvenList = getGapBetween(listOddSum, listEvenSum);
        }

        // It is impossible for an odd number to balance both sets, since an odd number divided by 2 cannot result in an even number.
        if (gapBetweenOddAndEvenList % 2 != 0) {
            System.out.println(NOT_POSSIBLE_OUTPUT);
        } else {
            System.out.println(IS_POSSIBLE_OUTPUT);
            System.out.println(listEven.size());
            System.out.println(evenListResult.toString().trim());
            System.out.println(listOdd.size());
            System.out.println(oddListResult.toString().trim());
        }
    }

    private static long getGapBetween(long listOddSum, long listEvenSum) {
        if (listOddSum > listEvenSum) {
            return listOddSum - listEvenSum;
        }

        return listEvenSum - listOddSum;
    }
}
