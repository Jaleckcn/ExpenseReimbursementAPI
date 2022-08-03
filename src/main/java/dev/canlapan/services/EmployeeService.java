package dev.canlapan.services;

import dev.canlapan.entities.Employee;

import java.util.List;

public interface EmployeeService {

    Employee registerEmployee(Employee employee);
    Employee retrieveEmployeeByID(int employeeID);

    List<Employee> getAllEmployees();

    boolean deleteEmployeeID(int employeeID);

    Employee modifyEmployee(int employeeID, Employee employee);
}
