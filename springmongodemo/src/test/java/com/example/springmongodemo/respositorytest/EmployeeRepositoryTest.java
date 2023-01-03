package com.example.springmongodemo.respositorytest;

import com.example.springmongodemo.collections.Employee;
import com.example.springmongodemo.repository.EmployeeRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataMongoTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTest {


    @Container
    public static MongoDBContainer container = new MongoDBContainer("mongo:latest");

    @DynamicPropertySource
    public static void propertySource(DynamicPropertyRegistry registry){

        registry.add("spring.data.mongodb.host",container::getHost);
        registry.add("spring.data.mongodb.port",container::getFirstMappedPort);
    }
    @Autowired
    private EmployeeRepo employeeRepo;


    Employee employee1=new Employee(1,"kanishk","mavi","kanishkmavi8@gmail.com");
    Employee employee2=new Employee(2,"hardik","shrma","hedkshrma8@gmail.com");
    Employee employee3=new Employee(3,"anjali","singh","anjalisingh8@gmail.com");


    @BeforeAll
    static void initAll(){
        container.start();
    }

    @Test
    @Order(value = 2)
    void employeerepoFindAll_Test(){

        List<Employee> employees=employeeRepo.findAll();

         assertThat(employees).hasSize(3);
        assertThat(employees).hasSizeGreaterThan(0);
        assertThat(employees.get(0).getFirstName()).isEqualTo("kanishk");
    }

    @Test
    @Order(value = 3)
    void employeeRepoFindById_Test(){

        Employee employee = employeeRepo.findById(3).get();


        assertThat(employee.getLastName()).isEqualTo("singh");
        assertThat(employee).isNotNull();

    }

    @Test
    @Order(value = 4)
    void employeeRepoFindByValue_Test(){

        List<Employee>employees=employeeRepo.findEmployeesByFirstNameLikeOrLastNameLike("jali","jali");

        assertThat(employees).hasSize(1);
        assertThat(employees.get(0).getLastName()).isEqualTo("singh");

    }

    @Test
    @Order(value = 1)
    void employeeRepoSaveEmployee_Test(){

        assertThat(employeeRepo.save(employee1).getFirstName()).isEqualTo(employee1.getFirstName());
        assertThat(employeeRepo.save(employee2).getFirstName()).isEqualTo(employee2.getFirstName());
        assertThat(employeeRepo.save(employee3).getFirstName()).isEqualTo(employee3.getFirstName());

        // normally we check if its id is greater than 0 as we use spring data jpa
    }

    @Test
    @Order(value = 5)
    void employeeRepoUpdateEmployee_test(){

        Employee sampleEmployee=employeeRepo.findById(3).get();

        sampleEmployee.setLastName("updatedname");

        assertThat(employeeRepo.save(sampleEmployee).getLastName()).isEqualTo("updatedname");

    }

    @Test
    @Order(value = 5)
    void employeeRepoDelete_test(){

       employeeRepo.deleteById(2);

        Optional<Employee>result = employeeRepo.findById(2);

        Employee deletedEmployee=null;

        if(result.isPresent()){
            deletedEmployee=result.get();
        }
        else{
            assertThat(deletedEmployee).isNull();
        }
    }

    @AfterAll
    static void stopContainer(){

        container.close();
    }


}
