package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class FoolTest {

    @Test
    public void whenNumber9ThenFizz() {
        String rsl = Fool.checkWord(9);
        assertThat(rsl).isEqualTo("Fizz");
    }

    @Test
    public void whenNumber25ThenBuzz() {
        String rsl = Fool.checkWord(25);
        assertThat(rsl).isEqualTo("Buzz");
    }

    @Test
    public void whenNumber30ThenFizzBuzz() {
        String rsl = Fool.checkWord(30);
        assertThat(rsl).isEqualTo("FizzBuzz");
    }

    @Test
    public void whenWrightQuestion() {
        int num = Fool.computeAnswer(9, "Fizz");
        assertThat(num).isEqualTo(10);
    }

    @Test
    public void whenWrongQuestion() {
        int num = Fool.computeAnswer(6, "Buzz");
        assertThat(num).isEqualTo(0);
    }
}