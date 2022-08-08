package dev.canlapan.handlers.EmployeeHandlers;

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
        Employee temp = App.employeeService.retrieveEmployeeByID(employeeID);
        if (temp == null){
            ctx.status(404);
            ctx.result("Employee ID " + employeeID + " not found");
            return;
        }else {
            ctx.status(200);
        }
        String employeeJSON = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(employeeJSON, Employee.class);
        Employee updateEmployee = App.employeeService.modifyEmployee(employeeID, employee);
        String json = gson.toJson(updateEmployee);
        ctx.result(json);
    }
}
