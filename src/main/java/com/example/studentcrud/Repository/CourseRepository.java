package com.example.studentcrud.Repository;

import com.example.studentcrud.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
