package com.crudexample.crud.services;

import com.crudexample.crud.dtos.EmployeeDto;
import com.crudexample.crud.dtos.UpdateEmployeeDto;
import com.crudexample.crud.entities.Employee;
import com.crudexample.crud.mappers.IEmployeeMapper;
import com.crudexample.crud.repositories.IEmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import jakarta.persistence.EntityNotFoundException;

public class EmployeeServiceTest {

    @Mock
    private IEmployeeRepository employeeRepository;

    @Mock
    private IEmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateEmployee() {
        // Arrange
        EmployeeDto employeeDto = new EmployeeDto();
        // Set employeeDto properties

        Employee employee = new Employee();
        // Set employee properties

        when(employeeMapper.toEntity(employeeDto)).thenReturn(employee);
        when(employeeRepository.existsByEmployeeEmail(employeeDto.employeeEmail)).thenReturn(false);
        when(employeeRepository.save(employee)).thenReturn(employee);

        // Act
        Employee createdEmployee = employeeService.createEmployee(employeeDto);

        // Assert
        assertNotNull(createdEmployee);
    }

    @Test
    void testUpdateEmployee() {
        // Arrange
        Integer employeeId = 1;
        UpdateEmployeeDto updateEmployeeDto = new UpdateEmployeeDto();
        // Set updateEmployeeDto properties

        Employee existingEmployee = new Employee();
        // Set existingEmployee properties

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
        doNothing().when(employeeMapper).updateEntity(existingEmployee, updateEmployeeDto);
        when(employeeRepository.save(existingEmployee)).thenReturn(existingEmployee);

        // Act
        Employee updatedEmployee = employeeService.updateEmployee(employeeId, updateEmployeeDto);

        // Assert
        assertNotNull(updatedEmployee);
    }

    @Test
    void testDeleteEmployee() {
        // Arrange
        Integer employeeId = 1;

        // Mock the behavior of repository to return false for existsById
        when(employeeRepository.existsById(employeeId)).thenReturn(false);

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> employeeService.deleteEmployee(employeeId));
        verify(employeeRepository, times(1)).existsById(employeeId);
        verify(employeeRepository, never()).deleteById(anyInt()); // Verify deleteById was never called
    }

    @Test
    void testGetEmployee() {
        // Arrange
        Integer employeeId = 1;
        Employee existingEmployee = new Employee();
        // Set existingEmployee properties

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));

        // Act
        Employee employee = employeeService.getEmployee(employeeId);

        // Assert
        assertNotNull(employee);
    }

    @Test
    void testGetAllEmployees() {
        // Arrange
        Iterable<Employee> employees = mock(Iterable.class);

        when(employeeRepository.findAll()).thenReturn(employees);

        // Act
        Iterable<Employee> allEmployees = employeeService.getAllEmployees();

        // Assert
        assertNotNull(allEmployees);
    }
}