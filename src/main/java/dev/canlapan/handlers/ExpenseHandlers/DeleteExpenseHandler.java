package dev.canlapan.handlers.ExpenseHandlers;

import dev.canlapan.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteExpenseHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int expensesID = Integer.parseInt(ctx.pathParam("expenseID"));
        boolean result = App.expenseService.deleteExpenseID(expensesID);
        if(result){
            ctx.status(201);
            ctx.result("Expense has been deleted");
        }else{
            ctx.status(404);
            ctx.result("Cannot find the Expense");
        }
    }
}
