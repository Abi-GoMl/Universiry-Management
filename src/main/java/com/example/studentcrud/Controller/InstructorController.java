package com.example.studentcrud.Controller;

import com.example.studentcrud.Controller.dto.InstructorRequest;
import com.example.studentcrud.Controller.dto.InstructorResponse;
import com.example.studentcrud.Entity.Instructor;
import com.example.studentcrud.Service.InstructorService;
import com.example.studentcrud.Mapper.EntityToResponseMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping
    public ResponseEntity<InstructorResponse> saveInstructor(@Valid @RequestBody InstructorRequest req) {
        Instructor instructor = new Instructor();
        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());
        Instructor savedInstructor = instructorService.saveInstructor(instructor);
        return new ResponseEntity<>(EntityToResponseMapper.toInstructorResponse(savedInstructor), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InstructorResponse>> getAllInstructors() {
        return new ResponseEntity<>(EntityToResponseMapper.toInstructorResponseList(instructorService.getAllInstructors()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(EntityToResponseMapper.toInstructorResponse(instructorService.getByInstructorId(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructorResponse> updateInstructor(@PathVariable Long id,
                                                       @Valid @RequestBody InstructorRequest req) {
        Instructor instructor = new Instructor();
        instructor.setName(req.getName());
        instructor.setEmail(req.getEmail());
        return new ResponseEntity<>(EntityToResponseMapper.toInstructorResponse(instructorService.updateInstructor(id, instructor)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
