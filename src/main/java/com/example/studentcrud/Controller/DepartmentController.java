package com.example.studentcrud.Controller;

import com.example.studentcrud.Controller.dto.DepartmentRequest;
import com.example.studentcrud.Entity.Department;
import com.example.studentcrud.Service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> saveDepartment(@Valid @RequestBody DepartmentRequest req) {
        Department department = new Department();
        department.setName(req.getName());
        department.setHod(req.getHod());
        Department savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getByDepartmentId(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.getByDepartmentId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id,
                                                       @Valid @RequestBody DepartmentRequest req) {
        Department department = new Department();
        department.setName(req.getName());
        department.setHod(req.getHod());
        return new ResponseEntity<>(departmentService.updateDepartment(id, department), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
