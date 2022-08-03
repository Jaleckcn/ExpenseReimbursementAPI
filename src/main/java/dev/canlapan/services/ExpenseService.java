package dev.canlapan.services;

import dev.canlapan.entities.Expense;

public interface ExpenseService {
    Expense registerExpense(Expense expense);
    Expense retrieveExpenseByID(int expenseID);

    Expense getAllExpenses();

    boolean deleteExpenseID(int expenseID);

    Expense modifyExpense(int expenseID, Expense expense);


}
