package dev.canlapan.handlers.ExpenseHandler;

import dev.canlapan.app.App;
import dev.canlapan.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetSpecificExpenseHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
//        int expenseID = Integer.parseInt(ctx.pathParam("expenseID"));
//        Expense expense = App.expenseService.retrieveExpenseByID(expenseID);
    }
}
