package com.example.studentcrud.Controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentResponse {
    private Long id;
    private String name;
    private int age;
    private String email;
    private String phoneNumber;
    private Long departmentId;
    private Long profileId;
    private List<Long> courseIds;
}
