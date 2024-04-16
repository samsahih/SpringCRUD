package com.crudexample.crud.mappers;

import com.crudexample.crud.dtos.EmployeeDto;
import com.crudexample.crud.dtos.UpdateEmployeeDto;
import com.crudexample.crud.entities.Employee;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Validator;

@Component
public class EmployeeMapper implements IEmployeeMapper {

    private final Validator validator;

    @Autowired
    public EmployeeMapper(Validator validator) {
        this.validator = validator;
    }

    @Override
    public Employee toEntity(EmployeeDto dto) {
        if (dto == null || !validator.validate(dto).isEmpty()) {
            return null;
        }

        Employee employee = new Employee();
        employee.setEmployeeName(dto.employeeName);
        employee.setEmployeeEmail(dto.employeeEmail);
        employee.setEmployeeContactNumber(dto.employeeContactNumber);
        employee.setEmployeeAddress(dto.employeeAddress);
        employee.setEmployeeGender(dto.employeeGender);
        employee.setEmployeeDepartment(dto.employeeDepartment);
        employee.setEmployeeSkills(dto.employeeSkills);

        return employee;
    }

    @Override
    public void updateEntity(Employee employee, UpdateEmployeeDto dto) {
        if (employee != null && dto != null) {
            // Update only the non-null fields from the DTO
            if (dto.employeeName != null) {
                employee.setEmployeeName(dto.employeeName);
            }
            if (dto.employeeEmail != null) {
                employee.setEmployeeEmail(dto.employeeEmail);
            }
            if (dto.employeeContactNumber != null) {
                employee.setEmployeeContactNumber(dto.employeeContactNumber);
            }
            if (dto.employeeAddress != null) {
                employee.setEmployeeAddress(dto.employeeAddress);
            }
            if (dto.employeeGender != null) {
                employee.setEmployeeGender(dto.employeeGender);
            }
            if (dto.employeeDepartment != null) {
                employee.setEmployeeDepartment(dto.employeeDepartment);
            }
            if (dto.employeeSkills != null) {
                employee.setEmployeeSkills(dto.employeeSkills);
            }
        }
    }
}