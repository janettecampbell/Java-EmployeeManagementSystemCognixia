package com.mycompany.employeemanagementsystem;

import com.mycompany.employees.Employee;
import com.mycompany.departments.Department;


import java.io.*;
// future database connectivity
//import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class EmployeeManagementSystem {
    private List<Employee> employees;
    private List<Department> departments;
    private Scanner scanner;

    public EmployeeManagementSystem() {
        employees = new ArrayList<>();
        departments = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        // plan to save inputs to file in the future
        loadEmployeesFromFile("resources/employees.txt");
        loadDepartmentsFromFile();

        int choice;
        do {
            System.out.println("******** Employee Management System ********");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee Information");
            System.out.println("3. Remove Employee");
            System.out.println("4. List Employees");
            System.out.println("5. Add Department");
            System.out.println("6. Update Department Information");
            System.out.println("7. Remove Department");
            System.out.println("8. List Departments");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            // consume newline character
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    updateEmployee();
                    break;
                case 3:
                    removeEmployee();
                    break;
                case 4:
                    listEmployees();
                    break;
                case 5:
                    addDepartment();
                    break;
                case 6:
                    updateDepartment();
                    break;
                case 7:
                    removeDepartment();
                    break;
                case 8:
                    listDepartments();
                    break;
                case 0:
                    System.out.println("Exiting Employee Management System...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        } while (choice != 0);

        // plan to save inputs to file in the future
        saveEmployeesToFile();
        saveDepartmentsToFile();

    }

    private void addEmployee() {
        System.out.println("\n******** Add Employee ********");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();
        // consume newline character
        scanner.nextLine();
        System.out.print("Enter date of employment (month/day/year): ");
        String dateOfEmployment = scanner.nextLine();
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();
        // consume newline character
        scanner.nextLine();
        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        try {
            Employee newEmployee = new Employee(firstName, lastName, employeeId, dateOfEmployment, salary, department);
            if (isEmployeeIdUnique(employeeId)) {
                employees.add(newEmployee);
                System.out.println("\n********************************");
                System.out.println("* Employee added successfully! *");
                System.out.println("********************************");
            } else {
                throw new EmployeeManagementException("Employee ID already exists.");
            }
        } catch (EmployeeManagementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateEmployee() {
        System.out.println("\n******** Update Employee Information ********");
        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();
        // consume newline character
        scanner.nextLine();

        Employee employeeToUpdate = findEmployeeById(employeeId);
        if (employeeToUpdate == null) {
            System.out.println("Employee not found.");
            return;
        }

        int choice;
        do {
            System.out.println("\n******** Update Employee Information ********");
            System.out.println("1. Update First Name");
            System.out.println("2. Update Last Name");
            System.out.println("3. Update Date of Employment");
            System.out.println("4. Update Salary");
            System.out.println("5. Update Department");
            System.out.println("0. Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            // consume newline character
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new first name: ");
                    String newFirstName = scanner.nextLine();
                    employeeToUpdate.setFirstName(newFirstName);
                    break;
                case 2:
                    System.out.print("Enter new last name: ");
                    String newLastName = scanner.nextLine();
                    employeeToUpdate.setLastName(newLastName);
                    break;
                case 3:
                    System.out.print("Enter new date of employment (month/day/year): ");
                    String newDateOfEmployment = scanner.nextLine();
                    employeeToUpdate.setDateOfEmployment(newDateOfEmployment);
                    break;
                case 4:
                    System.out.print("Enter new salary: ");
                    double newSalary = scanner.nextDouble();
                    employeeToUpdate.setSalary(newSalary);
                    break;
                case 5:
                    System.out.print("Enter new department: ");
                    String newDepartment = scanner.nextLine();
                    employeeToUpdate.setDepartment(newDepartment);
                    break;
                case 0:
                    System.out.println("Exiting Update Employee Information ...");
                    start();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        } while (choice != 0);



        System.out.println("\n**********************************************");
        System.out.println("* Employee information updated successfully! *");
        System.out.println("**********************************************");
    }

    private void removeEmployee() {
        System.out.println("\n******** Remove Employee ********");
        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();
        // consume newline character
        scanner.nextLine();

        Employee employeeToRemove = findEmployeeById(employeeId);
        if (employeeToRemove == null) {
            System.out.println("Employee not found.");
            return;
        }

        employees.remove(employeeToRemove);
        System.out.println("\n**********************************");
        System.out.println("* Employee removed successfully! *");
        System.out.println("**********************************");
    }

    private void listEmployees() {
        System.out.println("\n******** List Employees ********");
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    private void addDepartment() {
        System.out.println("\n******** Add Department ********");
        System.out.print("Enter department name: ");
        String name = scanner.nextLine();
        System.out.print("Enter department budget: ");
        double budget = scanner.nextDouble();
        // consume newline character
        scanner.nextLine();
        System.out.print("Enter department phone: ");
        String phone = scanner.nextLine();

        Department newDepartment = new Department(name, budget, phone);
        departments.add(newDepartment);

        System.out.println("\n**********************************");
        System.out.println("* Department added successfully! *");
        System.out.println("**********************************");
    }

    private void updateDepartment() {
        System.out.println("\n******** Update Department Information ********");
        System.out.print("Enter department name: ");
        String name = scanner.nextLine();

        Department departmentToUpdate = findDepartmentByName(name);
        if (departmentToUpdate == null) {
            System.out.println("Department not found.");
            return;
        }

        System.out.print("Enter new department budget: ");
        double newBudget = scanner.nextDouble();
        // consume newline character
        scanner.nextLine();
        System.out.print("Enter new department phone: ");
        String newPhone = scanner.nextLine();

        departmentToUpdate.setBudget(newBudget);
        departmentToUpdate.setPhone(newPhone);

        System.out.println("\n************************************************");
        System.out.println("* Department information updated successfully! *");
        System.out.println("************************************************");
    }

    private void removeDepartment() {
        System.out.println("\n******** Remove Department ********");
        System.out.print("Enter department name: ");
        String name = scanner.nextLine();

        Department departmentToRemove = findDepartmentByName(name);
        if (departmentToRemove == null) {
            System.out.println("Department not found.");
            return;
        }

        departments.remove(departmentToRemove);
        System.out.println("\n************************************");
        System.out.println("* Department removed successfully! *");
        System.out.println("************************************");
    }

    private void listDepartments() {
        System.out.println("\n******** List Departments ********");
        if (departments.isEmpty()) {
            System.out.println("No departments found.");
        } else {
            for (Department department : departments) {
                System.out.println(department);
            }
        }
    }

    private Employee findEmployeeById(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                return employee;
            }
        }
        return null;
    }

    private boolean isEmployeeIdUnique(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                return false;
            }
        }
        return true;
    }

    private Department findDepartmentByName(String name) {
        for (Department department : departments) {
            if (department.getName().equals(name)) {
                return department;
            }
        }
        return null;
    }

    // plan to save inputs to file in the future
    private void loadEmployeesFromFile(String filename) {

        try (Scanner scanner = new Scanner(new File(filename))){

            while (scanner.hasNextLine()) {
                Employee employee = new Employee();

                for (int i = 0; i <= 4 && scanner.hasNextLine(); i++) {
                    String line = scanner.nextLine();
                    int index = line.indexOf(":");

                    if (index != -1) {
                        String key = line.substring(0, index).trim();
                        String value = line.substring(index + 1).trim();

                        System.out.println("Key: " + key + ", Value: " + value); // Debug statement

                        switch (key) {
                            case "Employee ID":
                                employee.setEmployeeId(Integer.parseInt(value));
                                break;
                            case "Name":
                                String[] nameParts = value.split(" ");
                                employee.setFirstName(nameParts[0]);
                                employee.setLastName(nameParts[nameParts.length - 1]);
                                break;
                            case "Department":
                                employee.setDepartment(value);
                                break;
                            case "Salary":
                                employee.setSalary(Double.parseDouble(value));
                                break;
                        }
                    }
                }
                employees.add(employee);
            }
        } catch (IOException e) {
            System.out.println("Error loading employees from file: " + e.getMessage());
        }
    }

        // plan to save inputs to file in the future
    private void saveEmployeesToFile() {

        try (FileWriter writer = new FileWriter("resources/employees.txt")){
            for (Employee employee : employees) {
                writer.write(employee + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving employees to file: " + e.getMessage());
        }
    }

    // plan to save inputs to file in the future
    private void loadDepartmentsFromFile() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("resources/departments.dat"))) {
            departments = (List<Department>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading departments from file: " + e.getMessage());
        }
    }

    // plan to save inputs to file in the future
    private void saveDepartmentsToFile() {
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(new FileOutputStream("resources/departments.dat"))) {
            outputStream.writeObject(departments);
        } catch (IOException e) {
            System.out.println("Error saving departments to file: " + e.getMessage());
        }
    }
}
