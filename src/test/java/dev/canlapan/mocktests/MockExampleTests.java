package dev.canlapan.mocktests;

import dev.canlapan.daos.EmployeeDAO;
import dev.canlapan.daos.ExpenseDAO;
import dev.canlapan.entities.Employee;
import dev.canlapan.entities.Expense;
import dev.canlapan.entities.Status;
import dev.canlapan.services.EmployeeService;
import dev.canlapan.services.EmployeeServiceImpl;
import dev.canlapan.services.ExpenseService;
import dev.canlapan.services.ExpenseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MockExampleTests {
    @Test
    void mock_1(){
        List<String> employeeList = Mockito.mock(List.class);
        Mockito.when(employeeList.get(10)).thenReturn("Jalec");
        System.out.println(employeeList.get(10));
    }

    @Test
    void register_employee_must_have_full_name(){
        EmployeeDAO employeeDAO = Mockito.mock(EmployeeDAO.class);
        Employee employee = new Employee(1,"Chris","");
        Mockito.when(employeeDAO.createEmployee(employee)).thenReturn(employee);
        EmployeeService employeeService = new EmployeeServiceImpl(employeeDAO);

        //Will throw an exception if either employeeFirstName or employeeLastName is blank
        Assertions.assertThrows(RuntimeException.class, () -> {
            employeeService.registerEmployee(employee);
        });
    }

    @Test
    void registered_expenses_must_have_expense_amount(){
        ExpenseDAO expenseDAO = Mockito.mock(ExpenseDAO.class);
        Expense expense = new Expense(1,-100.00f, Status.PENDING,1,"Gas for company car","BUSINESS TRAVEL");
        Mockito.when(expenseDAO.createExpense(expense)).thenReturn(expense);
        ExpenseService expenseService = new ExpenseServiceImpl(expenseDAO);

        //Will throw an exception if expense amount is not greater than 0
        Assertions.assertThrows(RuntimeException.class, () -> {
            expenseService.registerExpense(expense);
        });
    }

    @Test
    void sort_expenses_by_status(){
        ExpenseDAO expenseDAO = Mockito.mock(ExpenseDAO.class);
        //creating a list of fake expenses
        List<Expense> fakeExpenses = new ArrayList<>();
        fakeExpenses.add(new Expense(0,100.00f, Status.PENDING,1,"Gas for company car","BUSINESS TRAVEL"));
        fakeExpenses.add(new Expense(0,346.75f, Status.DENIED,2,"Hotel room for one night on Vacation","BUSINESS TRAVEL"));
        fakeExpenses.add(new Expense(0,4000.29f, Status.PENDING,3,"Computers for Work Room 101","BUSINESS TOOLS"));
        fakeExpenses.add(new Expense(0,5000.00f, Status.APPROVED,1,"Tuition for Fall 2022 Semester","EDUCATION/TRAINING"));

        Mockito.when(expenseDAO.getAllExpenses()).thenReturn(fakeExpenses);

        ExpenseService expenseService = new ExpenseServiceImpl(expenseDAO);
        List<Expense> sortedExpenses = expenseService.getStatus(Status.PENDING);
        Assertions.assertEquals(2,sortedExpenses.size());
        List<Expense> sortedExpenses2 = expenseService.getStatus(Status.DENIED);
        Assertions.assertEquals(1,sortedExpenses2.size());
    }

}
