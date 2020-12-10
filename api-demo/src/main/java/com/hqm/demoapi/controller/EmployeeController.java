package com.hqm.demoapi.controller;

import com.hqm.demoapi.model.Employees;
import com.hqm.demoapi.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeesRepository employeesRepository;

    @GetMapping("/employees")
    public ResponseEntity<List<Employees>> getEmployees(@RequestParam(required = false) String args){
        List<Employees> employees = new ArrayList<Employees>();

        employeesRepository.findAll().forEach(employees::add);

        if(employees.isEmpty()){
            return new ResponseEntity<>(employees, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employees> getTutorialById(@PathVariable("id") long id) {
        Optional<Employees> employeeData = employeesRepository.findById(id);

        if (employeeData.isPresent()) {
            return new ResponseEntity<>(employeeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
