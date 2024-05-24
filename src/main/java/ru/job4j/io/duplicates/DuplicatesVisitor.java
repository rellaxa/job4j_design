package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, Set<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty key = new FileProperty(attributes.size(), file.getFileName().toString());
        map.computeIfAbsent(key, k -> new HashSet<>()).add(file.toAbsolutePath());
        return FileVisitResult.CONTINUE;
    }

    public void printDuplicates() {
        map.entrySet().stream()
                .filter(el -> el.getValue().size() > 1)
                .forEach(el -> {
                    System.out.println(el.getKey().toString());
                    el.getValue().forEach(System.out::println);
                });
    }
}