package com.example.studentcrud.Controller.dto;

import lombok.Data;

@Data
public class CourseResponse {
    private Long id;
    private String name;
    private int credits;
    private int semester;
    private Long departmentId;
    private Long instructorId;
}
