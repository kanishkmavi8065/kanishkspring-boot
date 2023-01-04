package com.example.restboot.adapters.SecondaryAdaptor;

import com.example.restboot.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee,Integer>{
    List<Employee> findAllByIdGreaterThan(int value);
    List<Employee> findEmployeesByFirstNameLike(String value);
}
