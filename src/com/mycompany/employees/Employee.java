package com.mycompany.employees;

public class Employee {
    private String firstName;
    private String lastName;
    private int employeeId;
    private String dateOfEmployment;
    private double salary;
    private String department;

    public Employee(String firstName, String lastName, int employeeId, String dateOfEmployment, double salary, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.dateOfEmployment = dateOfEmployment;
        this.salary = salary;
        this.department = department;
    }

    // Getters and Setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(String dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + "\nName: " + firstName + " " + lastName + "\nDepartment: " + department + "\nSalary: $" + salary + "\n\n";
    }
}
