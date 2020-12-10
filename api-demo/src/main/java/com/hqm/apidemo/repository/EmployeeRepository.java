package com.hqm.apidemo.repository;

import com.hqm.apidemo.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employees, Long> {
    
}
