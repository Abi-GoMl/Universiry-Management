package com.example.studentcrud.Mapper;

import com.example.studentcrud.Controller.dto.*;
import com.example.studentcrud.Entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntityToResponseMapper {

    public static StudentResponse toStudentResponse(Student s) {
        if (s == null) return null;
        StudentResponse r = new StudentResponse();
        r.setId(s.getId());
        r.setName(s.getName());
        r.setAge(s.getAge());
        r.setEmail(s.getEmail());
        r.setPhoneNumber(s.getPhoneNumber());
        r.setDepartmentId(s.getDepartment() != null ? s.getDepartment().getId() : null);
        r.setProfileId(s.getStudentProfile() != null ? s.getStudentProfile().getId() : null);
        if (s.getCourses() != null) {
            r.setCourseIds(s.getCourses().stream().map(Course::getId).collect(Collectors.toList()));
        }
        return r;
    }

    public static CourseResponse toCourseResponse(Course c) {
        if (c == null) return null;
        CourseResponse r = new CourseResponse();
        r.setId(c.getId());
        r.setName(c.getName());
        r.setCredits(c.getCredits());
        r.setSemester(c.getSemester());
        r.setDepartmentId(c.getDepartment() != null ? c.getDepartment().getId() : null);
        r.setInstructorId(c.getInstructor() != null ? c.getInstructor().getId() : null);
        return r;
    }

    public static DepartmentResponse toDepartmentResponse(Department d) {
        if (d == null) return null;
        DepartmentResponse r = new DepartmentResponse();
        r.setId(d.getId());
        r.setName(d.getName());
        r.setHod(d.getHod());
        if (d.getStudents() != null) {
            r.setStudentIds(d.getStudents().stream().map(Student::getId).collect(Collectors.toList()));
        }
        if (d.getCourse() != null) {
            r.setCourseIds(d.getCourse().stream().map(Course::getId).collect(Collectors.toList()));
        }
        return r;
    }

    public static InstructorResponse toInstructorResponse(Instructor i) {
        if (i == null) return null;
        InstructorResponse r = new InstructorResponse();
        r.setId(i.getId());
        r.setName(i.getName());
        r.setEmail(i.getEmail());
        if (i.getCourses() != null) {
            r.setCourseIds(i.getCourses().stream().map(Course::getId).collect(Collectors.toList()));
        }
        return r;
    }

    public static StudentProfileResponse toStudentProfileResponse(StudentProfile p) {
        if (p == null) return null;
        StudentProfileResponse r = new StudentProfileResponse();
        r.setId(p.getId());
        r.setMarks(p.getMarks());
        r.setAddress(p.getAddress());
        r.setStudentId(p.getStudent() != null ? p.getStudent().getId() : null);
        return r;
    }

    public static List<StudentResponse> toStudentResponseList(List<Student> list) {
        if (list == null) return new ArrayList<>();
        return list.stream().map(EntityToResponseMapper::toStudentResponse).collect(Collectors.toList());
    }

    public static List<CourseResponse> toCourseResponseList(List<Course> list) {
        if (list == null) return new ArrayList<>();
        return list.stream().map(EntityToResponseMapper::toCourseResponse).collect(Collectors.toList());
    }

    public static List<DepartmentResponse> toDepartmentResponseList(List<Department> list) {
        if (list == null) return new ArrayList<>();
        return list.stream().map(EntityToResponseMapper::toDepartmentResponse).collect(Collectors.toList());
    }

    public static List<InstructorResponse> toInstructorResponseList(List<Instructor> list) {
        if (list == null) return new ArrayList<>();
        return list.stream().map(EntityToResponseMapper::toInstructorResponse).collect(Collectors.toList());
    }

    public static List<StudentProfileResponse> toStudentProfileResponseList(List<StudentProfile> list) {
        if (list == null) return new ArrayList<>();
        return list.stream().map(EntityToResponseMapper::toStudentProfileResponse).collect(Collectors.toList());
    }
}
