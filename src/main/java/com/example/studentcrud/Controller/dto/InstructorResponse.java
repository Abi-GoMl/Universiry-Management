package com.example.studentcrud.Controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class InstructorResponse {
    private Long id;
    private String name;
    private String email;
    private List<Long> courseIds;
}
