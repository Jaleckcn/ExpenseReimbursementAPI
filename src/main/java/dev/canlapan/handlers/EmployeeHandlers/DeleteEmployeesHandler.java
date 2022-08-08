package dev.canlapan.handlers.EmployeeHandlers;

import dev.canlapan.app.App;
import dev.canlapan.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteEmployeesHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int employeeID = Integer.parseInt(ctx.pathParam("employeeID"));
        Employee temp = App.employeeService.retrieveEmployeeByID(employeeID);
        if (temp == null){
            ctx.status(404);
            ctx.result("Employee ID " + employeeID + " not found");
            return;
        }else {
            App.employeeService.deleteEmployeeID(employeeID);
            ctx.status(200);
            ctx.result("Employee has been deleted")
;        }
    }
}

