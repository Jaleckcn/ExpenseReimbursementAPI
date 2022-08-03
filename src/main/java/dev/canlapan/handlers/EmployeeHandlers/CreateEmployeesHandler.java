package dev.canlapan.handlers.EmployeeHandlers;

import com.google.gson.Gson;
import dev.canlapan.app.App;
import dev.canlapan.entities.Employee;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public class CreateEmployeesHandler implements Handler {


    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String json = ctx.body(); //takes json string and converts it into an instance of an object
        Gson gson = new Gson();
        Employee employee = gson.fromJson(json, Employee.class);
        Employee registerEmployee = App.employeeService.registerEmployee(employee);
        String employeesJson = gson.toJson(registerEmployee);
        App.employee.add(employee);
        ctx.status(201);
        ctx.result(employeesJson);
    }
}
