package dev.canlapan.entities;

public class Employee {
    private int employeeID; //employee ID
    private String employeeFirstName; // first name of the employee
    private String employeeLastName; //last name of the employee

    public Employee(){

    }

    public Employee(int employeeID, String employeeFirstName, String employeeLastName) {
        this.employeeID = employeeID;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                " Employee id=" + employeeID +
                ", Employee Name='" + employeeFirstName + ' ' + employeeLastName + '}';
    }
}


