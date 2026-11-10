package cses.sortingandsearching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JosephusProblem2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        String[] firstInputLine = br.readLine().split(" ");
        int circleLength = Integer.parseInt(firstInputLine[0]);
        int totalSteps = Integer.parseInt(firstInputLine[1]);

        int size = Math.max(Integer.highestOneBit(circleLength - 1) << 1, 1);
        int toSkip = size - circleLength;
        int[] segmentedTree = new int[size * 2];

        for (int i = segmentedTree.length - 1 - toSkip; i >= circleLength + toSkip; i--) {
            segmentedTree[i] = 1;
        }

        int treePosition = (circleLength - 1) + toSkip;
        for (int i = segmentedTree.length - 2; i >= 2; i -= 2) {
            segmentedTree[treePosition] = segmentedTree[i] + segmentedTree[i + 1];
            treePosition--;
        }

        int index = 0;
        while (segmentedTree[1] > 0) {
            index = (index + totalSteps) % segmentedTree[1];

            int node = removeNextNodeAlive(segmentedTree, index + 1, size);
            sb.append(node).append(" ");
        }

        System.out.println(sb);
    }

    private static int removeNextNodeAlive(int[] segmentedTree,
                                           int searched,
                                           int size) {
        int index = 1;
        segmentedTree[1] = segmentedTree[1] - 1;

        while (index < size) {
            if (segmentedTree[2 * index] >= searched) {
                index = 2 * index;
            } else {
                searched = searched - segmentedTree[2 * index];
                index = 2 * index + 1;
            }
            segmentedTree[index] = segmentedTree[index] - 1;
        }

        return index - size + 1;
    }
}
