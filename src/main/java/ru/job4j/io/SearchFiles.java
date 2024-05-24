package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFiles extends SimpleFileVisitor<Path> {

    private Predicate<Path> condition;
    private List<Path> matchedFiles = new ArrayList<>();

    public SearchFiles(Predicate<Path> condition) {
        this.condition = condition;
    }

    public List<Path> getPaths() {
        return matchedFiles;
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        if (condition.test(file)) {
            matchedFiles.add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }
}
