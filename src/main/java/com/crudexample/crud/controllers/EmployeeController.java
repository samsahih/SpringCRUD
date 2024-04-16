package com.crudexample.crud.controllers;

import com.crudexample.crud.dtos.EmployeeDto;
import com.crudexample.crud.dtos.UpdateEmployeeDto;
import com.crudexample.crud.entities.Employee;
import com.crudexample.crud.services.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService _employeeService;

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        Employee createdEmployee = _employeeService.createEmployee(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable @NotNull Integer id, @Valid @RequestBody UpdateEmployeeDto employeeDto) {
        Employee updatedEmployee = _employeeService.updateEmployee(id, employeeDto);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
        _employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer id) {
        Employee employee = _employeeService.getEmployee(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/get")
    public ResponseEntity<Iterable<Employee>> getAllEmployees() {
        Iterable<Employee> employees = _employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
}
