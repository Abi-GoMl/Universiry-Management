package com.example.studentcrud.Controller.dto;

import lombok.Data;

@Data
public class StudentProfileResponse {
    private Long id;
    private int marks;
    private String address;
    private Long studentId;
}
