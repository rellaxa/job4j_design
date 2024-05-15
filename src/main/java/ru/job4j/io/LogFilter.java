package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            for (String str : input.lines().toList()) {
                String[] words = str.split(" ");
                if ("404".equals(words[words.length - 2])) {
                    lines.add(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}
