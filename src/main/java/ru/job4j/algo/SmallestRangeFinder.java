package ru.job4j.algo;

import java.util.Arrays;

public class SmallestRangeFinder {

    public static int[] findSmallestRange(int[] nums, int k) {
        int length = nums.length;
        int left = 0, right = 1;
        int counter = 1;
        int[] range = new int[]{left, right};
        while (right < length) {
            if (nums[left] == nums[right]) {
                range[0] = right;
                counter = 0;
            }
            left++;
            counter++;
            right++;
            if (counter == k) {
                range[1] = right - 1;
                return range;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 3, 5, 6, 7};
        int k = 1;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
