package dev.canlapan.services;

import dev.canlapan.entities.Expense;
import dev.canlapan.entities.Status;

import java.util.List;

public interface ExpenseService {
    Expense registerExpense(Expense expense);
    Expense retrieveExpenseByID(int expenseID);

    List<Expense> getAllExpenses();

    List<Expense> getStatus(Status status);

    boolean deleteExpenseID(int expenseID);

    Status approveExpenseByID(int expenseID);
    Status denyExpenseByID(int expenseID);

    List<Expense> getAllExpenseByEmployeeID(int employeeID);

    Expense modifyExpense(int expenseID, Expense expense);


}
