package dev.canlapan.handlers.ExpenseHandler;

import com.google.gson.Gson;
import dev.canlapan.app.App;
import dev.canlapan.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {

//        String expenseJSON = ctx.body();
//        Gson gson = new Gson();
//        Expense expense = gson.fromJson(expenseJSON, Expense.class);
//        Expense updatedExpense = App.expenseService.modifyExpense(expense);
//        String json = gson.toJson(updatedExpense);
//        ctx.result(json);
    }
}