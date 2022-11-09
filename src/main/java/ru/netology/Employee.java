package ru.netology;

import org.jetbrains.annotations.NotNull;

public class Employee {

    private long id;
    private String firstName;
    private String lastName;
    private String country;
    private int age;

    public Employee() {
        // Пустой конструктор
    }

    public Employee(long id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    public Employee(String @NotNull [] attr) {
        this.id = Long.parseLong(attr[0]);
        this.firstName = attr[1];
        this.lastName = attr[2];
        this.country = attr[3];
        this.age = Integer.parseInt(attr[4]);
    }

    public String toString() {
        return "{id=" + this.id
                + ", firstname=" + this.firstName
                + ", lastname=" + this.lastName
                + ", country=" + this.country
                + ", age=" + this.age + "}";
    }
}
