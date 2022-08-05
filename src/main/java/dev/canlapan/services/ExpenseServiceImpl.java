package dev.canlapan.services;

import dev.canlapan.daos.ExpenseDAO;
import dev.canlapan.daos.ExpenseDAOLocal;
import dev.canlapan.entities.Employee;
import dev.canlapan.entities.Expense;
import dev.canlapan.entities.Status;

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
    public List<Expense> getStatus(Status status) {
        return null;
    }

    @Override
    public boolean deleteExpenseID(int expenseID) {
        boolean isSuccessful = this.expenseDAO.deleteExpenseByID(expenseID);
        return isSuccessful;
    }

    @Override
    public Status approveExpenseByID(int expenseID) {
        if (expenseDAO.getExpenseByID(expenseID)!=null){
            expenseDAO.getExpenseByID(expenseID).setExpenseStatus(Status.APPROVED);
            return Status.APPROVED;
        } else {
            throw new RuntimeException("Expense ID does not exist");
        }
    }

    @Override
    public Status denyExpenseByID(int expenseID) {
        if (expenseDAO.getExpenseByID(expenseID)!=null){
            expenseDAO.getExpenseByID(expenseID).setExpenseStatus(Status.DENIED);
            return Status.DENIED;
        } else {
            throw new RuntimeException("Expense ID does not exist");
        }
    }

    @Override
    public Expense modifyExpense(int expenseID, Expense expense) {
        return this.expenseDAO.updateExpense(expenseID, expense);
    }

}
