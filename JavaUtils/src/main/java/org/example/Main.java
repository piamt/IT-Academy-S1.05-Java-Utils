package org.example;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        System.out.println("Nivell 1 - Exercici 1:");
        DirectoryContent.findAllFilesInFolder("./src/main");

        System.out.println("\n Nivell 1 - Exercici 2 & 3:");
        DirectoryContent.findAllFilesRecursiveInFolder("../", "src/main/files/directoryContent.txt");

        System.out.println("\n Nivell 1 - Exercici 4:");
        DirectoryContent.readTextFile("src/main/files/directoryContent.txt");

        System.out.println("\n Nivell 1 - Exercici 5:");
        MyClass myClass = new MyClass("Laura", 34);
        DirectoryContent.serialise(myClass, "src/main/files/myclass.ser");
        MyClass myClass2 = DirectoryContent.deserialise("src/main/files/myclass.ser");
        System.out.println("Deserialised: " + myClass2.toString());

        System.out.println("\nNivell 2:");
        try (InputStream input = Objects.requireNonNull(MyClass.class.getClassLoader().getResource("config.properties")).openStream()) {
            Properties appProps = new Properties();
            appProps.load(input);
            String folder = appProps.getProperty("folder");
            String filePath = appProps.getProperty("filePath");
            DirectoryContent.findAllFilesRecursiveInFolder(folder, filePath);
        } catch (IOException|NullPointerException e) {
            System.out.println("Exception accessing files");
        }

        System.out.println("\nNivell 3: String encrypted & decrypted (no es demana però volia provar");
        SecretKey secretKey = null;
        IvParameterSpec ivParameterSpec = EncryptionUtil.generateIv();;
        String input = "testing some string to encrypt";
        String algorithm = "AES/CBC/PKCS5Padding";

        try {
            secretKey = EncryptionUtil.generateKey(128);
            String cipherText = EncryptionUtil.encrypt(algorithm, input, secretKey, ivParameterSpec);
            String plainText = EncryptionUtil.decrypt(algorithm, cipherText, secretKey, ivParameterSpec);
            System.out.println("cipherText: " + cipherText + " plainText: " + plainText);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            if (secretKey == null) {
                System.out.println("Generating secret key failed");
            } else {
                System.out.println("Encryption or decryption failed");
            }
        }

        System.out.println("\nNivell 3: File encrypt & decrypt");
        File inputFile = new File("src/main/files/directoryContent.txt");
        File encryptedFile = new File("src/main/files/directoryContent.encrypted");
        File decryptedFile = new File("src/main/files/directoryContentDecrypted.txt");

        try {
            EncryptionUtil.encryptFile(algorithm, secretKey, ivParameterSpec, inputFile, encryptedFile);
        } catch (InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | IOException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            System.out.println("Encryption of file failed");
        }

        try {
            EncryptionUtil.decryptFile(algorithm, secretKey, ivParameterSpec, encryptedFile, decryptedFile);
        } catch (InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | IOException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            System.out.println("Decryption of file failed");
        }
    }
}