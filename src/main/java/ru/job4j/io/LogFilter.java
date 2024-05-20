package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
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

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)
                ))) {
            for (String line : data) {
                output.printf("%s%n", line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}
