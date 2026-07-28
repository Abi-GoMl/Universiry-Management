package com.example.studentcrud.Controller;

import com.example.studentcrud.Controller.dto.StudentRequest;
import com.example.studentcrud.Entity.Student;
import com.example.studentcrud.Service.StudentService;
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
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody StudentRequest req) {
        Student s = new Student();
        s.setName(req.getName());
        s.setEmail(req.getEmail());
        s.setAge(req.getAge());
        Student savedstudent = studentService.saveStudent(s);
        return new ResponseEntity<>(savedstudent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.getByStudentId(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequest req) {
        Student s = new Student();
        s.setName(req.getName());
        s.setEmail(req.getEmail());
        s.setAge(req.getAge());
        return new ResponseEntity<>(studentService.updateStudent(id,s),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
