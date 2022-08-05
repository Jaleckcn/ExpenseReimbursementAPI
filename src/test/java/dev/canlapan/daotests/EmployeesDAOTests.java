package dev.canlapan.daotests;

import dev.canlapan.daos.EmployeeDAO;
import dev.canlapan.daos.EmployeeDAOPostgres;
import dev.canlapan.entities.Employee;
import dev.canlapan.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeesDAOTests {

    static EmployeeDAO employeeDAO = new EmployeeDAOPostgres();

    @BeforeAll // this method will execute before any tests
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "create table employee(\n" +
                    "\temployee_id serial primary key,\n" +
                    "\tfirst_name varchar(100) not null,\n" +
                    "\tlast_name varchar(100) not null\n" +
                    ");";

            Statement statement = conn.createStatement();
            statement.execute(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void create_employee_test(){
        Employee employee = new Employee(0, "Jalec","Canlapan");
        Employee savedEmployee = this.employeeDAO.createEmployee(employee);
        Assertions.assertNotEquals(0,savedEmployee.getEmployeeID());
        System.out.println(employee);
    }

    @Test
    @Order(2)
    void get_employee_by_id_test(){
        Employee employee = employeeDAO.getEmployeeByID(1);
        Assertions.assertEquals("Jalec",employee.getEmployeeFirstName());
    }

    @Test
    @Order(3)
    void update_employee_test(){
        //think of update as more of a replacement or swap
        Employee employee2 = new Employee(1,"Steve", "Jobs");
        employeeDAO.updateEmployee(1, employee2);
        Employee employee = employeeDAO.getEmployeeByID(1);
        Assertions.assertEquals("Steve",employee.getEmployeeFirstName());
    }

    @Test
    @Order(4)
    void delete_employee_by_id_test(){
        boolean result = employeeDAO.deleteEmployeeByID(1);
        Assertions.assertTrue(result);
    }

    @Test
    @Order(5) //Integration Test, It requires multiple methods to work correctly
    void get_all_employees_test(){
        Employee employee1 = new Employee (0,"Yanoa", "Canlapan");
        Employee employee2 = new Employee (0,"Gina", "Morelli");
        Employee employee3 = new Employee (0,"Kaenen", "Dacanay");

        employeeDAO.createEmployee(employee1);
        employeeDAO.createEmployee(employee2);
        employeeDAO.createEmployee(employee3);

        List<Employee> employeeList = employeeDAO.getAllEmployees();
        Assertions.assertEquals(3,employeeList.size());

    }
//        @AfterAll // Runs after the last tests finishes
//    //If you don't want the table to be deleted, just comment this section out
//    static void teardown(){
//        try(Connection connection = ConnectionUtil.createConnection()){
//            String sql = "drop table employee";
//            Statement statement = connection.createStatement();
//            statement.execute(sql);
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//    }

}
