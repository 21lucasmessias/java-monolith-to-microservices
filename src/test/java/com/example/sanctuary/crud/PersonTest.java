package com.example.sanctuary.crud;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.sanctuary.crud.models.Person;

@SpringBootTest()
public class PersonTest {
    
    @Test
    public void givenValidParamsWhenTryToCreatePersonShouldReturn200() {
        String name = "Lucas";
        int age = 27;
        Instant birthDate = LocalDate.of(1996, 8, 12).atStartOfDay(ZoneId.of("UTC")).toInstant();

        Person person = new Person("Lucas", 27, birthDate);

        assert(person.getName().equals(name));
        assert(person.getAge() == age);
        assert(person.getBirthdate().equals(birthDate));
    }
}
