package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.list;

public class DirectoryManager {

    public static void findAllFilesInFolder(String folder) {
        try (var files = list(Paths.get(folder))) {
            files.sorted().forEach(System.out::println);
        } catch (InvalidPathException e) {
            System.out.println("Incorrect directory");
        } catch (IOException e) {
            System.out.println("Could not access the directory");
        }
    }

    public static void findAllFilesRecursiveInFolder(String folder, String filePath) {
        List<String> lines = new ArrayList<>();
        Path path = Paths.get(filePath);

        try (var files = Files.walk(Paths.get(folder))) {
            files.sorted().forEach(a -> {
                String line = outputString(a);
                System.out.println(line);
                lines.add(line);
            });
        } catch (InvalidPathException e) {
            System.out.println("Incorrect directory");
        } catch (SecurityException | IOException e) {
            System.out.println("Could not access the directory");
        }

        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Could not write in file " + filePath);
        }
    }

    static String outputString(Path path) {
        String directoryOrFile = (Files.isDirectory(path)) ? " (D) " : " (F) ";
        String lastModified = null;
        try {
            lastModified = Files.getLastModifiedTime(path).toString();
        } catch (IOException e) {
            System.out.println("Tried to access path but not found: " + path.toString());
        }

        return path + directoryOrFile + "Last modified: " + lastModified;
    }

    public static void readTextFile(String filePath) {
        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Read text file failed for file: " + filePath);
        }

        for (String line : list) {
            System.out.println(line);
        }
    }
}
