package dev.canlapan.handlers.ExpenseHandlers;

import com.google.gson.Gson;
import dev.canlapan.app.App;
import dev.canlapan.entities.Expense;
import dev.canlapan.entities.Status;
import dev.canlapan.services.ExpenseService;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PatchExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int expenseID = Integer.parseInt(ctx.pathParam("expenseID"));
        Status status = Status.valueOf(ctx.pathParam("status").toUpperCase());
        Gson gson = new Gson();
        System.out.println(status);

        if(status == Status.APPROVED || status == Status.DENIED) {
            //either approve or deny

           Expense tempExpense = App.expenseService.retrieveExpenseByID(expenseID);
           if(tempExpense != null){
               tempExpense.setExpenseStatus(status);
               Expense updatedExpense = App.expenseService.modifyExpense(expenseID, tempExpense);
               System.out.println(updatedExpense);
               ctx.status(200); //expense has been approved or denied
               ctx.result(updatedExpense.getExpenseStatus().name()); //.name taking the enum and giving the String version of enum
           }else{
               ctx.status(404);
               ctx.result("Expense not found");
           }
        }


    }
}
