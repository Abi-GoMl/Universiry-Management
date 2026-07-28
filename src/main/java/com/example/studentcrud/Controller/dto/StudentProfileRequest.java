package com.example.studentcrud.Controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class StudentProfileRequest {
    @Min(value = 0, message = "Marks cannot be less than 0")
    @Max(value = 100, message = "Marks cannot be greater than 100")
    private int marks;

    private String address;

    private Long studentId;
}
