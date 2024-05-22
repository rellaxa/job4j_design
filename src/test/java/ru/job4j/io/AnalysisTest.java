package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {

    @Test
    void unavailable(@TempDir Path path) throws IOException {
        File source = path.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(new FileWriter(source))) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("300 10:59:01");
        }

        File target = path.resolve("target.csv").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(result::append);
        }
        assertThat("10:57:01;10:59:01;").hasToString(result.toString());
    }
}