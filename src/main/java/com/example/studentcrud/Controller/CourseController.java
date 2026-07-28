package com.example.studentcrud.Controller;

import com.example.studentcrud.Controller.dto.CourseRequest;
import com.example.studentcrud.Controller.dto.CourseResponse;
import com.example.studentcrud.Entity.Course;
import com.example.studentcrud.Entity.Department;
import com.example.studentcrud.Entity.Instructor;
import com.example.studentcrud.Service.CourseService;
import com.example.studentcrud.Mapper.CourseMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseMapper courseMapper;

    @PostMapping
    public ResponseEntity<CourseResponse> saveCourse(@Valid @RequestBody CourseRequest req) {
        Course course = new Course();
        course.setName(req.getName());
        course.setCredits(req.getCredits());
        course.setSemester(req.getSemester());
        if (req.getDepartmentId() != null) {
            Department d = new Department(); d.setId(req.getDepartmentId()); course.setDepartment(d);
        }
        if (req.getInstructorId() != null) {
            Instructor i = new Instructor(); i.setId(req.getInstructorId()); course.setInstructor(i);
        }
        Course savedCourse = courseService.saveCourse(course);
        return new ResponseEntity<>(courseMapper.toResponse(savedCourse), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return new ResponseEntity<>(courseMapper.toResponseList(courseService.getAllCourses()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(courseMapper.toResponse(courseService.getByCourseId(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Long id,
                                               @Valid @RequestBody CourseRequest req) {
        Course course = new Course();
        course.setName(req.getName());
        course.setCredits(req.getCredits());
        course.setSemester(req.getSemester());
        if (req.getDepartmentId() != null) {
            Department d = new Department(); d.setId(req.getDepartmentId()); course.setDepartment(d);
        }
        if (req.getInstructorId() != null) {
            Instructor i = new Instructor(); i.setId(req.getInstructorId()); course.setInstructor(i);
        }
        return new ResponseEntity<>(courseMapper.toResponse(courseService.updateCourse(id, course)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
