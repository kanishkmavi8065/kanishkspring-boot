package com.example.springmongodemo.controller;

import com.example.springmongodemo.collections.Employee;
import com.example.springmongodemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeRestController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    List<Employee> getAlllEmployee(){

        return employeeService.findAll();

    }

    @GetMapping("/employees/{employeeId}")
    Employee getEmployee(@PathVariable int employeeId){

        return employeeService.findById(employeeId);
    }

    @GetMapping("/employees/nameStartsWith/{value}")
    List<Employee>getAllemployees(@PathVariable String value)
    {
        return employeeService.getAllemployeesByValue(value);
    }

    @PostMapping("/employees")
    Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }


    @PutMapping("/employees")
    Employee updateEmployee(@RequestBody Employee employee){

        return employeeService.save(employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    String deleteEmploye(@PathVariable int employeeId){

        employeeService.deleteById(employeeId);

        return "deleted employee with id:"+employeeId;
    }

}
