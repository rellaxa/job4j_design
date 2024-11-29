package ru.job4j.algo.sort;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MergeTest {

    @Test
    void whenSortedThenOk() {
        int[] array = {10, 4, 6, 4, 8, -13, 2, 3};
        assertThat(Merge.mergesort(array)).containsExactly(-13, 2, 3, 4, 4, 6, 8, 10);
    }

    @Test
    void whenDuplicatesThenOk() {
        int[] array = {1, 1, 1, 3, 3, 3, 0, 0};
        assertThat(Merge.mergesort(array)).containsExactly(0, 0, 1, 1, 1, 3, 3, 3);
    }

    @Test
    void whenDuplicatesOfOneSymbol() {
        int[] array = {1, 1, 1, 1};
        assertThat(Merge.mergesort(array)).containsExactly(1, 1, 1, 1);
    }

    @Test
    void whenOneSymbolThenOneSymbol() {
        int[] array = {363};
        assertThat(Merge.mergesort(array)).containsExactly(363);
    }

    @Test
    void whenEmptyArrayThenEmpty() {
        int[] array = {};
        assertThat(Merge.mergesort(array)).isEmpty();
    }
}