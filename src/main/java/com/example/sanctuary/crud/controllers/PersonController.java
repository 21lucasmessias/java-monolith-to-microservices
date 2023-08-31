package com.example.sanctuary.crud.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.sanctuary.crud.dtos.PersonDTO;
import com.example.sanctuary.crud.models.Person;
import com.example.sanctuary.crud.repositories.PersonRepository;

import jakarta.validation.Valid;

@Controller("/person")
public class PersonController {

    @Autowired()
    PersonRepository personRepository;

    @GetMapping()
    public ResponseEntity<List<Person>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> findPersonById(@PathVariable(value="id") UUID id) {
        Optional<Person> person = personRepository.findById(id);

        if (person.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(person.get());
    }    

    @PostMapping()
    public ResponseEntity<Person> createPerson(@Valid @RequestBody PersonDTO dto) {
        var person = new Person();

        BeanUtils.copyProperties(dto, person);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(personRepository.save(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePerson(@PathVariable(value="id") UUID id, @Valid @RequestBody PersonDTO dto) {
        Optional<Person> personOptional = personRepository.findById(id);

        if (personOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found!");
        }

        Person person = personOptional.get();
        BeanUtils.copyProperties(dto, person);
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.save(person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePerson(@PathVariable(value="id") UUID id) {
        Optional<Person> person = personRepository.findById(id);

        if (person.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found!");
        }
        personRepository.delete(person.get());
        return ResponseEntity.status(HttpStatus.OK).body("Person deleted successfully.");
    }
}
