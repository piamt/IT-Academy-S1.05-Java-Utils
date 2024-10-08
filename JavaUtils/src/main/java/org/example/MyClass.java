package org.example;

public class MyClass implements  java.io.Serializable {

    String name;
    double age;

    public MyClass(String name, double age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyClass: " + "name=" + name + ", age=" + age;
    }
}
