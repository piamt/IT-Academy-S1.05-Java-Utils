package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.nio.file.Files.list;

public class DirectoryContent {

    //Exercise 1
    public static void findAllFilesInFolder(String folder) throws IOException {
        try (var files = list(Paths.get(folder))) {
            files.sorted().forEach(System.out::println);
        }
    }

    //Exercise 2 & 3
    public static void findAllFilesRecursiveInFolder(String folder, String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        Path path = Paths.get(filePath);

        try (var files = Files.walk(Paths.get(folder))) {
            files.sorted().forEach(a -> {
                String line = outputString(a);
                System.out.println(line);
                lines.add(line);
            });
        }

        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    static String outputString(Path path) throws NumberFormatException {
        String directoryOrFile = (Files.isDirectory(path)) ? " (D) " : " (F) ";
        String lastModified = null;
        try {
            lastModified = Files.getLastModifiedTime(path).toString();
        } catch (IOException e) {
            throw new NullPointerException(); // TODO: Update with another exception
        }

        return path + directoryOrFile + "Last modified: " + lastModified;
    }

    // Exercise 4
    public static void readTextFile(String filePath) {
        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Read text file failed");
        } finally {
            for (String line : list) {
                System.out.println(line);
            }
        }
    }

    public static void serialise(MyClass myClass, String fileName) { //myclass.ser
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(myClass);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in /tmp/" + fileName);
        } catch (IOException e) {
            System.out.println("Serialisation could not be completed");
        }
    }

    public static MyClass deserialise(String fileName) {
        MyClass myClass = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            myClass = (MyClass) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            System.out.println("Deserialisation could not be completed");
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("MyClass class not found");
            return null;
        } finally {
            System.out.println(Objects.requireNonNull(myClass).toString());
            return myClass;
        }
    }
}
