package ru.job4j.algo.sliding.window;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class IntervalTest {

    @Test
    public void whenIntervalsOverlapThenFindMaxOverlapInterval() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(7, 8));

        int[] result = Interval.Main.findMaxOverlapInterval(intervals);
        assertThat(result).containsExactly(3, 4);
    }

    @Test
    public void whenSequentialIntervalsThenFindMaxOverlapInterval() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 4));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(4, 6));

        int[] result = Interval.Main.findMaxOverlapInterval(intervals);
        assertThat(result).containsExactly(2, 3);
    }

    @Test
    public void whenNonOverlappingIntervalsThenFindMaxOverlapInterval() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 10));
        intervals.add(new Interval(2, 3));
        intervals.add(new Interval(4, 5));
        intervals.add(new Interval(6, 7));
        intervals.add(new Interval(8, 9));

        int[] result = Interval.Main.findMaxOverlapInterval(intervals);
        assertThat(result).containsExactly(2, 3);
    }

    @Test
    public void whenSecondOverlapMoreThenFirstThenFindMaxOverlapInterval() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 4));
        intervals.add(new Interval(2, 4));
        intervals.add(new Interval(4, 7));
        intervals.add(new Interval(5, 7));
        intervals.add(new Interval(6, 7));
        intervals.add(new Interval(8, 10));

        int[] result = Interval.Main.findMaxOverlapInterval(intervals);
        assertThat(result).containsExactly(6, 7);
    }

    @Test
    public void whenSingleIntervalThenFindMaxOverlapInterval() {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 10));

        int[] result = Interval.Main.findMaxOverlapInterval(intervals);
        assertThat(result).containsExactly(1, 10);
    }

    @Test
    public void whenNoIntervalsThenFindMaxOverlapInterval() {
        List<Interval> intervals = new ArrayList<>();

        int[] result = Interval.Main.findMaxOverlapInterval(intervals);
        assertThat(result).containsExactly(-1, -1);
    }

}