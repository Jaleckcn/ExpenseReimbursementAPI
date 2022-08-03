package dev.canlapan.daos;

import dev.canlapan.entities.Expense;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseDAOLocal implements ExpenseDAO {

    //generating an incrementing ID for each expense added to the system
    private Map<Integer, Expense> expenseTable = new HashMap();
    private int expenseIDMaker = 1;
    @Override
    public Expense createExpense(Expense expense) {
        return null;
    }

    @Override
    public Expense getExpenseByID(int expenseID) {
        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {
        return null;
    }

    @Override
    public Expense updateExpense(int expenseID, Expense expense) {
        return null;
    }

    @Override
    public boolean deleteExpenseByID(int expenseID) {
        return false;
    }
}
