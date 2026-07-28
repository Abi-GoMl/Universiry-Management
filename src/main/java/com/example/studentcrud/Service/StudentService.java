package com.example.studentcrud.Service;

import com.example.studentcrud.Entity.Student;
import com.example.studentcrud.Exception.ResourceNotFoundException;
import com.example.studentcrud.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        Student saved = studentRepository.save(student);
        log.info("Saved student id={}", saved.getId());
        return saved;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getByStudentId(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Student not found id={}", id);
                    return new ResourceNotFoundException("Student with id " + id + " not found");
                });
    }

    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " not found"));
        existingStudent.setName(student.getName());
        existingStudent.setDepartment(student.getDepartment());
        existingStudent.setAge(student.getAge());
        Student saved = studentRepository.save(existingStudent);
        log.info("Updated student id={}", saved.getId());
        return saved;
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            log.error("Tried to delete missing student id={}", id);
            throw new ResourceNotFoundException("Student with id " + id + " not found");
        }
        studentRepository.deleteById(id);
        log.info("Deleted student id={}", id);
    }
}
