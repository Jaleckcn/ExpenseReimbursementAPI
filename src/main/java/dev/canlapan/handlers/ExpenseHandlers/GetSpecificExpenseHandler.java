package dev.canlapan.handlers.ExpenseHandlers;

import com.google.gson.Gson;
import dev.canlapan.app.App;
import dev.canlapan.entities.Employee;
import dev.canlapan.entities.Expense;
import dev.canlapan.entities.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetSpecificExpenseHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int expenseID = Integer.parseInt(ctx.pathParam("expenseID"));
        Expense temp = App.expenseService.retrieveExpenseByID(expenseID);
        Gson gson = new Gson();
        String expense = gson.toJson(temp);
        System.out.println(temp);
        if (temp == null){
            ctx.status(404);
            ctx.result("Expense ID " + expenseID + " not found");
        }else{
            App.expenseService.retrieveExpenseByID(expenseID);
            ctx.status(200);
            ctx.result(expense);
        }
    }
}
