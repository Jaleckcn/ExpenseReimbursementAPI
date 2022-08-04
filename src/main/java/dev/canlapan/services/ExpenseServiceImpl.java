package dev.canlapan.services;

import dev.canlapan.daos.ExpenseDAO;
import dev.canlapan.daos.ExpenseDAOLocal;
import dev.canlapan.entities.Employee;
import dev.canlapan.entities.Expense;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseDAO expenseDAO;

    public ExpenseServiceImpl(ExpenseDAO expenseDAO) { this.expenseDAO = expenseDAO; }

    @Override
    public Expense registerExpense(Expense expense) {
        if(expense.getExpenseAmount() < 0){
            throw new RuntimeException("Expense amount must be greater than $0.00");
        }

        if(expense.getType().length() == 0){
            throw new RuntimeException("Please fill out the description field. Ex.) hotel accommodations, gas, food or other ");
        }

        if(expense.getDescription().length() == 0){
            throw new RuntimeException("Type of expense must be filled out. Ex.) travel, education, supplies, tools or other ");
        }
        Expense savedExpense = this.expenseDAO.createExpense(expense);
        return savedExpense;
    }



    @Override
    public Expense retrieveExpenseByID(int expenseID) {
        return this.expenseDAO.getExpenseByID(expenseID);
    }

    @Override
    public List<Expense> getAllExpenses() {
        return this.expenseDAO.getAllExpenses();
    }

    @Override
    public boolean deleteExpenseID(int expenseID) {
        boolean isSuccessful = this.expenseDAO.deleteExpenseByID(expenseID);
        return isSuccessful;
    }

    @Override
    public Expense modifyExpense(int expenseID, Expense expense) {
        return this.expenseDAO.updateExpense(expenseID, expense);
    }

}
