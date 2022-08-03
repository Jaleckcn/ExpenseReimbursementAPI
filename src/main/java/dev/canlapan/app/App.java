package dev.canlapan.app;

import dev.canlapan.daos.EmployeeDAOLocal;
import dev.canlapan.daos.ExpenseDAOLocal;
import dev.canlapan.entities.Employee;
import dev.canlapan.entities.Expense;
import dev.canlapan.handlers.EmployeeHandlers.*;
import dev.canlapan.handlers.ExpenseHandler.*;
import dev.canlapan.services.EmployeeService;
import dev.canlapan.services.EmployeeServiceImpl;
import dev.canlapan.services.ExpenseService;
import dev.canlapan.services.ExpenseServiceImpl;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static List<Employee> employee = new ArrayList();
    public static List<Expense> expense = new ArrayList();
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOLocal());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDAOLocal());

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
        app.get("/expenses?status=pending", patchExpenseHandler);
        app.get("expenses/{id}",getSpecificExpenseHandler);
        app.put("/expenses/{id}",updateExpenseHandler);
        app.patch("/expenses/{id}",patchExpenseHandler); //patch is used to change the Status field of the Expenses
        app.patch("/expense/{id}",patchExpenseHandler);
        app.delete("/expenses/{id}",deleteExpenseHandler);


        app.start();
    }
}

