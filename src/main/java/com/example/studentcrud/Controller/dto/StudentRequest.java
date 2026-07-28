package com.example.studentcrud.Controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentRequest {
    @NotBlank(message = "name must not be blank")
    @Size(min = 3, max = 50)
    private String name;

    @Email(message = "email must be valid")
    private String email;

    @Min(value = 18, message = "age must be at least 18")
    private int age;
}
