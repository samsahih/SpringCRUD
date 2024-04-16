package com.crudexample.crud.services;

import com.crudexample.crud.dtos.EmployeeDto;
import com.crudexample.crud.dtos.UpdateEmployeeDto;
import com.crudexample.crud.entities.Employee;
import com.crudexample.crud.mappers.IEmployeeMapper;
import com.crudexample.crud.repositories.IEmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private IEmployeeRepository _employeeRepository;

    @Autowired
    private IEmployeeMapper _employeeMapper;

    public Employee createEmployee(EmployeeDto dto) {
        try {
            // Check if the email already exists
            if (_employeeRepository.existsByEmployeeEmail(dto.employeeEmail)) {
                throw new IllegalArgumentException("Email already exists");
            }

            Employee employee = _employeeMapper.toEntity(dto);
            return _employeeRepository.save(employee);
        } catch (Exception ex) {
            // Log the exception or handle it as per the application's requirements
            throw new RuntimeException("Failed to create employee", ex);
        }
    }

    public Employee updateEmployee(Integer employeeId, UpdateEmployeeDto dto) {
        try {
            Employee existingEmployee = _employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

            _employeeMapper.updateEntity(existingEmployee, dto);

            return _employeeRepository.save(existingEmployee);
        } catch (EntityNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            // Log the exception or handle it as per your application's requirements
            throw new RuntimeException("Failed to update employee", ex);
        }
    }

    public void deleteEmployee(Integer employeeId) {
        if (!_employeeRepository.existsById(employeeId)) {
            throw new EntityNotFoundException("Employee not found");
        }

        try {
            _employeeRepository.deleteById(employeeId);
        } catch (Exception ex) {
            // Log the exception or handle it as per your application's requirements
            throw new RuntimeException("Failed to delete employee", ex);
        }
    }

    public Employee getEmployee(Integer employeeId) {
        return _employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    }

    public Iterable<Employee> getAllEmployees() {
        return _employeeRepository.findAll();
    }
}
