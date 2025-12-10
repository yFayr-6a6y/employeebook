package com.example.employeebook.service;

import com.example.employeebook.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> getEmployeesByDepartment(int departmentId) {
        return employeeService.getAllEmployees().stream()
                .filter(e -> e.getDepartment() == departmentId)
                .toList();
    }

    public int getSalarySumByDepartment(int departmentId) {
        return getEmployeesByDepartment(departmentId).stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public int getMaxSalaryByDepartment(int departmentId) {
        return getEmployeesByDepartment(departmentId).stream()
                .mapToInt(Employee::getSalary)
                .max()
                .orElseThrow(() -> new NoSuchElementException("В отделе " + departmentId + " нет сотрудников"));
    }

    public int getMinSalaryByDepartment(int departmentId) {
        return getEmployeesByDepartment(departmentId).stream()
                .mapToInt(Employee::getSalary)
                .min()
                .orElseThrow(() -> new NoSuchElementException("В отделе " + departmentId + " нет сотрудников"));
    }

    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        return employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}