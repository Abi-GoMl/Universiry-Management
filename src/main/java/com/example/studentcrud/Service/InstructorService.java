package com.example.studentcrud.Service;

import com.example.studentcrud.Entity.Instructor;
import com.example.studentcrud.Exception.ResourceNotFoundException;
import com.example.studentcrud.Repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    // Create
    public Instructor saveInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    // Read All
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    // Read By Id
    public Instructor getByInstructorId(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor Not Found"));
    }

    // Update
    public Instructor updateInstructor(Long id, Instructor instructor) {

        Instructor existingInstructor = instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor Not Found"));

        existingInstructor.setName(instructor.getName());
        existingInstructor.setEmail(instructor.getEmail());


        return instructorRepository.save(existingInstructor);
    }

    // Delete
    public void deleteInstructor(Long id) {

        instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor Not Found"));

        instructorRepository.deleteById(id);
    }
}
