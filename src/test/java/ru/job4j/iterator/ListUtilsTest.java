package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 2, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIf() {
        Predicate<Integer> predicate = el -> el == 3;
        ListUtils.removeIf(input, predicate);
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void whenReplaceIF() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, -2, 6, 11, 8, -4, 999));
        ListUtils.replaceIf(list, el -> el == -4, 666);
        assertThat(list).hasSize(7).containsSequence(1, -2, 6, 11, 8, 666, 999);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> list = new ArrayList<>(Arrays.asList(11, -12, 100, -80, -76, 31, -43, 28, 35, -82));
        List<Integer> elements = new ArrayList<>(Arrays.asList(-12, -80, -76, -43, -82));
        ListUtils.removeAll(list, elements);
        assertThat(list).hasSize(5).containsSequence(11, 100, 31, 28, 35);
    }
}