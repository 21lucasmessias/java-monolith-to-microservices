package com.example.sanctuary.crud.models;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
public class Person {
    @Id
    private UUID Id;
    private String name;
    private int age;
    private Instant birthdate;

    public Person(String name, int age, Instant birthdate) {
        this.name = name;
        this.age = age;
        this.birthdate = birthdate;
    }
}