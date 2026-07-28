package com.example.studentcrud.Controller;

import com.example.studentcrud.Controller.dto.DepartmentRequest;
import com.example.studentcrud.Controller.dto.DepartmentResponse;
import com.example.studentcrud.Entity.Department;
import com.example.studentcrud.Service.DepartmentService;
import com.example.studentcrud.Mapper.DepartmentMapper;
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

    @Autowired
    private DepartmentMapper departmentMapper;

    @PostMapping
    public ResponseEntity<DepartmentResponse> saveDepartment(@Valid @RequestBody DepartmentRequest req) {
        Department department = new Department();
        department.setName(req.getName());
        department.setHod(req.getHod());
        Department savedDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(departmentMapper.toResponse(savedDepartment), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {
        return new ResponseEntity<>(departmentMapper.toResponseList(departmentService.getAllDepartments()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getByDepartmentId(@PathVariable Long id) {
        return new ResponseEntity<>(departmentMapper.toResponse(departmentService.getByDepartmentId(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(@PathVariable Long id,
                                                       @Valid @RequestBody DepartmentRequest req) {
        Department department = new Department();
        department.setName(req.getName());
        department.setHod(req.getHod());
        return new ResponseEntity<>(departmentMapper.toResponse(departmentService.updateDepartment(id, department)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
