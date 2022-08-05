package dev.canlapan.daos;

import dev.canlapan.entities.Employee;
import dev.canlapan.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOPostgres implements EmployeeDAO {

    @Override
    public Employee createEmployee(Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()){
            //insert into employee values (default, 'Jalec', 'Canlapan');

            String sql = "insert into employee values (default, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getEmployeeFirstName());
            preparedStatement.setString(2, employee.getEmployeeLastName());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys(); //returns the id that was created
            rs.next(); //you have to move the cursor to the first valid record

            int generatedKey = rs.getInt("employee_id");
            employee.setEmployeeID(generatedKey);
            System.out.println(employee);
            return employee;


        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public Employee getEmployeeByID(int employeeID) {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from employee where employee_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,employeeID);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Employee employee = new Employee();
            employee.setEmployeeID(rs.getInt("employee_id"));
            employee.setEmployeeFirstName(rs.getString("first_name"));
            employee.setEmployeeLastName(rs.getString("last_name"));


            return employee;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {

        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from employee";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            List<Employee> employeeList = new ArrayList();
            while(rs.next()){
                Employee employee = new Employee();
                employee.setEmployeeID(rs.getInt("employee_id"));
                employee.setEmployeeFirstName(rs.getString("first_name"));
                employee.setEmployeeLastName(rs.getString("last_name"));
                employeeList.add(employee);
            }
            return employeeList;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee updateEmployee(int employeeID, Employee employee) {
        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "update employee set first_name = ?, last_name = ? where employee_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, employee.getEmployeeFirstName());
            preparedStatement.setString(2, employee.getEmployeeLastName());
            preparedStatement.setInt(3,employee.getEmployeeID());

            preparedStatement.executeUpdate();
            return employee;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteEmployeeByID(int employeeID) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from employee where employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,employeeID);
            ps.execute();
            return true;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

}
