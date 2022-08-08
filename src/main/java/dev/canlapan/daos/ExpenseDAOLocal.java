package dev.canlapan.daos;

import dev.canlapan.entities.Expense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseDAOLocal implements ExpenseDAO {

    //generating an incrementing ID for each expense added to the system
    private Map<Integer, Expense> expenseTable = new HashMap();
    private int expenseIDMaker = 1;
    @Override
    public Expense createExpense(Expense expense) {
        expense.setExpenseID(expenseIDMaker);
        expenseIDMaker++;
        expenseTable.put(expense.getExpenseID(),expense);
        return expense;
    }

    @Override
    public Expense getExpenseByID(int expenseID) {
        return expenseTable.get(expenseID);
    }

    @Override
    public List<Expense> getAllExpenses() {
       List<Expense> temp = new ArrayList();
        for (Map.Entry<Integer, Expense> id : expenseTable.entrySet()){
            temp.add(id.getValue());
        }
        return temp;

    }

    @Override
    public Expense updateExpense(int expenseID, Expense expense) {

        return expenseTable.put(expenseID,expense);
    }

    @Override
    public boolean deleteExpenseByID(int expenseID) {

        Expense expense = expenseTable.remove(expenseID);
        if(expense == null) {
            return false;
        }
        return true;
    }

}
