package com.example.studentcrud.Service;

import com.example.studentcrud.Entity.Student;
import com.example.studentcrud.Repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentRepository repo;

    @InjectMocks
    StudentService service;

    @Test
    void saveStudent_callsRepositoryAndReturnsSaved() {
        Student s = new Student();
        s.setName("Alice");
        when(repo.save(any(Student.class))).thenAnswer(i -> {
            Student arg = i.getArgument(0);
            arg.setId(1L);
            return arg;
        });

        Student out = service.saveStudent(s);
        assertNotNull(out.getId());
        verify(repo).save(s);
    }
}
