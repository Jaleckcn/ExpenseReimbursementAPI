package dev.canlapan.handlers.ExpenseHandler.EmployeeHandlers;

import com.google.gson.Gson;
import dev.canlapan.app.App;
import dev.canlapan.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

//get specific employee by ID
public class GetSpecificEmployeeHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int employeeID = Integer.parseInt(ctx.pathParam("employeeID"));
        Employee employee = App.employeeService.retrieveEmployeeByID(employeeID);
        Gson gson = new Gson();
       try {
           String json = gson.toJson(employee);
           ctx.result(json);
       } catch (RuntimeException e) {
           ctx.status(404);
           ctx.result("Employee ID " + employeeID + " not found");
       }
    }
}
