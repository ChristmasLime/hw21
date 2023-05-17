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

    public Employee add(String firstName, String lastName, Double salary ,Integer departmentId)  {
        Employee employee = new Employee(firstName, lastName,salary,departmentId);
        if (employees.containsKey(createKey(firstName, lastName,salary,departmentId))) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(createKey(firstName, lastName,salary,departmentId), employee);
        return employee;
    }
    public Employee find(String firstName, String lastName, Double salary, Integer departmentId) {
        Employee employee = employees.get(createKey(firstName, lastName, salary, departmentId));
        if (employee == null) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public Employee remove(String firstName, String lastName, Double salary, Integer departmentId) {
        return employees.remove(createKey(firstName, lastName, salary, departmentId));
    }

    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    private static String createKey(String firstName, String lastName, Double salary, Integer departmentId) {
        return (firstName + lastName + salary.toString() + departmentId.toString()).toLowerCase();
    }
    public Employee employeeMaxSalaryInDepartment(int departmentId) {
        return employees.values().stream()
                .filter(e -> e.getDepartmentId()==(departmentId))
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(()-> new EmployeeNotFoundException());
    }
    public Employee employeeMinSalaryInDepartment(int departmentId) {
        return employees.values().stream()
                .filter(e -> e.getDepartmentId()==(departmentId))
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(()-> new EmployeeNotFoundException());
    }

    public List<Employee> employeesInAllDepartments(Integer departmentId) {
        Stream<Employee> employeeStream = employees.values().stream();
        if (departmentId != null) {
            employeeStream = employeeStream.filter(e -> e.getDepartmentId() == departmentId);
        }

        List<Employee> employeesInDepartment = employeeStream.collect(Collectors.toList());
        return employeesInDepartment;
    }

}

