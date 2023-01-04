package com.example.restboot.ports.outputports;

import com.example.restboot.domain.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeServiceOutputPort {

    List<Employee>getAllEmployees();

    Optional<Employee> getEmployeeById(int id);

    Employee addEmployee(Employee employee);

    void deleteEmployeeByid(int id);

    List<Employee> findAllByIdGreaterThan(int value);

    List<Employee> findEmployeesByFirstNameLike(String value);
}
