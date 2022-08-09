package dev.canlapan.handlers.EmployeeHandlers;

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
           System.out.println(json.getClass().getName());

           //Checking to see if the Employee ID is valid. If not, return 404 status
           if (json.equals("null")) {
               ctx.status(404);
               ctx.result("Employee ID " + employeeID + " not found");
           } else {
               ctx.result(json);
               ctx.status(200);
           }
       } catch (RuntimeException e) {
           ctx.status(500);
           ctx.result("Error");
       }
    }
}
