package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        try {
            // findAllFilesInFolder("./src/main");
            DirectoryContent.findAllFilesRecursiveInFolder("../", "directoryContent.txt");
        } catch (IOException e) {
            System.out.println("I/O exception accessing files");
        }

        DirectoryContent.readTextFile("directoryContent.txt");

        MyClass myClass = new MyClass("Laura", 34);

        DirectoryContent.serialise(myClass, "myclass.ser");
        DirectoryContent.deserialise("myclass.ser");
    }
}