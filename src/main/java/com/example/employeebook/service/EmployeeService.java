package com.example.employeebook.service;

import com.example.employeebook.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(String fullname, int department, int salary) {
        Employee employee = new Employee(fullname, department, salary);
        employees.add(employee);
        return employee;
    }

    public Employee removeEmployee(int id) {
        Employee employee = findEmployeeById(id);
        employees.remove(employee);
        return employee;
    }

    public Employee findEmployeeById(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Сотрудник с id=" + id + " не найден"));
    }

    public List<Employee> getAllEmployees() {
        return List.copyOf(employees);
    }
}