package com.example.studentcrud.Mapper;

import com.example.studentcrud.Controller.dto.StudentProfileResponse;
import com.example.studentcrud.Entity.StudentProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentProfileMapper {

    @Mapping(target = "studentId", source = "student.id")
    StudentProfileResponse toResponse(StudentProfile profile);

    List<StudentProfileResponse> toResponseList(List<StudentProfile> list);
}
