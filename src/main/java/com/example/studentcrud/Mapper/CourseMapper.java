package com.example.studentcrud.Mapper;

import com.example.studentcrud.Controller.dto.CourseResponse;
import com.example.studentcrud.Entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "instructorId", source = "instructor.id")
    CourseResponse toResponse(Course course);

    List<CourseResponse> toResponseList(List<Course> list);
}
