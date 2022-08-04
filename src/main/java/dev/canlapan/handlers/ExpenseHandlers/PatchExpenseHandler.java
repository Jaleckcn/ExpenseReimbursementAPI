package dev.canlapan.handlers.ExpenseHandlers;

import com.google.gson.Gson;
import dev.canlapan.entities.Expense;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class PatchExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String status = ctx.queryParam("status");
        Gson gson = new Gson();
        System.out.println(status);

//        if(status == null) {
//
//            List<Expense> books = App.bookService.getAllBooks();
//            String json = gson.toJson(books);
//            ctx.result(json);
//        } else {
//            List<Book> books = App.bookService.getBookByTitle(title);
//            String json = gson.toJson(books);
//            ctx.result(json);
//        }
    }
}
