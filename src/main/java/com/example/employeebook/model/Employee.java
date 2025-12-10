package com.example.employeebook.model;

import java.util.Objects;

public class Employee {
    private static int counter = 1;
    private int id = 0;
    private String fullname;
    private int department;
    private int salary;

    public Employee(String fullname, int department, int salary) {
        this.fullname = fullname;
        this.department = department;
        this.salary = salary;
        this.id = counter++;
    }

    public String getFullname() { return fullname; }
    public int getSalary() { return salary; }
    public int getDepartment() { return department; }
    public int getId() { return id; }

    public void setDepartment(int department) { this.department = department; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public void setSalary(int salary) { this.salary = salary; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary &&
                department == employee.department &&
                Objects.equals(fullname, employee.fullname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullname, department, salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "fullname='" + fullname + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                ", id=" + id +
                '}';
    }
}