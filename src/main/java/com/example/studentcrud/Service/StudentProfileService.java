package com.example.studentcrud.Service;

import com.example.studentcrud.Entity.StudentProfile;
import com.example.studentcrud.Exception.ResourceNotFoundException;
import com.example.studentcrud.Repository.StudentProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;

    public StudentProfileService(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    // Create
    public StudentProfile saveStudentProfile(StudentProfile studentProfile) {
        return studentProfileRepository.save(studentProfile);
    }

    // Read All
    public List<StudentProfile> getAllStudentProfiles() {
        return studentProfileRepository.findAll();
    }

    // Read By Id
    public StudentProfile getById(Long id) {
        return studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student Profile Not Found"));
    }

    // Update
    public StudentProfile updateStudentProfile(Long id, StudentProfile studentProfile) {

        StudentProfile existingStudentProfile = studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student Profile Not Found"));

        existingStudentProfile.setMarks(studentProfile.getMarks());
        existingStudentProfile.setAddress(studentProfile.getAddress());

        return studentProfileRepository.save(existingStudentProfile);
    }

    // Delete
    public void deleteStudentProfile(Long id) {

        studentProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student Profile Not Found"));

        studentProfileRepository.deleteById(id);
    }
}