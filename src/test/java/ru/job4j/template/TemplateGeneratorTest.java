package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class TemplateGeneratorTest {

    @Test
    public void whenCorrectMapThenGetString() {
        String template = "I am a ${name}, Who are ${subject}?";
        Generator generator = new TemplateGenerator();
        Map<String, String> args = new HashMap<>();
        args.put("name", "Relaxa");
        args.put("subject", "you");
        String rsl = generator.produce(template, args);
        String expected = "I am a Relaxa, Who are you?";
        assertThat(rsl).isEqualTo(expected);
    }

    @Test
    public void whenMissingPairInMapThenGetException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Relaxa");
        assertThatThrownBy(() -> new TemplateGenerator().produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("There is no value for template");
    }

    @Test
    public void whenExtraPairInMapThenGetException() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Relaxa");
        args.put("subject", "you");
        args.put("key", "value");
        assertThatThrownBy(() -> new TemplateGenerator().produce(template, args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Extra keys in map");
    }

}