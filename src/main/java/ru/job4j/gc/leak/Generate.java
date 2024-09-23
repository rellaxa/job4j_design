package ru.job4j.gc.leak;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Generate {

    void generate();

    default List<String> read(String path)  {
        List<String> text = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines()
                    .forEach(text::add);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return text;
    }
}
