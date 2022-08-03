package dev.canlapan.daos;

import dev.canlapan.entities.Employee;
import dev.canlapan.utils.ConnectionUtil;

import java.sql.*;
import java.util.List;

public class EmployeeDAOPostgres implements EmployeeDAO {
    @Override
    public Employee createEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()){
            //insert into employee values (default, 'Jalec', 'Canlapan',0);

            String sql = "insert into employee values (default, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getEmployeeFirstName());
            preparedStatement.setString(2, employee.getEmployeeLastName());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys(); //returns the id that was created
            rs.next(); //you have to move the cursor to the first valid record

            int generatedKey = rs.getInt("employeeID");
            employee.setEmployeeID(generatedKey);
            return employee;


        }catch(SQLException e){

        }
        return null;
    }


    @Override
    public Employee getEmployeeByID(int employeeID) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public Employee updateEmployee(int employeeID, Employee employee) {
        return null;
    }

    @Override
    public boolean deleteEmployeeByID(int employeeID) {
        return false;
    }
}
