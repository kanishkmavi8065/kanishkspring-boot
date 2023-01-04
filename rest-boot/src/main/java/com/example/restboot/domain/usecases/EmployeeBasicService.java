package com.example.restboot.domain.usecases;

import com.example.restboot.domain.entity.Employee;
import com.example.restboot.ports.inputports.EmployeeServiceInputPort;
import com.example.restboot.ports.outputports.EmployeeServiceOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeBasicService implements EmployeeServiceInputPort {


    @Autowired
    private EmployeeServiceOutputPort employeeServiceOutputPort;

    @Override
    public List<Employee> findAll() {

        return employeeServiceOutputPort.getAllEmployees();
    }

    @Override
    public Employee findById(int employeeId) {

         Optional<Employee>result=employeeServiceOutputPort.getEmployeeById(employeeId);

         Employee employee;
         if(result.isPresent()){
             employee=result.get();
         }else{
             throw new RuntimeException("Cannot find student with id: "+employeeId);
         }

         return employee;
    }

    @Override
    public void save(Employee employee) {

        employeeServiceOutputPort.addEmployee(employee);
    }

    @Override
    public void deleteById(int employeeId) {

        Optional<Employee>result=employeeServiceOutputPort.getEmployeeById(employeeId);



        if(result.isPresent()){
            employeeServiceOutputPort.deleteEmployeeByid(employeeId);

        }else{
            throw new RuntimeException("cannot find employee by id "+employeeId);
        }

    }

    @Override
    public List<Employee> findAllByIdGreaterThan(int value) {

        return employeeServiceOutputPort.findAllByIdGreaterThan(value)
;    }

    @Override
    public List<Employee> findEmployeesByFirstNameLike(String value) {

        System.out.println(value);
        return employeeServiceOutputPort.findEmployeesByFirstNameLike(value);
    }
}
