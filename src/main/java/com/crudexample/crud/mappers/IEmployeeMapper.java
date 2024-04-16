package com.crudexample.crud.mappers;

import com.crudexample.crud.dtos.EmployeeDto;
import com.crudexample.crud.dtos.UpdateEmployeeDto;
import com.crudexample.crud.entities.Employee;
import org.mapstruct.Mapping;

public interface IEmployeeMapper {
    @Mapping(target = "employeeId", ignore = true)
    Employee toEntity(EmployeeDto dto);

    @Mapping(target = "employeeId", ignore = true)
    void updateEntity(Employee employee, UpdateEmployeeDto dto);
}
