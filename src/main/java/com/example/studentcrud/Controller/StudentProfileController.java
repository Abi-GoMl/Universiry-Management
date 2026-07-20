package com.example.studentcrud.Controller;

import com.example.studentcrud.Entity.StudentProfile;
import com.example.studentcrud.Service.StudentProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentprofiles")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @PostMapping
    public ResponseEntity<StudentProfile> saveStudentProfile(@Valid @RequestBody StudentProfile studentProfile) {
        StudentProfile savedStudentProfile = studentProfileService.saveStudentProfile(studentProfile);
        return new ResponseEntity<>(savedStudentProfile, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAllStudentProfiles() {
        return new ResponseEntity<>(studentProfileService.getAllStudentProfiles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getById(@PathVariable Long id) {
        return new ResponseEntity<>(studentProfileService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentProfile> updateStudentProfile(@PathVariable Long id,
                                                               @Valid @RequestBody StudentProfile studentProfile) {
        return new ResponseEntity<>(studentProfileService.updateStudentProfile(id, studentProfile), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentProfile(@PathVariable Long id) {
        studentProfileService.deleteStudentProfile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
