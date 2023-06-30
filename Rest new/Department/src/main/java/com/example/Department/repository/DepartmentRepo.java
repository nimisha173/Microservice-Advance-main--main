package com.example.Department.repository;

import com.example.Department.entity.department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepo extends JpaRepository<department,Long> {
    Optional<department> findBydeptId(Long deptId);
//    Optional<department> findByName(String deptname);
}
