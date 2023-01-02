package com.example.springmongodemo.servicetest;

import com.example.springmongodemo.collections.Employee;
import com.example.springmongodemo.repository.EmployeeRepo;
import com.example.springmongodemo.service.EmployeeService;
import com.example.springmongodemo.service.EmployeeServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

    @MockBean
    private EmployeeRepo employeeRepo;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    Employee employee1=new Employee(6,"kanishk","mavi","kanishkmavi8@gmail.com");
    Employee employee2=new Employee(2,"hardik","shrma","hedkshrma8@gmail.com");
    Employee employee3=new Employee(3,"anjali","singh","anjalisingh8@gmail.com");

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllEmployees_success(){

        List<Employee> employees= new ArrayList<>(Arrays.asList(employee1,employee2,employee3));
        Mockito.when(employeeRepo.findAll()).thenReturn(employees);

        assertEquals(3,employeeService.findAll().size());
        assertEquals("hardik",employeeService.findAll().get(1).getFirstName());
    }

     @Test
    void getEmployeeById_Success(){
        Mockito.when(employeeRepo.findById(employee2.getId())).thenReturn(Optional.ofNullable(employee2));

        assertEquals("shrma",employeeService.findById(employee2.getId()).getLastName());
        assertEquals(employee2,employeeService.findById(employee2.getId()));
    }

    @Test
    void getEmployeesByValue_Success(){

        List<Employee> employees= new ArrayList<>(Arrays.asList(employee1,employee3));
        Mockito.when(employeeRepo.findEmployeesByFirstNameLikeOrLastNameLike("an","an")).thenReturn(employees);

        assertEquals(2,employeeService.getAllemployeesByValue("an").size());
        assertEquals("anjali",employeeService.getAllemployeesByValue("an").get(1).getFirstName());
    }

    @Test
    void saveEmployee_Succes(){

        Mockito.when(employeeRepo.save(employee3)).thenReturn(employee3);

        Employee savedEmployee=employeeService.save(employee3);
        assertEquals(employee3,savedEmployee);
        assertEquals("anjali",savedEmployee.getFirstName());

    }

    @Test
    void deleteEmployee_Sucess(){

        Mockito.when(employeeRepo.findById(employee2.getId())).thenReturn(Optional.ofNullable(employee2));

        employeeService.deleteById(employee2.getId());

        verify(employeeRepo,times(1)).deleteById(employee2.getId());

    }

}
