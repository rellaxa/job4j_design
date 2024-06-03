package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class CSVReader {
    private static Path path;
    private static String delimiter;
    private static String out;
    private static String filter;

    public static void handle(ArgsName argsName) throws IOException {
        checkArgs(argsName);
        List<List<String>> table = readLines();
        List<Integer> indexes = getIndexes(table.get(0));
        List<String> result = new ArrayList<>();

        for (List<String> line : table) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < indexes.size(); i++) {
                String value = line.get(indexes.get(i));
                builder.append(value).append(
                        i < indexes.size() - 1 ? delimiter : System.lineSeparator()
                );
            }
            result.add(builder.toString());
        }
        printOut(result);
    }


    private static List<List<String>> readLines() {
        List<List<String>> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(path.toFile())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(Arrays.asList(line.split(delimiter)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static List<Integer> getIndexes(List<String> line) {
        List<Integer> indexes = new ArrayList<>();
        String[] filters = filter.split(",");
        for (String filter : filters) {
            int index = line.indexOf(filter);
            indexes.add(index);
        }
        return indexes;
    }

    private static void printOut(List<String> result) {
        if ("stdout".equals(out)) {
            result.forEach(System.out::print);
        } else {
            try (FileWriter writer = new FileWriter(out)) {
                for (String str : result) {
                    writer.write(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkArgs(ArgsName argsName) {
        path = Path.of(argsName.get("path"));
        delimiter = argsName.get("delimiter");
        out = argsName.get("out");
        filter = argsName.get("filter");

        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Path " + path + " does not exist");
        }
        if (delimiter.length() != 1) {
            throw new IllegalArgumentException("Delimiter must be a single character");
        }
        if ((!"stdout".equals(out)) && (!out.contains(".csv"))) {
            throw new IllegalArgumentException("Output file " + out + " does not exist");
        }
        String[] strings = filter.split(",");
        try (var scanner = new Scanner(new FileReader(path.toFile()))) {
            String file = scanner.nextLine();
            for (String line : strings) {
                if (!file.contains(line)) {
                    throw new IllegalArgumentException("Table: " + file + " does not contain: " + line + " for filter");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Must be 3 arguments");
        }
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
