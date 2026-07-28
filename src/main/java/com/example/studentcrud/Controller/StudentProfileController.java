package com.example.studentcrud.Controller;

import com.example.studentcrud.Controller.dto.StudentProfileRequest;
import com.example.studentcrud.Controller.dto.StudentProfileResponse;
import com.example.studentcrud.Entity.Student;
import com.example.studentcrud.Entity.StudentProfile;
import com.example.studentcrud.Service.StudentProfileService;
import com.example.studentcrud.Service.StudentService;
import com.example.studentcrud.Mapper.StudentProfileMapper;
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

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentProfileMapper profileMapper;

    @PostMapping
    public ResponseEntity<StudentProfileResponse> saveStudentProfile(@Valid @RequestBody StudentProfileRequest req) {
        StudentProfile profile = new StudentProfile();
        profile.setMarks(req.getMarks());
        profile.setAddress(req.getAddress());
        if (req.getStudentId() != null) {
            Student s = studentService.getByStudentId(req.getStudentId());
            profile.setStudent(s);
        }
        StudentProfile savedStudentProfile = studentProfileService.saveStudentProfile(profile);
        return new ResponseEntity<>(profileMapper.toResponse(savedStudentProfile), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentProfileResponse>> getAllStudentProfiles() {
        return new ResponseEntity<>(profileMapper.toResponseList(studentProfileService.getAllStudentProfiles()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfileResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(profileMapper.toResponse(studentProfileService.getById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentProfileResponse> updateStudentProfile(@PathVariable Long id,
                                                               @Valid @RequestBody StudentProfileRequest req) {
        StudentProfile profile = new StudentProfile();
        profile.setMarks(req.getMarks());
        profile.setAddress(req.getAddress());
        if (req.getStudentId() != null) {
            Student s = studentService.getByStudentId(req.getStudentId());
            profile.setStudent(s);
        }
        return new ResponseEntity<>(profileMapper.toResponse(studentProfileService.updateStudentProfile(id, profile)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentProfile(@PathVariable Long id) {
        studentProfileService.deleteStudentProfile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
