package com.homework21.homework21.service;

import com.homework21.homework21.exception.EmployeeAlreadyAddedException;
import com.homework21.homework21.exception.EmployeeNotFoundException;
import com.homework21.homework21.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees = new HashMap<>();

    private static String createKey(String firstName, String lastName) {
        return (firstName + lastName).toLowerCase();
    }

    public Employee add(String firstName, String lastName, Double salary, Integer departmentId) {
        Employee employee = new Employee(firstName, lastName, salary, departmentId);
        if (employees.containsKey(createKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(createKey(firstName, lastName), employee);
        return employee;
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(createKey(firstName, lastName));
        if (employee!=null) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    public Employee remove(String firstName, String lastName) {
        return employees.remove(createKey(firstName, lastName));
    }

    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    public Employee minSalary(Integer departmentId) {
        return employees.values().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    public Employee maxSalary(Integer dedartmentId) {
        return employees.values().stream()
                .filter(e -> e.getDepartmentId() == dedartmentId)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }
    public List<Employee> salaryInDepartment(Integer departmentId) {
        Stream<Employee> employeeStream = employees.values().stream();
        if (departmentId!=null) {
            employeeStream = employeeStream.filter(e -> e.getDepartmentId() == departmentId);
        }
        List<Employee>employeeList=employeeStream.collect(Collectors.toList());
        return employeeList;
    }
}
