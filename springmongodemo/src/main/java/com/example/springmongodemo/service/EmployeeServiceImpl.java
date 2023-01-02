package com.example.springmongodemo.service;

import com.example.springmongodemo.collections.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springmongodemo.repository.EmployeeRepo;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo repo;

    @Override
    public List<Employee> findAll() {

        return repo.findAll();
    }
    @Override
    public Employee findById(int id) {
        Optional<Employee>result=repo.findById(id);

        Employee employee=null;

        if(result.isPresent()){
            employee= result.get();
        }else{
            throw new RuntimeException("cannot find Employee with id:"+ id);
        }
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        repo.save(employee);
        return employee;

    }

    @Override
    public void deleteById(int id) {

        Optional<Employee>result=repo.findById(id);

        if(result.isPresent()){
            repo.deleteById(id);
        }else{

            throw new RuntimeException("cannot find Employee with id:"+ id);
        }


    }

    @Override
    public List<Employee> getAllemployeesByValue(String value) {

        return repo.findEmployeesByFirstNameLikeOrLastNameLike(value,value);
    }
}
