package dev.canlapan.daos;

import dev.canlapan.entities.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDAOLocal implements EmployeeDAO {

    private Map<Integer, Employee> employeeTable = new HashMap<>();
    private int employeeIDMaker = 1;

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setEmployeeID(employeeIDMaker);
        employeeIDMaker++;
        employeeTable.put(employee.getEmployeeID(),employee);
        return employee;
    }

    @Override
    public Employee getEmployeeByID(int employeeID) {
        return employeeTable.get(employeeID);
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> temp = new ArrayList();

        for (Map.Entry<Integer, Employee> id : employeeTable.entrySet()){
            temp.add(id.getValue());
        }
        return temp;

    }

    @Override
    public Employee updateEmployee(int employeeID,Employee employee) {

        return employeeTable.put(employeeID,employee);
    }

    @Override
    public boolean deleteEmployeeByID(int employeeID) {
        Employee employee = employeeTable.remove(employeeID);
        if(employee == null) {
            return false;
        }
        return true;
    }
}
