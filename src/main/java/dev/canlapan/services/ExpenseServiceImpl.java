package dev.canlapan.services;

import dev.canlapan.daos.ExpenseDAO;
import dev.canlapan.daos.ExpenseDAOLocal;
import dev.canlapan.entities.Expense;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseDAO expenseDAO;

    public ExpenseServiceImpl(ExpenseDAO expenseDAO) { this.expenseDAO = expenseDAO; }

    @Override
    public Expense registerExpense(Expense expense) {
        return null;
    }



    @Override
    public Expense retrieveExpenseByID(int expenseID) {
        return this.expenseDAO.getExpenseByID(expenseID);
    }

    @Override
    public Expense getAllExpenses() {
        return null;
    }

    @Override
    public boolean deleteExpenseID(int expenseID) {
        //Expense cannot be deleted
        return false;
    }

    @Override
    public Expense modifyExpense(int expenseID, Expense expense) {
        return this.expenseDAO.updateExpense(expenseID, expense);
    }

}
