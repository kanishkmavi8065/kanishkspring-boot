package com.example.restboot.adapters.primaryAdapter;

import com.example.restboot.domain.entity.Employee;
import com.example.restboot.ports.inputports.EmployeeServiceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
// it works as a primary adaptor which uses inputport to call the buisssness logic implemtation defined in domainlayer
public class EmployeeRestController {

    @Autowired
    private EmployeeServiceInputPort employeeServiceInputPort;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeServiceInputPort.findAll();
    }


    @GetMapping("/employees/byvalue/{value}")
    public List<Employee>greaterThanvalue(@PathVariable int value){

        return employeeServiceInputPort.findAllByIdGreaterThan(value);
    }

    @GetMapping("/employees/namelike/{value}")
    public List<Employee>getAllemployeeWithName(@PathVariable String value){

        return employeeServiceInputPort.findEmployeesByFirstNameLike("%"+value+"%");
    }


    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId){

        return employeeServiceInputPort.findById(employeeId);
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee){

        employee.setId(0);

        employeeServiceInputPort.save(employee);

        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){

        employeeServiceInputPort.save(employee);
        return employee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

        employeeServiceInputPort.deleteById(employeeId);

        return "deleted employee with id: "+employeeId;
    }

}
