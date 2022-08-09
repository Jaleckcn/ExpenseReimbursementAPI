package dev.canlapan.services;

import dev.canlapan.daos.ExpenseDAO;
import dev.canlapan.entities.Expense;
import dev.canlapan.entities.Status;

import java.util.List;
import java.util.stream.Collectors;

public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseDAO expenseDAO;

    public ExpenseServiceImpl(ExpenseDAO expenseDAO) { this.expenseDAO = expenseDAO; }

    @Override
    public Expense registerExpense(Expense expense) {
        if(expense.getExpenseAmount() < 0){
            throw new RuntimeException("Expense amount must be greater than $0.00");
        }

        if(expense.getType().length() == 0){
            throw new RuntimeException("Type of Expenses: BUSINESS TRAVEL, EDUCATION/TRAINING, BUSINESS SUPPLIES, BUSINESS TOOLS, OTHER");
        }

        if(expense.getDescription().length() == 0){
            throw new RuntimeException("Description field must be filled out.");
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

        List<Expense> temp = expenseDAO.getAllExpenses();

        List<Expense> expenseByStatus = temp.stream().filter(expense -> expense.getExpenseStatus() == status).collect(Collectors.toList());

        return expenseByStatus;
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
    public List<Expense> getAllExpenseByEmployeeID(int employeeID) {
        List<Expense> temp = expenseDAO.getAllExpenses();

        List<Expense> expenses = temp.stream().filter(expense -> expense.getEmployeeID() == employeeID).collect(Collectors.toList());

        return expenses;
    }

    @Override
    public Expense modifyExpense(int expenseID, Expense expense) {
        return this.expenseDAO.updateExpense(expenseID, expense);
    }

}
