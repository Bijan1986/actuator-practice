package com.practice.spring.section4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.spring.section4.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
