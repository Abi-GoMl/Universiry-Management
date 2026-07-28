package com.example.studentcrud.Mapper;

import com.example.studentcrud.Controller.dto.StudentResponse;
import com.example.studentcrud.Entity.Course;
import com.example.studentcrud.Entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "profileId", source = "studentProfile.id")
    StudentResponse toResponse(Student student);

    default List<Long> courseIds(List<Course> courses) {
        if (courses == null) return null;
        return courses.stream().map(Course::getId).collect(Collectors.toList());
    }

    List<StudentResponse> toResponseList(List<Student> students);
}
