package cses.introductory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Stack;

public class TowerOfHanoi {
    public static final String SPACE = " ";
    public static final String LINE_BREAK = "\n";
    public static StringBuilder res = new StringBuilder();
    public static long stepCount = 0L;

    /**
     * A. Disk 1 moves first. It moves to C if the number of disks is odd, B if the number of disks is even.
     * B. Subsequent moves alternates between another disk and disk 1. If another disk moves (not disk 1), there is only 1 possible move.
     *    When disk 1 moves, it goes on top of the biggest even-sized disk (an empty tower is considered having 0 sized 'even' disk).
     * C. Stop when towers A and B are empty.
     * <p>
     * I guess the basis for the provided recursive solution is not really mathematical but based on a pattern. Any idea how the recursive solution is conceptualized?
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int disks = Integer.parseInt(br.readLine().trim());

        Stack<Integer> stackA = new Stack<>();
        Stack<Integer> stackB = new Stack<>();
        Stack<Integer> stackC = new Stack<>();

        List<Stack<Integer>> possibleStacks = List.of(stackA, stackB, stackC);

        for (int i = disks; i > 0; i--) {
            stackA.push(i);
        }

        Integer topValue = stackA.pop();

        Stack<Integer> smallestDiskStack;
        if (stackA.size() % 2 == 0) {
            stackC.push(topValue);
            smallestDiskStack = stackC;
        } else {
            stackB.push(topValue);
            smallestDiskStack = stackB;
        }

        getAndPrintNextSolution(possibleStacks, stackA, smallestDiskStack);
        stepCount++;

        // The smallest disk must be involved in each second step
        boolean moveSmallest = false;

        while (!stackA.isEmpty() || !stackB.isEmpty()) {
            if (moveSmallest) {
                int stackAEven = getValidMoveWeightForSmallestDisk(stackA);
                int stackBEven = getValidMoveWeightForSmallestDisk(stackB);
                int stackCEven = getValidMoveWeightForSmallestDisk(stackC);

                Integer smallestDisk = smallestDiskStack.pop();

                if (stackAEven > stackBEven && stackAEven > stackCEven) {
                    stackA.push(smallestDisk);
                    getAndPrintNextSolution(possibleStacks, smallestDiskStack, stackA);
                    stepCount++;
                    smallestDiskStack = stackA;
                } else if (stackBEven > stackAEven && stackBEven > stackCEven) {
                    stackB.push(smallestDisk);
                    getAndPrintNextSolution(possibleStacks, smallestDiskStack, stackB);
                    stepCount++;
                    smallestDiskStack = stackB;
                } else {
                    stackC.push(smallestDisk);
                    getAndPrintNextSolution(possibleStacks, smallestDiskStack, stackC);
                    stepCount++;
                    smallestDiskStack = stackC;
                }
            } else {
                // If the smallest disk is not involved the next move must be bruteforced
                for (int i = 0; i < possibleStacks.size(); i++) {
                    Stack<Integer> currentStack = possibleStacks.get(i);
                    Stack<Integer> possibleStackA = possibleStacks.get((i + 1) % 3);
                    Stack<Integer> possibleStackB = possibleStacks.get((i + 2) % 3);
                    if (isValidMoveForDisk(currentStack, possibleStackA)) {
                        Integer currentStackFirst = currentStack.pop();
                        possibleStackA.push(currentStackFirst);
                        getAndPrintNextSolution(possibleStacks, currentStack, possibleStackA);
                        stepCount++;
                        break;
                    } else if (isValidMoveForDisk(currentStack, possibleStackB)) {
                        Integer currentStackFirst = currentStack.pop();
                        getAndPrintNextSolution(possibleStacks, currentStack, possibleStackB);
                        stepCount++;
                        possibleStackB.push(currentStackFirst);
                        break;
                    }
                }
            }

            moveSmallest = !moveSmallest;
        }

        System.out.println(stepCount);
        System.out.println(res);
    }

    private static void getAndPrintNextSolution(List<Stack<Integer>> possibleStacks,
                                                Stack<Integer> smallestDiskStack,
                                                Stack<Integer> stackC) {
        res.append(getStackIndex(possibleStacks, smallestDiskStack))
                .append(SPACE)
                .append(getStackIndex(possibleStacks, stackC))
                .append(LINE_BREAK);
    }

    private static int getStackIndex(List<Stack<Integer>> possibleStacks, Stack<Integer> smallestDiskStack) {
        for (int i = 0; i < possibleStacks.size(); i++) {
            if (possibleStacks.get(i) == smallestDiskStack) {
                return i + 1;
            }
        }
        throw new RuntimeException("Stack not found in provided list");
    }

    /**
     * Weights the stack to see if the smallest disk should move here.
     *
     * If it's empty, return 1 (it's a valid move, but not the best one).
     * If the stack size is odd, that's our target, so return 2 (the highest value).
     * Anything else is unlikely to be the move, so just return 0.
     */
    public static int getValidMoveWeightForSmallestDisk(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return 1;
        }

        if (stack.get(stack.size() - 1) == 1) {
            return 0;
        }

        Integer last = stack.get(stack.size() - 1);

        if (last % 2 == 0) {
            return 2;
        }

        return 0;
    }

    /**
     * Checks if it is possible to move the disk from current stack to target stack
     */
    public static boolean isValidMoveForDisk(Stack<Integer> currentStack, Stack<Integer> targetStack) {
        if (targetStack.isEmpty()) {
            return true;
        }
        if (currentStack.isEmpty() || currentStack.get(currentStack.size() - 1) == 1) {
            return false;
        }

        return targetStack.get(targetStack.size() - 1) > currentStack.get(currentStack.size() - 1);
    }
}
