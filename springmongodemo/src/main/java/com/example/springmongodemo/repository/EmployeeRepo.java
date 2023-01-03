package com.example.springmongodemo.repository;

import com.example.springmongodemo.collections.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee,Integer> {

    List<Employee> findEmployeesByFirstNameLikeOrLastNameLike(String value1,String value2);


}
