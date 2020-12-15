package com.hqm.demoapi.controller;

import com.hqm.demoapi.model.Employees;
import com.hqm.demoapi.repository.EmployeesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get a list of Employess")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of employees",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Employees.class))}
            ),
    })
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

    @PostMapping("/employees")
    public ResponseEntity<Employees> createEmployee(@RequestBody Employees employees) {
            Employees _tutorial = employeesRepository.save(employees);
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);

    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employees> updateEmployee(@PathVariable("id") long id, @RequestBody Employees employees) {
        Optional<Employees> employeeData = employeesRepository.findById(id);

        if (employeeData.isPresent()) {
            Employees _employee = employeeData.get();
            _employee.setFirst_name(employees.getFirst_name());
            _employee.setLast_name(employees.getLast_name());
            _employee.setHire_date(employees.getHire_date());
            return new ResponseEntity<>(employeesRepository.save(_employee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
        employeesRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
