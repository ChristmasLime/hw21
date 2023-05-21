package com.homework21.homework21.controller;

import com.homework21.homework21.model.Employee;
import com.homework21.homework21.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    Collection<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String firstName,
                        @RequestParam String lastName,
                        @RequestParam Double salary,
                        @RequestParam Integer departmentId) {
        return service.add(firstName, lastName, salary, departmentId);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName,
                         @RequestParam String lastName) {
        return service.find(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName,
                           @RequestParam String lastName) {
        return service.remove(firstName, lastName);
    }

    @GetMapping("/min-salary")
    public Employee findMinSalary(@RequestParam Integer departmentId) {
        return service.minSalary(departmentId);
    }

    @GetMapping("/max-salary")
    public Employee findMaxSalary(@RequestParam Integer departmentId) {
        return service.maxSalary(departmentId);
    }

    @GetMapping("/all")
    public List<Employee> findSalaryInDepartment(@RequestParam(required = false) Integer departmentId) {
        return service.salaryInDepartment(departmentId);
    }
}
