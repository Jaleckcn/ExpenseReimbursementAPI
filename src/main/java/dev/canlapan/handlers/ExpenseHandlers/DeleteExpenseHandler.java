package dev.canlapan.handlers.ExpenseHandlers;

import dev.canlapan.app.App;
import dev.canlapan.entities.Employee;
import dev.canlapan.entities.Expense;
import dev.canlapan.entities.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteExpenseHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int expenseID = Integer.parseInt(ctx.pathParam("expenseID"));
        Expense temp = App.expenseService.retrieveExpenseByID(expenseID);
        if (temp == null){
            ctx.status(404);
            ctx.result("Expense ID " + expenseID + " not found");
            return;
        }else if (temp.getExpenseStatus() == Status.PENDING){
            App.expenseService.deleteExpenseID(expenseID);
            ctx.status(200);
            ctx.result("Expense has been deleted");
        } else{
            ctx.status(422);
            ctx.result("Expense has been approved or denied, cannot delete");
        }
    }
}
