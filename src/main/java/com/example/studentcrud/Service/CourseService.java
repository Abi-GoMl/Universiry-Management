package com.example.studentcrud.Service;

import com.example.studentcrud.Entity.Course;
import com.example.studentcrud.Exception.ResourceNotFoundException;
import com.example.studentcrud.Repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getByCourseId(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course Not Found"));
    }


    public Course updateCourse(Long id, Course course) {

        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course Not Found"));

        existingCourse.setName(course.getName());
        existingCourse.setCredits(course.getCredits());
        existingCourse.setSemester(course.getSemester());

        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(Long id) {
        courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course Not Found"));

        courseRepository.deleteById(id);
    }
}

