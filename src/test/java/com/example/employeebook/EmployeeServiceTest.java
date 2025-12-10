package com.example.employeebook.service;

import com.example.employeebook.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeService service;

    @BeforeEach
    void setUp() {
        service = new EmployeeService();
    }

    @Test
    void addEmployee_createsWithCorrectId() {
        Employee emp = service.addEmployee("Ivanov I.I.", 1, 50000);
        assertEquals(1, emp.getId());
        assertEquals("Ivanov I.I.", emp.getFullname());
    }

    @Test
    void removeExistingEmployee_returnsItAndRemoves() {
        Employee emp = service.addEmployee("Petrov P.P.", 2, 60000);
        Employee removed = service.removeEmployee(emp.getId());
        assertEquals(emp, removed);
        assertThrows(NoSuchElementException.class, () -> service.findEmployeeById(emp.getId()));
    }

    @Test
    void removeNonExisting_throwsException() {
        assertThrows(NoSuchElementException.class, () -> service.removeEmployee(999));
    }

    @Test
    void findEmployeeById_existing_returnsCorrect() {
        Employee added = service.addEmployee("Sidorov S.S.", 3, 70000);
        Employee found = service.findEmployeeById(added.getId());
        assertEquals(added, found);
    }

    @Test
    void getAllEmployees_returnsImmutableCopy() {
        service.addEmployee("A", 1, 100);
        service.addEmployee("B", 2, 200);
        assertEquals(2, service.getAllEmployees().size());
    }
}