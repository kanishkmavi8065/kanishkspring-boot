package com.example.springmongodemo.service;

import com.example.springmongodemo.collections.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();

    public Employee findById(int id);

    public Employee save(Employee employee);

    public void deleteById(int id);

    List<Employee> getAllemployeesByValue(String value);
}
