package dev.canlapan.services;

import dev.canlapan.entities.Expense;
import dev.canlapan.entities.Status;

import java.util.List;

public interface ExpenseService {
    Expense registerExpense(Expense expense);
    Expense retrieveExpenseByID(int expenseID);

    List<Expense> getAllExpenses();

    boolean deleteExpenseID(int expenseID);

    Status approveExpenseByID(int expenseID);
    Status denyExpenseByID(int expenseID);

    Expense modifyExpense(int expenseID, Expense expense);


}
