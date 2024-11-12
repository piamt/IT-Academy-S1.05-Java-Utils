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
        DirectoryManager.findAllFilesInFolder("./src/main");

        System.out.println("\n Nivell 1 - Exercici 2 & 3:");
        DirectoryManager.findAllFilesRecursiveInFolder("../", "src/main/files/directoryContent.txt");

        System.out.println("\n Nivell 1 - Exercici 4:");
        DirectoryManager.readTextFile("src/main/files/directoryContent.txt");

        System.out.println("\n Nivell 1 - Exercici 5:");
        User user = new User("Laura", 34);
        Serializer.serialise(user, "src/main/files/user.ser");
        User user2 = Serializer.deserialise("src/main/files/user.ser");
        System.out.println("Deserialised: " + user2.toString());

        System.out.println("\nNivell 2:");
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            Properties appProps = new Properties();
            appProps.load(input);
            String folder = appProps.getProperty("folder");
            String filePath = appProps.getProperty("filePath");
            DirectoryManager.findAllFilesRecursiveInFolder(folder, filePath);
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