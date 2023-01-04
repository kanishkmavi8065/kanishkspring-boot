package com.example.restboot.adapters.SecondaryAdaptor;

import com.example.restboot.domain.entity.Employee;
import com.example.restboot.ports.outputports.EmployeeServiceOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
// it is a secondary adaptor which implements output port and is called by buissness logic to perfrom data operations
public class EmployeeData implements EmployeeServiceOutputPort {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee addEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeByid(int id) {

        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAllByIdGreaterThan(int value) {
        return employeeRepository.findAllByIdGreaterThan(value);
    }

    @Override
    public List<Employee> findEmployeesByFirstNameLike(String value) {
        return employeeRepository.findEmployeesByFirstNameLike(value);
    }
}
