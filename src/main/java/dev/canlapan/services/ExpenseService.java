package dev.canlapan.services;

import dev.canlapan.entities.Expense;

import java.util.List;

public interface ExpenseService {
    Expense registerExpense(Expense expense);
    Expense retrieveExpenseByID(int expenseID);

    List<Expense> getAllExpenses();

    boolean deleteExpenseID(int expenseID);

    Expense modifyExpense(int expenseID, Expense expense);


}
