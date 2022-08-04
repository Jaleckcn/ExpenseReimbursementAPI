package dev.canlapan.handlers.ExpenseHandlers;

import com.google.gson.Gson;
import dev.canlapan.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class GetSpecificExpenseHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int expenseID = Integer.parseInt(ctx.pathParam("expenseID"));
        Gson gson = new Gson();
        String json = gson.toJson(App.expenseService.retrieveExpenseByID(expenseID));
        ctx.result(json);
        ctx.status(200);
    }
}
