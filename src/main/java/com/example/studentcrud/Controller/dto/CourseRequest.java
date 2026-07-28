package com.example.studentcrud.Controller.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseRequest {
    @NotBlank(message = "Course name cannot be blank")
    @Size(min = 3, max = 100)
    private String name;

    @Min(value = 1, message = "Credits must be at least 1")
    private int credits;

    @Min(value = 1, message = "Semester must be at least 1")
    private int semester;

    private Long departmentId;
    private Long instructorId;
}
