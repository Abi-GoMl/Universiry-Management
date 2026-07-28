package com.example.studentcrud.Controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DepartmentRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50)
    private String name;

    private String hod;
}
