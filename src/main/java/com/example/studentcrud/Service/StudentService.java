package com.example.studentcrud.Service;

import com.example.studentcrud.Entity.Student;
import com.example.studentcrud.Exception.ResourceNotFoundException;
import com.example.studentcrud.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getByStudentId(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student Not Found"));
    }

    public Student updateStudent(Long id,Student student) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if(existingStudent!=null) {
            existingStudent.setName(student.getName());
            existingStudent.setDepartment(student.getDepartment());
            existingStudent.setAge(student.getAge());
            return studentRepository.save(existingStudent);

        }
        return null;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}

