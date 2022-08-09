package dev.canlapan.daos;

import dev.canlapan.entities.Expense;
import dev.canlapan.entities.Status;
import dev.canlapan.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAOPostgres implements ExpenseDAO {
    @Override
    public Expense createExpense(Expense expense) {
        try(Connection conn = ConnectionUtil.createConnection()) {
            //insert into expense values (default, 1, 50.00, 'PENDING','Used company car for work','Gas');

            String sql = "insert into expense values (default, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //setting the fields with values given using Postman
            preparedStatement.setInt(1, expense.getEmployeeID());
            preparedStatement.setDouble(2, expense.getExpenseAmount());
            preparedStatement.setString(3, Status.PENDING.name());//.name is toString for ENUMS
            preparedStatement.setString(4, expense.getDescription());
            preparedStatement.setString(5, expense.getType());

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();

            expense.setExpenseID(rs.getInt("expense_id"));

            return expense;
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense getExpenseByID(int expenseID) {
        //Ex.) insert into expense values (default, 1, 50.00, 'PENDING','Used company car for work','Gas');
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from expense where expense_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,expenseID);

            ResultSet rs = ps.executeQuery();
            rs.next();

            Expense expense = new Expense();
            expense.setExpenseID(rs.getInt("expense_id"));
            expense.setEmployeeID(rs.getInt("employee_id"));
            expense.setExpenseAmount(rs.getFloat("expense_amount"));
            expense.setExpenseStatus(Status.valueOf(rs.getString("expense_status")));
            expense.setDescription(rs.getString("description"));
            expense.setType(rs.getString("expense_type"));

            return expense;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Expense> getAllExpenses() {
        try(Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from expense";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            //creating a list that will hold our expense records
            List<Expense> expenseList = new ArrayList();
            while(rs.next()){
                Expense expense = new Expense();
                expense.setExpenseID(rs.getInt("expense_id"));
                expense.setEmployeeID(rs.getInt("employee_id"));
                expense.setExpenseAmount(rs.getFloat("expense_amount"));
                expense.setExpenseStatus(Status.valueOf(rs.getString("expense_status")));
                expense.setDescription(rs.getString("description"));
                expense.setType(rs.getString("expense_type"));
                expenseList.add(expense);//expense has been added to our list
            }
            return expenseList;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Expense updateExpense(int expenseID, Expense expense) {
        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "update expense set employee_id = ?, expense_amount = ?, expense_status = ?, description = ?, expense_type = ? where expense_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, expense.getEmployeeID());
            preparedStatement.setFloat(2, expense.getExpenseAmount());
            preparedStatement.setString(3, expense.getExpenseStatus().name());
            preparedStatement.setString(4,expense.getDescription());
            preparedStatement.setString(5,expense.getType());
            preparedStatement.setInt(6,expense.getExpenseID());

            preparedStatement.executeUpdate();
            return expense;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteExpenseByID(int expenseID) {
        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from expense where expense_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,expenseID);
            ps.execute();
            return true;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

}
