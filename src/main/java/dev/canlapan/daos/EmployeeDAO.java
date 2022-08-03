package dev.canlapan.daos;

import dev.canlapan.entities.Employee;

import java.util.List;

public interface EmployeeDAO {
    //Create an employee
    Employee createEmployee(Employee employee); //method

    //Read
    Employee getEmployeeByID(int employeeID); //method
    List<Employee> getAllEmployees(); //method

    //Update
    Employee updateEmployee(int employeeID, Employee employee); //method

    //Delete
    boolean deleteEmployeeByID(int employeeID); //method

}
