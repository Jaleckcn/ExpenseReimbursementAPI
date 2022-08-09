package dev.canlapan.handlers.ExpenseHandlers;

import com.google.gson.Gson;
import dev.canlapan.app.App;
import dev.canlapan.entities.Expense;
import dev.canlapan.entities.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GetAllExpensesHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String status = ctx.queryParam("status");
        Gson gson = new Gson();
        //Checking to see if the Expense is Pending or not.
        //If yes, output all expenses with Pending status
        if (status == null) {
            List<Expense> expenses = App.expenseService.getAllExpenses();
            String json = gson.toJson(expenses);
            ctx.result(json);
        } else {
            String status1 = status.toUpperCase();
            List<Expense> expenses = App.expenseService.getStatus(Status.valueOf(status1)); //List of all expenses according to status
            String json = gson.toJson(expenses);
            ctx.result(json);
        }
    }
}

