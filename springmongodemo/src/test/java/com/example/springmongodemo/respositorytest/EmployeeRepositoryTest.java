package com.example.springmongodemo.respositorytest;

import com.example.springmongodemo.collections.Employee;
import com.example.springmongodemo.repository.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepo employeeRepo;


    @Test
    void employeerepoFindAll_Test(){

        List<Employee> employees=employeeRepo.findAll();

         assertThat(employees).hasSize(6);
        assertThat(employees).hasSizeGreaterThan(0);
        assertThat(employees.get(0).getFirstName()).isEqualTo("kanishk");
    }

    @Test
    void employeeRepoFindById_Test(){

        Employee employee = employeeRepo.findById(4).get();

        assertThat(employee.getLastName()).isEqualTo("updatedname");
        assertThat(employee).isNotNull();

    }

    @Test
    void employeeRepoFindByValue_Test(){

        List<Employee>employees=employeeRepo.findEmployeesByFirstNameLikeOrLastNameLike("ec","ec");

        assertThat(employees).hasSize(1);
        assertThat(employees.get(0).getLastName()).isEqualTo("check");
//        assertThat(employees.get(1).getFirstName()).isEqualTo("anjali");
    }

    @Test
    void employeeRepoSaveEmployee_Test(){

        Employee sampleEmployee = new Employee(6,"betrayed","girl","betrayedgirl@gmail.com");

        assertThat(employeeRepo.save(sampleEmployee).getFirstName()).isEqualTo(sampleEmployee.getFirstName());

        // normally we check if its id is greater than 0 as we use spring data jpa
    }

    @Test
    void employeeRepoUpdateEmployee_test(){

        Employee sampleEmployee=employeeRepo.findById(4).get();

        sampleEmployee.setLastName("updatedname");

        assertThat(employeeRepo.save(sampleEmployee).getLastName()).isEqualTo("updatedname");

    }

    @Test
    void employeeRepoDelete_test(){

       employeeRepo.deleteById(5);

        Optional<Employee>result = employeeRepo.findById(5);

        Employee deletedEmployee=null;

        if(result.isPresent()){
            deletedEmployee=result.get();
        }
        else{
            assertThat(deletedEmployee).isNull();
        }
    }


}
