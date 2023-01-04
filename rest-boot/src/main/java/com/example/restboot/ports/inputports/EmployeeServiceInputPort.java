package com.example.restboot.ports.inputports;

import com.example.restboot.domain.entity.Employee;

import java.util.List;

public interface EmployeeServiceInputPort {

    public List<Employee> findAll();

    public Employee findById(int employeeId);

    public void save(Employee employee);

    public void deleteById(int employeeId);

    List<Employee> findAllByIdGreaterThan(int value);

    List<Employee>findEmployeesByFirstNameLike(String value);
}
