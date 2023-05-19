package com.homework21.homework21.controller;

import com.homework21.homework21.model.Employee;
import com.homework21.homework21.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String firstName,
                        @RequestParam String lastName,
                        @RequestParam double salary,
                        @RequestParam int departmentId) {
        return service.add(firstName, lastName, salary, departmentId);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName,
                         @RequestParam String lastName,
//                         @RequestParam double salary,
                         @RequestParam int departmentId) {
        return service.find(firstName, lastName, departmentId);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName,
                           @RequestParam String lastName,
//                           @RequestParam double salary,
                           @RequestParam Integer departmentId) {
        return service.remove(firstName, lastName,  departmentId);
    }

    @GetMapping
    public Collection<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("/max-salary")
    public Employee findEmployeeWithMaxSalaryInDepartment(@RequestParam int departmentId) {
        return service.employeeMaxSalaryInDepartment(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee findEmployeeWithMinSalaryInDepartment(@RequestParam int departmentId) {
        return service.employeeMinSalaryInDepartment(departmentId);
    }
    @GetMapping("/all")
    public List<Employee> findAllEmployeesInAllDepartments(@RequestParam(required = false) Integer departmentId) {
        return service.employeesInAllDepartments(departmentId);
    }
}