package dev.canlapan.handlers.ExpenseHandlers;

import com.google.gson.Gson;
import dev.canlapan.app.App;
import dev.canlapan.entities.Employee;
import dev.canlapan.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int expenseID = Integer.parseInt(ctx.pathParam("expenseID"));
        Expense temp = App.expenseService.retrieveExpenseByID(expenseID);
        String expenseJSON = ctx.body();
        Gson gson = new Gson();
        Expense expense = gson.fromJson(expenseJSON, Expense.class);
        if (temp == null){
            ctx.status(404);
            ctx.result("Expense ID " + expenseID + " not found");
            return;
        }else {
            ctx.status(200);
            Expense updatedExpense = App.expenseService.modifyExpense(expenseID, expense);
            ctx.result(String.valueOf(updatedExpense));
        }
    }
}
