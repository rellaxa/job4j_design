package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        Path dir = Path.of(cachingDir);
        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException(dir + " is not directory");
        }
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String value;
        Path path = Path.of(cachingDir, key);
        if (!Files.exists(path)) {
            System.out.printf("%s%s", "File does not exist ", path);
        }
        if (!Files.isRegularFile(path)) {
            System.out.printf("%s%s", path, " is not file");
        }
        if (!Files.isReadable(path)) {
            System.out.printf("%s%s", path, " is not available to read");
        }
        try {
            value = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return value;
    }
}
