package Model;

public class Expenses {
    private double salary;
    private double expense;
    private String nameExpense;
    private double total;

    public Expenses(String nameExpense, double expense) {
        this.nameExpense = nameExpense;
        this.expense = expense;
    }

    public double getSalary() { return salary; }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public String getNameExpense() {
        return nameExpense;
    }

    public void setNameExpense(String nameExpense) {
        this.nameExpense = nameExpense;
    }

    public void setSalary(double salary) { this.salary = salary; }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
