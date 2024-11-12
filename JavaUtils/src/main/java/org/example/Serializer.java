package org.example;

import java.io.*;

public class Serializer {

    public static void serialise(User user, String fileName) { //myclass.ser
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(user);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in /tmp/" + fileName);
        } catch (IOException e) {
            System.out.println("Serialisation could not be completed");
        }
    }

    public static User deserialise(String fileName) {
        User user = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            user = (User) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            System.out.println("Deserialisation could not be completed");
            return user;
        } catch (ClassNotFoundException c) {
            System.out.println("MyClass class not found");
            return user;
        }
        return user;
    }
}