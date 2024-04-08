package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("sphere");
    }

    @Test
    void numberOfVerticesOfCubeIs8() {
        Box box = new Box(8, 15);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(8)
                .isPositive()
                .isEven()
                .isGreaterThan(1)
                .isLessThan(10);
    }

    @Test
    void isThisExist() {
        Box box = new Box(10, 1);
        boolean exist = box.isExist();
        assertThat(exist).isFalse();
    }

    @Test
    void areaOfTetrahedron() {
        Box box = new Box(4,  6);
        double area = box.getArea();
        assertThat(area).isEqualTo(62.36, withPrecision(0.008))
                .isCloseTo(62.34, withPrecision(0.015))
                .isGreaterThan(62.353)
                .isLessThan(63);
    }
}