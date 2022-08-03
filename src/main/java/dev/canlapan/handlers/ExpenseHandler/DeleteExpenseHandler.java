package dev.canlapan.handlers.ExpenseHandler;

import dev.canlapan.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteExpenseHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
//        int expensesID = Integer.parseInt(ctx.pathParam("expensesID"));
//        boolean result = App.expenseService.deleteExpense(expensesID);
//        if(result){
//            ctx.status(404);
//            ctx.result("Expense not found");
//        }else{
//            ctx.status(422);
//            ctx.result("Expense is approved or denied. Cannot delete the expense");
//        }
    }
}
