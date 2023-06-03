package com.mycompany.departments;

public class Department {
    private String name;
    private double budget;
    private String phone;

    public Department(String name, double budget, String phone) {
        this.name = name;
        this.budget = budget;
        this.phone = phone;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Department: " + name + "\nBudget: $" + budget + "\nPhone: " + phone + "\n\n";
    }
}
