package dev.canlapan.entities;

public class Expense {// class are singular, handles are plural
    private int expenseID;//id
    private double expenseAmount;//
    private Status expenseStatus;//
    private Employee employee;
    private String description;//a description field
    private String type; //type of expense


    public Expense(int expenseID, double expenseAmount, Status expenseStatus, Employee employee, String description, String type) {
        this.expenseID = expenseID;
        this.expenseAmount = expenseAmount;
        this.expenseStatus = expenseStatus;
        this.employee = employee;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
                " Expense id=" + expenseID +
                ", Expense Amount='" + expenseAmount + '\'' +
                ", Description='" + description + '\'' +
                ", Type='" + type + '\'' +
                ", Status=" + expenseStatus +
                '}';
    }
}
