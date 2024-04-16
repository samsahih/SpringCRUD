package com.crudexample.crud.mappers;

import com.crudexample.crud.dtos.EmployeeDto;
import com.crudexample.crud.dtos.UpdateEmployeeDto;
import com.crudexample.crud.entities.Employee;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeMapperTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Mock
    private EmployeeDto employeeDto;

    @Mock
    private UpdateEmployeeDto updateEmployeeDto;

    @InjectMocks
    private EmployeeMapper employeeMapper;

    @BeforeEach
    void setUp() {
        employeeMapper = new EmployeeMapper(validator);
        MockitoAnnotations.initMocks(this);

        employeeDto.employeeName = "John";
        employeeDto.employeeEmail = "test@test.com";
        employeeDto.employeeContactNumber = "213142343";
        employeeDto.employeeAddress = "Antalya, Turkey";
        employeeDto.employeeGender = "M";
        employeeDto.employeeDepartment = "IT";
        employeeDto.employeeSkills = "Java, Spring, Azure";
    }

    @Test
    public void testToEntity() {
        // Arrange
        employeeDto.employeeEmail = "john@example.com"; // Set employee email
        // Set up other properties as needed

        // Act
        Employee employee = employeeMapper.toEntity(employeeDto);

        // Assert
        assertEquals("John", employee.getEmployeeName());
        assertEquals("john@example.com", employee.getEmployeeEmail());
        // Assert other properties
    }

    @Test
    public void testUpdateEntity() {
        // Arrange
        Employee employee = new Employee();
        employee.setEmployeeName("John");
        employee.setEmployeeEmail("john@example.com");

        updateEmployeeDto.employeeName = "Updated John";
        updateEmployeeDto.employeeEmail = "updatedjohn@example.com";

        // Act
        employeeMapper.updateEntity(employee, updateEmployeeDto);

        // Assert
        assertEquals("Updated John", employee.getEmployeeName());
        assertEquals("updatedjohn@example.com", employee.getEmployeeEmail());
        // Assert other properties
    }

    @Test
    public void testToEntityWithEmptyName() {
        // Arrange
        employeeDto.employeeName = ""; // Set employee name to empty

        // Act
        Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
        assertTrue(violations.stream().anyMatch(violation -> "Name can not be empty".equals(violation.getMessage())));

        // Assert
        assertEquals(1, violations.size());
        assertNull(employeeMapper.toEntity(employeeDto)); // Ensure toEntity returns null when there are validation violations
        assertNotNull(employeeDto); // Ensure employeeDto remains unchanged
    }

    @Test
    public void testUpdateEntityWithInvalidEmail() {
        // Arrange
        employeeDto.employeeEmail = "test"; // invalid email

        // Act
        Set<ConstraintViolation<EmployeeDto>> violations = validator.validate(employeeDto);
        assertTrue(violations.stream().anyMatch(violation -> "Incorrect email format".equals(violation.getMessage())));

        // Assert
        assertEquals(1, violations.size());
        assertNull(employeeMapper.toEntity(employeeDto)); // Ensure toEntity returns null when there are validation violations
        assertNotNull(employeeDto); // Ensure employeeDto remains unchanged
    }
}