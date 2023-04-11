package org.example.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Student {

    @Id
    public String id;

    private String name;

    private String surname;

    private int age;

    public Student(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

}
