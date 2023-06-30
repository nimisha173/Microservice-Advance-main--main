package com.example.departmentservice.service;

import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

//    public Optional<Department> getDepartmentById(Long departmentId) {
//        return departmentRepository.findById(departmentId);
//    }

    public Optional<Department> findByDepartmentId(Long departmentId) {
        return departmentRepository.findById(departmentId);
    }
}
