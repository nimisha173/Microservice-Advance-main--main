package com.example.Department.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long deptId;
    private String deptName;
    private String deptCode;
}
