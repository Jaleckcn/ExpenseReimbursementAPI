package dev.canlapan.handlers.ExpenseHandler.EmployeeHandlers;

import com.google.gson.Gson;
import dev.canlapan.app.App;
import dev.canlapan.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class UpdateSpecificEmployeeHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int employeeID = Integer.parseInt(ctx.pathParam("employeeID"));
        String employeeJSON = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(employeeJSON, Employee.class);
        Employee updateEmployee = App.employeeService.modifyEmployee(employeeID, employee);

        try {
            String json = gson.toJson(updateEmployee);
            ctx.status(201);
            ctx.result("An employee's field has been updated");
        } catch (RuntimeException e) {
            ctx.status(404);
            ctx.result("Employee ID not found");
        }
    }
}
