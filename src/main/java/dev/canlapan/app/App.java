package dev.canlapan.app;

import dev.canlapan.daos.EmployeeDAOLocal;
import dev.canlapan.daos.EmployeeDAOPostgres;
import dev.canlapan.daos.ExpenseDAOLocal;
import dev.canlapan.entities.Employee;
import dev.canlapan.entities.Expense;
import dev.canlapan.handlers.EmployeeHandlers.*;
import dev.canlapan.handlers.ExpenseHandlers.*;
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
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDAOPostgres());
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
        app.get("/expenses/{status}", patchExpenseHandler); //question mark denotes the following key as the query parameter
        app.get("expenses/{expenseID}",getSpecificExpenseHandler);
        app.put("/expenses/{expenseID}",updateExpenseHandler);
        app.patch("/expenses/{expenseID}/{status}",patchExpenseHandler); //patch is used to change the Status field of the Expenses
        app.delete("/expenses/{expenseID}",deleteExpenseHandler);
//
//        app.get("/employees/{id}/expenses",null); //returns expenses for employee 120
//        app.post("/employees/{id}/expenses",null);//adds an expense to employee 120


        app.start();
    }
}

