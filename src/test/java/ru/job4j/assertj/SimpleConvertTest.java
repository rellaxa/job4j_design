package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "third", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "third", "four", "five");
        assertThat(list)
                .startsWith("first")
                .endsWith("four", "five")
                .containsExactlyInAnyOrder("first", "second", "third", "four", "five")
                .containsSequence("second", "third")
                .first().isEqualTo("first");

        assertThat(list).element(2).isNotNull()
                .isEqualTo("third");
        assertThat(list).last().isNotNull()
                .isEqualTo("five");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "third", "four", "five");
        assertThat(set).isNotNull()
                .allSatisfy(e -> {
                    assertThat(e).isGreaterThan("a");
                    assertThat(e).isLessThan("z");
                })
                .allMatch(e -> e.length() >= 4)
                .anyMatch(e -> e.charAt(2) == 'v')
                .noneMatch(String::isEmpty);

        assertThat(set).filteredOn(e -> e.charAt(0) == 'f').containsOnly("first", "four", "five");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "third", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("second", "third", "four")
                .containsValues(1, 2, 3)
                .doesNotContainKey("zero")
                .doesNotContainValue(6)
                .containsEntry("second", 1);
    }
}