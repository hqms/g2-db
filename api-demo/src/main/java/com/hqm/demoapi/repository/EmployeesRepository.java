package com.hqm.demoapi.repository;


import com.hqm.demoapi.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {

}
