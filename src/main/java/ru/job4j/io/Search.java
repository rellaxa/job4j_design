package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Must be 2 arguments");
        }
        if (!Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException("Root is not a directory: " + args[0]);
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Pattern must start with \".\"");
        }
    }

    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Path.of(args[0]);
        String pattern = args[1];
        search(start, path -> path.toFile().getName().endsWith(pattern))
                .forEach(System.out::println);
    }
}