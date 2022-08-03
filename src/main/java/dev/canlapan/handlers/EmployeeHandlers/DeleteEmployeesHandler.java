package dev.canlapan.handlers.EmployeeHandlers;

import dev.canlapan.app.App;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class DeleteEmployeesHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        int employeeID = Integer.parseInt(ctx.pathParam("employeeID"));
        boolean result = App.employeeService.deleteEmployeeID(employeeID);
        if(result){
            ctx.status(200);//status code meaning success but nothing is returned
            ctx.result("Deleted Employee ID " + employeeID);
        }else{
            ctx.status(404);
            ctx.result("Employee ID " + employeeID +" not found");
        }
    }
}

