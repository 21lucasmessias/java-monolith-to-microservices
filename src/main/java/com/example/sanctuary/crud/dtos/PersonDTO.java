package com.example.sanctuary.crud.dtos;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonDTO(@NotBlank String name, @NotNull int age, @NotNull Instant birthdate) {
    
}
