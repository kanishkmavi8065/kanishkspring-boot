package com.example.springmongodemo.controllertest;

import com.example.springmongodemo.collections.Employee;
import com.example.springmongodemo.controller.EmployeeRestController;
import com.example.springmongodemo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class EmployeeControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper=new ObjectMapper();

    @MockBean
    private EmployeeService employeeService;


    @InjectMocks
    private EmployeeRestController employeeRestController;


    Employee employee1=new Employee(6,"kanishk","mavi","kanishkmavi8@gmail.com");
    Employee employee2=new Employee(2,"hardik","shrma","hedkshrma8@gmail.com");
    Employee employee3=new Employee(3,"anjali","singh","anjalisingh8@gmail.com");


    @BeforeEach
       void setup(){
           MockitoAnnotations.openMocks(this);
           this.mockMvc= MockMvcBuilders.standaloneSetup(employeeRestController).build();
        }

        @Test
         void getAllEmployees_success() throws Exception {

           List<Employee> employees=new ArrayList<>(Arrays.asList(employee1,employee2,employee3));

           Mockito.when(employeeService.findAll()).thenReturn(employees);

            MockHttpServletRequestBuilder mockRequest =  MockMvcRequestBuilders.get("/employees")
                    .contentType(MediaType.APPLICATION_JSON);

            this.mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(3)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[2].firstName",is("anjali")));

        }


        @Test
         void getEmployee_success() throws Exception {

        Mockito.when(employeeService.findById(employee1.getId())).thenReturn(employee1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/employees/6")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",is(6)));

        }

        @Test
        void getEmployeeByValue_Success() throws Exception {

        List<Employee>employees=new ArrayList<>(Arrays.asList(employee1));

        Mockito.when(employeeService.getAllemployeesByValue("kan")).thenReturn(employees);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/employees/nameStartsWith/kan")
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName",is("mavi")));

        }


        @Test
        void saveEmployee_success() throws Exception{

        Mockito.when(employeeService.save(employee1)).thenReturn(employee1);


       String jsonValueOfEmployee=objectMapper.writeValueAsString(employee1);



        MockHttpServletRequestBuilder mockRequest= MockMvcRequestBuilders.post("/employees").contentType(MediaType.APPLICATION_JSON)
                .content(jsonValueOfEmployee);

        this.mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$",notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName",is("mavi")));
        }


        @Test
        void updateEmployee() throws Exception{

        employee2.setEmail("finalupdated@gmail.com");
        Mockito.when(employeeService.save(employee2)).thenReturn(employee2);

            String updatedJsonValue=objectMapper.writeValueAsString(employee2);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/employees").contentType(MediaType.APPLICATION_JSON)
                .content(updatedJsonValue);

        this.mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$",notNullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName",is("hardik")));

        }

        @Test
        void deleteEmployee_Success() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/employees/6").contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(mockRequest).andExpect(MockMvcResultMatchers.status().isOk());

        }

}
