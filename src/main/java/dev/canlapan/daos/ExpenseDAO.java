package dev.canlapan.daos;

import dev.canlapan.entities.Employee;
import dev.canlapan.entities.Expense;

import java.util.List;

public interface ExpenseDAO {
    //Create an expense
    Expense createExpense(Expense expense); //method

    //Read
    Expense getExpenseByID(int expenseID); //method
    List<Expense> getAllExpenses(); //method

    //Update
    Expense updateExpense(int expenseID, Expense expense); //method

    //Delete
    boolean deleteExpenseByID(int expenseID); //method
}
