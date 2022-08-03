package dev.canlapan.handlers.ExpenseHandler;

import com.google.gson.Gson;
import dev.canlapan.app.App;
import dev.canlapan.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String json = ctx.body();
        Gson gson = new Gson();
        Expense expense = gson.fromJson(json, Expense.class);
        Expense registerExpense = App.expenseService.registerExpense(expense);
        String expenseJson = gson.toJson(registerExpense);
        ctx.status(201); //successful in creating new Expense
        ctx.result(expenseJson);
    }
}
