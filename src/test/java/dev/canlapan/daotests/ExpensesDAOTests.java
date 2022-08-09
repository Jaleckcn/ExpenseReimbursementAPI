package dev.canlapan.daotests;

import dev.canlapan.daos.ExpenseDAO;
import dev.canlapan.daos.ExpenseDAOPostgres;
import dev.canlapan.entities.Expense;
import dev.canlapan.entities.Status;
import dev.canlapan.utils.ConnectionUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpensesDAOTests {

    static ExpenseDAO expenseDAO = new ExpenseDAOPostgres();

    @BeforeAll
    static void setup(){
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "create table expense(\n" +
                    "\texpense_id serial primary key, \n" +
                    "\temployee_id int,\n" +
                    "\texpense_amount decimal check (expense_amount > 0),\n" +
                    "\texpense_status varchar(100) not null,\n" +
                    "\tdescription varchar(100) not null,\n" +
                    "\texpense_type varchar(100) not null\n" +
                    ");";

            Statement statement = conn.createStatement();
            statement.execute(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    void create_expense_test(){
        //expenseID is not auto incrementing
        Expense expense = new Expense(0, 50.00f, Status.PENDING,1,"Used company car for work","BUSINESS TRAVEL");
        Expense savedExpense = this.expenseDAO.createExpense(expense);
        Assertions.assertNotEquals(0,savedExpense.getExpenseID());
    }

    @Test
    @Order(2)
    void get_expense_by_id_test(){
        Expense expense = expenseDAO.getExpenseByID(1);
        Assertions.assertEquals("PENDING" ,expense.getExpenseStatus().name());
        System.out.println(expense);
    }

    @Test
    @Order(3)
    void update_expense_test(){
        Expense expense2 = new Expense(1,46.88f,Status.valueOf(Status.PENDING.toString()),1,"Traveling by company car","BUSINESS TRAVEL");
        expenseDAO.updateExpense(1, expense2);
        Expense expense = expenseDAO.getExpenseByID(1);
        Assertions.assertEquals("BUSINESS TRAVEL",expense.getType());
    }


    @Test
    @Order(4)
    void delete_expense_by_id_test(){
        boolean result = expenseDAO.deleteExpenseByID(1);
        Assertions.assertTrue(result);
    }


    @Test
    @Order(5) //Integration Test, It requires multiple methods to work correctly
    void get_all_employees_test(){
        Expense expense1 = new Expense (0, 1430.57f, Status.PENDING, 1,"Vacation in Las Vegas","BUSINESS TRAVEL");
        Expense expense2 = new Expense (0, 300.39f, Status.PENDING, 3,"Office supplies for the company","BUSINESS SUPPLIES");
        Expense expense3 = new Expense (0, 76.21f, Status.PENDING, 3,"Gas for company car","BUSINESS TRAVEL");
        Expense expense4 = new Expense (0, 15.95f, Status.PENDING, 1,"Lunch while traveling","OTHER");

        expenseDAO.createExpense(expense1);
        expenseDAO.createExpense(expense2);
        expenseDAO.createExpense(expense3);
        expenseDAO.createExpense(expense4);


        List<Expense> expensesList = expenseDAO.getAllExpenses();
        Assertions.assertEquals(4,expensesList.size());

        System.out.println(expense1);
        System.out.println(expense2);
        System.out.println(expense3);
        System.out.println(expense4);
    }


    @AfterAll // Runs after the last tests finishes
    //If you don't want the table to be deleted, just comment this section out
    static void teardown(){
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "drop table expense";
            Statement statement = connection.createStatement();
            statement.execute(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
