package com.crudexample.crud.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class EmployeeDto {

    @NotNull(message = "Name is mandatory")
    @NotEmpty(message = "Name can not be empty")
    public String employeeName;

    @NotNull(message = "Email is mandatory")
    @NotEmpty(message = "Email can not be empty")
    @Email
    public String employeeEmail;

    @NotNull(message = "Phone is mandatory")
    @NotEmpty(message = "Phone can not be empty")
    public String employeeContactNumber;

    public String employeeAddress;

    public String employeeGender;

    public String employeeDepartment;

    public String employeeSkills;
}
