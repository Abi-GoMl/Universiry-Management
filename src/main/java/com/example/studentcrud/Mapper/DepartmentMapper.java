package com.example.studentcrud.Mapper;

import com.example.studentcrud.Controller.dto.DepartmentResponse;
import com.example.studentcrud.Entity.Department;
import com.example.studentcrud.Entity.Student;
import com.example.studentcrud.Entity.Course;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentResponse toResponse(Department department);

    default List<Long> studentIds(List<Student> students) {
        if (students == null) return null;
        return students.stream().map(Student::getId).collect(Collectors.toList());
    }

    default List<Long> courseIds(List<Course> courses) {
        if (courses == null) return null;
        return courses.stream().map(Course::getId).collect(Collectors.toList());
    }

    List<DepartmentResponse> toResponseList(List<Department> list);
}
