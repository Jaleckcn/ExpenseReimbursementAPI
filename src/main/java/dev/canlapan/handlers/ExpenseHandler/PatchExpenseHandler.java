package dev.canlapan.handlers.ExpenseHandler;

import com.google.gson.Gson;
import dev.canlapan.app.App;
import dev.canlapan.entities.Status;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PatchExpenseHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String status = ctx.queryParam(" "); //insert value here
        Gson gson = new Gson();

//        if(status == null) {
//
//            List<Book> books = App.bookService.getAllBooks();
//            String json = gson.toJson(books);
//            ctx.result(json);
//        } else {
//            List<Book> books = App.bookService.getBookByTitle(title);
//            String json = gson.toJson(books);
//            ctx.result(json);
//        }
    }
}
