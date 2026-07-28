package com.example.studentcrud.Controller;

import com.example.studentcrud.Controller.dto.StudentRequest;
import com.example.studentcrud.Controller.dto.StudentResponse;
import com.example.studentcrud.Entity.Student;
import com.example.studentcrud.Service.StudentService;
import com.example.studentcrud.Mapper.EntityToResponseMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponse> saveStudent(@Valid @RequestBody StudentRequest req) {
        Student s = new Student();
        s.setName(req.getName());
        s.setEmail(req.getEmail());
        s.setAge(req.getAge());
        Student savedstudent = studentService.saveStudent(s);
        return new ResponseEntity<>(EntityToResponseMapper.toStudentResponse(savedstudent), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        return new ResponseEntity<>(EntityToResponseMapper.toStudentResponseList(studentService.getAllStudents()),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(EntityToResponseMapper.toStudentResponse(studentService.getByStudentId(id)),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequest req) {
        Student s = new Student();
        s.setName(req.getName());
        s.setEmail(req.getEmail());
        s.setAge(req.getAge());
        return new ResponseEntity<>(EntityToResponseMapper.toStudentResponse(studentService.updateStudent(id,s)),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
