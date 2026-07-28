package com.example.studentcrud.Controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentResponse {
    private Long id;
    private String name;
    private String hod;
    private List<Long> studentIds;
    private List<Long> courseIds;
}
