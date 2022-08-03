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

        int expenseID = Integer.parseInt(ctx.pathParam("expenseID"));
        String expenseJSON = ctx.body();
        Gson gson = new Gson();
        Expense expense = gson.fromJson(expenseJSON, Expense.class);
        Expense updatedExpense = App.expenseService.modifyExpense(expenseID, expense);

        try {
            String json = gson.toJson(updatedExpense);
            ctx.status(201);
            ctx.result("An employee's expense field has been updated");
        } catch (RuntimeException e) {
            ctx.status(404);
            ctx.result("Expense not found");
        }
    }
}
