package dev.canlapan.app;

import com.google.gson.Gson;
import dev.canlapan.daos.EmployeeDAOLocal;
import dev.canlapan.daos.EmployeeDAOPostgres;
import dev.canlapan.daos.ExpenseDAOLocal;
import dev.canlapan.daos.ExpenseDAOPostgres;
import dev.canlapan.entities.Employee;
import dev.canlapan.entities.Expense;
import dev.canlapan.handlers.EmployeeHandlers.*;
import dev.canlapan.handlers.ExpenseHandlers.*;
import dev.canlapan.services.EmployeeService;
import dev.canlapan.services.EmployeeServiceImpl;
import dev.canlapan.services.ExpenseService;
import dev.canlapan.services.ExpenseServiceImpl;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static List<Employee> employee = new ArrayList();
    public static List<Expense> expense = new ArrayList();
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOPostgres());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDAOPostgres());

    public static void main(String[] args) {
        Javalin app = Javalin.create();

        CreateEmployeesHandler createEmployeesHandler = new CreateEmployeesHandler();
        GetAllEmployeesHandler getAllEmployeesHandler = new GetAllEmployeesHandler();
        GetSpecificEmployeeHandler getSpecificEmployeeHandler = new GetSpecificEmployeeHandler();
        DeleteEmployeesHandler deleteEmployeesHandler = new DeleteEmployeesHandler();
        UpdateSpecificEmployeeHandler updateSpecificEmployeeHandler = new UpdateSpecificEmployeeHandler();

        app.post("/employees", createEmployeesHandler);
        app.get("/employees",getAllEmployeesHandler);
        app.get("/employees/{employeeID}",getSpecificEmployeeHandler);
        app.delete("/employees/{employeeID}", deleteEmployeesHandler);
        app.put("/employees/{employeeID}",updateSpecificEmployeeHandler);

        CreateExpenseHandler createExpenseHandler = new CreateExpenseHandler();
        GetAllExpensesHandler getAllExpensesHandler = new GetAllExpensesHandler();
        GetSpecificExpenseHandler getSpecificExpenseHandler = new GetSpecificExpenseHandler();
        DeleteExpenseHandler deleteExpenseHandler = new DeleteExpenseHandler();
        UpdateExpenseHandler updateExpenseHandler = new UpdateExpenseHandler();
        PatchExpenseHandler patchExpenseHandler = new PatchExpenseHandler();


        app.post("/expenses",createExpenseHandler);
        app.get("/expenses",getAllExpensesHandler);
        app.get("/expenses/{expenseID}",getSpecificExpenseHandler);
        app.put("/expenses/{expenseID}",updateExpenseHandler);
        app.patch("/expenses/{expenseID}/{status}",patchExpenseHandler); //patch is used to change the Status field of the Expenses
        app.delete("/expenses/{expenseID}",deleteExpenseHandler);

        Handler getAllExpensesByEmployeeID = ctx ->{
            int employeeID =  Integer.parseInt(ctx.pathParam("id"));
            if (App.employeeService.retrieveEmployeeByID(employeeID) == null){
                ctx.status(404);
                ctx.result("Employee ID not found");
            }else{
                Gson gson = new Gson();
                List<Expense> expenses = App.expenseService.getAllExpenseByEmployeeID(employeeID);
                String json = gson.toJson(expenses);
                ctx.result(json);
            }
        };
        app.get("/employees/{id}/expenses",getAllExpensesByEmployeeID); //returns expenses for employee 120


       Handler createExpenseByEmployeeID = ctx -> {
           int employeeID = Integer.parseInt(ctx.pathParam("id"));
           if (App.employeeService.retrieveEmployeeByID(employeeID) == null) {
               ctx.status(404);
               ctx.result("Employee ID not found");
           } else {
               String json = ctx.body();
               Gson gson = new Gson();
               Expense expense = gson.fromJson(json, Expense.class);
               expense.setEmployeeID(employeeID);
               Expense registerExpense = App.expenseService.registerExpense(expense);
               String expenseJson = gson.toJson(registerExpense);
               ctx.status(201); //successful in creating new Expense
               ctx.result(expenseJson);
           }
       };
       app.post("/employees/{id}/expenses",createExpenseByEmployeeID);//adds an expense to employee 120
       app.start();
    }
}

