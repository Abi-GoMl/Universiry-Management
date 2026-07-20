package com.example.studentcrud.Repository;

import com.example.studentcrud.Entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile,Long> {
}
