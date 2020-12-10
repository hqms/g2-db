package com.hqm.demoapi.controller;

import com.hqm.demoapi.model.Employees;
import com.hqm.demoapi.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
}
