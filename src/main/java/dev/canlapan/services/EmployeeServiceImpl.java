package dev.canlapan.services;

import dev.canlapan.daos.EmployeeDAO;
import dev.canlapan.entities.Employee;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee registerEmployee(Employee employee) {
        if(employee.getEmployeeFirstName().length() == 0){
            throw new RuntimeException("First name must have at least one character");
        }

        if(employee.getEmployeeLastName().length() == 0){
            throw new RuntimeException("Last name must have at least one character");
        }
        Employee savedEmployee = this.employeeDAO.createEmployee(employee);
        return savedEmployee;
    }

    @Override
    public List<Employee> getAllEmployees(){
        return this.employeeDAO.getAllEmployees();
    }
    @Override
    public Employee retrieveEmployeeByID(int employeeID) {
        return this.employeeDAO.getEmployeeByID(employeeID);
    }

    @Override
    public boolean deleteEmployeeID(int employeeID) {
        boolean isSuccessful = this.employeeDAO.deleteEmployeeByID(employeeID);
        return isSuccessful;
    }

    @Override
    public Employee modifyEmployee(int employeeID,Employee employee) {
        return this.employeeDAO.updateEmployee(employeeID, employee);
    }
}
