package com.example.Department.controller;

import com.example.Department.entity.department;
import com.example.Department.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class deptcontroller {

    @Autowired
    private DepartmentRepo deptrepo;

    @PostMapping("/add")
    public ResponseEntity<department>adddepartment(@RequestBody department dept){
        return ResponseEntity.ok().body(deptrepo.save(dept));
    }
    @GetMapping("/get/{deptId}")
    public ResponseEntity<department>getdeptById(@PathVariable Long deptId){
        Optional<department>op=deptrepo.findById(deptId);
        if(op.isPresent()){
            deptrepo.findById(deptId);
            return ResponseEntity.ok().body(op.get());
        }else{
           return ResponseEntity.noContent().build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<department>>getall(){
        List<department> departments = deptrepo.findAll();
        return ResponseEntity.ok().body(departments);
    }
//    @GetMapping("/getbyname/{deptname}")
//    public ResponseEntity<department>getByName(@PathVariable String deptname){
//        Optional<department>op1=deptrepo.findByName(deptname);
//        if(op1.isPresent()){
//            deptrepo.findByName(deptname);
//            return ResponseEntity.ok().body(op1.get());
//        }else{
//            return ResponseEntity.noContent().build();
//        }
//
//    }
    @PutMapping("/{deptId}")
    public ResponseEntity<department>updatedept(@RequestBody department dept){
        department dept1;
        Optional<department>op2=deptrepo.findBydeptId(dept.getDeptId());
        if(op2.isPresent()){
            dept1=op2.get();
            dept1.setDeptId(dept.getDeptId());
            dept1.setDeptName(dept.getDeptName());
            dept1.setDeptCode(dept.getDeptCode());
            deptrepo.save(dept1);
            return ResponseEntity.ok().body(dept1);
        }else{
            return ResponseEntity.noContent().build();
        }
    }
    @DeleteMapping("/{deptId}")
    public ResponseEntity<String>deleteById(@PathVariable long deptId){
        Optional<department>op4=deptrepo.findById(deptId);
            if(op4.isPresent()){
                deptrepo.deleteById(deptId);
                return ResponseEntity.ok().body("deleted");
            }
            else{
                return ResponseEntity.noContent().build();
            }
        }
    }


