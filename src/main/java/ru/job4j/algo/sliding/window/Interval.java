package ru.job4j.algo.sliding.window;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Interval {

    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", start, end);
    }

    public static class Main {

        public static int[] findMaxOverlapInterval(List<Interval> intervals) {
            if (intervals.isEmpty()) {
                return new int[]{-1, -1};
            }
            var activeIntervals = new PriorityQueue<Interval>(Comparator.comparingInt(i -> i.end));
            int maxOverlap = 0;
            int maxStart = -1;
            int maxEnd = -1;
            for (Interval interval : intervals) {
                activeIntervals.add(interval);
                while (activeIntervals.element().end <= interval.start) {
                    activeIntervals.poll();
                }
                if (maxOverlap < activeIntervals.size()) {
                    maxOverlap = activeIntervals.size();
                    maxStart = interval.start;
                    maxEnd = activeIntervals.element().end;
                }
            }
            return new int[]{maxStart, maxEnd};
        }

        public static void main(String[] args) {
            List<Interval> intervals = new ArrayList<>();
            intervals.add(new Interval(1, 4));
            intervals.add(new Interval(2, 4));
            intervals.add(new Interval(4, 7));
            intervals.add(new Interval(5, 7));
            intervals.add(new Interval(6, 7));
            intervals.add(new Interval(8, 10));
            int[] result = findMaxOverlapInterval(intervals);
            System.out.println("Interval that overlaps the maximum number of intervals: ["
                    + result[0] + ", " + result[1] + "]");
        }
    }
}
