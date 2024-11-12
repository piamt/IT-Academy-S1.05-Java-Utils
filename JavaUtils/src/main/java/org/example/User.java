package org.example;

public class User implements  java.io.Serializable {

    String name;
    double age;

    public User(String name, double age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyClass: " + "name=" + name + ", age=" + age;
    }
}
