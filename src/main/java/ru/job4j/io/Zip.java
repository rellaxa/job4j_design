package ru.job4j.io;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private Path directory;
    private String exclude;
    private String output;

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(input.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkArgs(String[] args) {
        ArgsName names = ArgsName.of(args);
        directory = Path.of(names.get("d"));
        exclude = names.get("e");
        output = names.get("o");

        if (!Files.exists(directory)) {
            throw new IllegalArgumentException("Directory does not exist: " + directory);
        }
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException("Type must start with '.': " + exclude);
        }
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException("Name extension must be .zip: " + output);
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        zip.checkArgs(args);
        zip.packFiles(Search.search(zip.directory, path -> !path.endsWith(zip.exclude)), new File(zip.output));
    }
}