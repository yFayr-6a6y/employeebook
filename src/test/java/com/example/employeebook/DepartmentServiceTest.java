package com.example.employeebook.service;

import com.example.employeebook.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    private final Employee e1 = new Employee("Ivanov I.I.", 1, 50000);
    private final Employee e2 = new Employee("Petrov P.P.", 1, 70000);
    private final Employee e3 = new Employee("Sidorov S.S.", 2, 60000);

    @Test
    void getEmployeesByDepartment_filtersCorrectly() {
        when(employeeService.getAllEmployees()).thenReturn(List.of(e1, e2, e3));
        assertEquals(2, departmentService.getEmployeesByDepartment(1).size());
        assertEquals(1, departmentService.getEmployeesByDepartment(2).size());
        assertTrue(departmentService.getEmployeesByDepartment(99).isEmpty());
    }

    @Test
    void getSalarySumByDepartment_calculatesCorrectly() {
        when(employeeService.getAllEmployees()).thenReturn(List.of(e1, e2));
        assertEquals(120000, departmentService.getSalarySumByDepartment(1));
    }

    @Test
    void getMaxSalaryByDepartment_returnsMax() {
        when(employeeService.getAllEmployees()).thenReturn(List.of(e1, e2));
        assertEquals(70000, departmentService.getMaxSalaryByDepartment(1));
    }

    @Test
    void getMaxSalaryByDepartment_emptyDepartment_throwsException() {
        when(employeeService.getAllEmployees()).thenReturn(List.of(e3));
        assertThrows(NoSuchElementException.class,
                () -> departmentService.getMaxSalaryByDepartment(1));
    }

    @Test
    void getMinSalaryByDepartment_returnsMin() {
        when(employeeService.getAllEmployees()).thenReturn(List.of(e1, e2));
        assertEquals(50000, departmentService.getMinSalaryByDepartment(1));
    }

    @Test
    void getEmployeesGroupedByDepartment_groupsCorrectly() {
        when(employeeService.getAllEmployees()).thenReturn(List.of(e1, e2, e3));
        Map<Integer, List<Employee>> map = departmentService.getEmployeesGroupedByDepartment();
        assertEquals(2, map.size());
        assertEquals(2, map.get(1).size());
        assertEquals(1, map.get(2).size());
    }
}