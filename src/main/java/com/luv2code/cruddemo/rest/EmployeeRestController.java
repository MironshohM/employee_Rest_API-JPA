package com.luv2code.cruddemo.rest;


import com.luv2code.cruddemo.dao.EmployeeDAO;
import com.luv2code.cruddemo.entity.Employee;
import com.luv2code.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {


    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService=theEmployeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee FindByID(@PathVariable int employeeId){
        Employee employee=employeeService.findById(employeeId);
        if(employee==null){
            throw new RuntimeException("Employee id not found "+employeeId);
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        employee.setId(0);

        Employee employee1=employeeService.save(employee);

        return employee1;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee employee1=employeeService.save(employee);

        return employee1;
    }


    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee employee=employeeService.findById(employeeId);
        if(employee==null){
            throw new RuntimeException("Employee id not found "+employeeId);
        }
        employeeService.deleteByID(employee.getId());
        return "Deleted Employee id: "+employee.getId();
    }

}
