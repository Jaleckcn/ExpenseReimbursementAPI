package dev.canlapan.entities;

public class Expense {// class are singular, handles are plural
    private int expenseID;//expense ID number
    private double expenseAmount;//ranging from $0.01 to $x.xx
    private Status expenseStatus;//PENDING, APPROVED, DENIED

    private int employeeID;//employee ID the expense is tied to
    private String description;//a description field, ex) hotel accomodations, gas, food
    private String type; //type of expense Business travel, education, supplies, tools


    public Expense(int expenseID, double expenseAmount, Status expenseStatus, int employeeID, String description, String type) {
        this.expenseID = expenseID;
        this.expenseAmount = expenseAmount;
        this.expenseStatus = expenseStatus;
        this.employeeID = employeeID;
        this.description = description;
        this.type = type;
    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public Status getExpenseStatus() {
        return expenseStatus;
    }

    public void setExpenseStatus(Status expenseStatus) {
        this.expenseStatus = expenseStatus;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Expense{" +
                 "Employee ID= " + employeeID + '\'' +
                " Expense ID=" + expenseID + '\'' +
                ", Expense Amount='" + expenseAmount + '\'' +
                ", Description='" + description + '\'' +
                ", Type='" + type + '\'' +
                ", Status=" + expenseStatus +
                '}';
    }
}
