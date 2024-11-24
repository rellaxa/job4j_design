package ru.job4j.algo.sort;

import java.util.*;

public class Intervals {

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(right -> right[0]));
        int[] prev = intervals[0];
        List<int[]> list = new ArrayList<>();
        for (int index = 1; index < intervals.length; index++) {
            int[] cur = intervals[index];
            int endPrev = prev[1];
            int startCur = cur[0];
            int endCur = cur[1];
            if (startCur <= endPrev) {
                prev[1] = Math.max(endPrev, endCur);
            } else {
                list.add(prev);
                prev = cur;
            }
        }
        list.add(prev);
        return list.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 4}, {0, 2}, {3, 5}})));
    }
}
