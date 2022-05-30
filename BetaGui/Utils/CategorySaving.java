package Utils;

import Model.Person;
import Utils.ExpensesConstants;

import java.util.Scanner;

public class CategorySaving {
    private static final double VALUE_MAX = 3000.0;
    protected static String category = null;
    protected double userSalary;
    private double analizeExpense;
    private double saveAdvice = 0;
    private boolean isHigh = false;
    private Scanner cs = new Scanner(System.in);
    private Person person;


    public CategorySaving() {
    }

    public static double getValueMax() {
        return VALUE_MAX;
    }

    public static void main(String[] args) {

    }

    public double getAnalizeExpense() {
        return analizeExpense;
    }

    public void setAnalizeExpense(double analizeExpense) {
        this.analizeExpense = analizeExpense;
    }

    public boolean isHigh() {
        return isHigh;
    }

    public void setHigh(boolean high) {
        isHigh = high;
    }

    public double getSaveAdvice() {
        return saveAdvice;
    }

    public void setSaveAdvice(int saveAdvice) {
        this.saveAdvice = saveAdvice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getUserSalary() {
        return userSalary;
    }

    public int setUserSalary(double userSalary) {
        this.userSalary = userSalary;
        return 0;
    }

    public void toMuchExpense(double expense, String nameExpense) {
        //  if(category != "E") {
        //      return;
        //  }
        System.out.println("-- Enter the category of the expense");
        System.out.println("[ E. Entertainment - C. Clothes - B. Bills - D.Alchool/Drugs");
        category = cs.next();
        if (category.equals(ExpensesConstants.ENTERT_VALUE)) {
            //saveAdvice = ex.salary;
            saveAdvice /= 2;
            if (expense > VALUE_MAX) {
                System.out.println("Hey man,you are spending over $3000 on " + nameExpense + " try to limit to " + saveAdvice);
            }
        }
//        saveAdvice = setUserSalary(userSalary);
//        saveAdvice /= 2;
//        if(expense > VALUE_MAX) {
//            System.out.println("Hey man,you are spending over $3000 on " + nameExpense + " try to limit to" + saveAdvice);
        //  }
    }
}
