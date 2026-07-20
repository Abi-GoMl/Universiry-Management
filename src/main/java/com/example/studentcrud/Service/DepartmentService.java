package com.example.studentcrud.Service;

import com.example.studentcrud.Entity.Department;
import com.example.studentcrud.Exception.ResourceNotFoundException;
import com.example.studentcrud.Repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getByDepartmentId(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department Not Found"));
    }

    public Department updateDepartment(Long id, Department department) {

        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department Not Found"));

        existingDepartment.setName(department.getName());
        existingDepartment.setHod(department.getHod());

        return departmentRepository.save(existingDepartment);
    }

    public void deleteDepartment(Long id) {

        departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department Not Found"));

        departmentRepository.deleteById(id);
    }
}