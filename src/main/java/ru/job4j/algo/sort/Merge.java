package ru.job4j.algo.sort;

import java.util.Arrays;

public class Merge {

    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        int leftLength = left.length;
        int rightLength = right.length;
        int[] result = new int[leftLength + rightLength];
        int leftIndex = 0, rightIndex = 0;
        int rslIndex = 0;

        while (leftIndex < leftLength && rightIndex < rightLength) {
            if (left[leftIndex] <= right[rightIndex]) {
                result[rslIndex] = left[leftIndex];
                leftIndex++;
            } else {
                result[rslIndex] = right[rightIndex];
                rightIndex++;
            }
            rslIndex++;
        }

        while (rightIndex < rightLength) {
            result[rslIndex] = right[rightIndex];
            rightIndex++;
            rslIndex++;
        }

        while (leftIndex < leftLength) {
            result[rslIndex] = left[leftIndex];
            leftIndex++;
            rslIndex++;
        }
        return result;
    }
}
