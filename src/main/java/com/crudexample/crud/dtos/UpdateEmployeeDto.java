package com.crudexample.crud.dtos;


import com.crudexample.crud.validations.NotEmptyIfPresent.INotEmptyIfPresentValidator;
import jakarta.validation.constraints.*;

public class UpdateEmployeeDto {

    @INotEmptyIfPresentValidator(message = "Name can not be empty")
    public String employeeName;

    @Email(message = "Invalid email format")
    @INotEmptyIfPresentValidator(message = "Email can not be empty")
    public String employeeEmail;

    @INotEmptyIfPresentValidator(message = "Phone can not be empty")
    public String employeeContactNumber;

    @INotEmptyIfPresentValidator(message = "Address can not be empty")
    public String employeeAddress;

    @INotEmptyIfPresentValidator(message = "Gender can not be empty")
    public String employeeGender;

    @INotEmptyIfPresentValidator(message = "Department can not be empty")
    public String employeeDepartment;

    @INotEmptyIfPresentValidator(message = "Skills can not be empty")
    public String employeeSkills;
}
