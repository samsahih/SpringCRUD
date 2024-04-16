package com.crudexample.crud.repositories;

import com.crudexample.crud.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends CrudRepository<Employee, Integer> {
    boolean existsByEmployeeEmail(String employeeEmail);
}