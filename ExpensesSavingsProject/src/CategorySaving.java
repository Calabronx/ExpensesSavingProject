public class CategorySaving {
    private double analizeExpense;
    private double userSalary;
    private int saveAdvice = 0;
    private static final double VALUE_MAX = 3000.0;
    private String category = null;
    private boolean isHigh = false;


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

    public int getSaveAdvice() {
        return saveAdvice;
    }

    public void setSaveAdvice(int saveAdvice) {
        this.saveAdvice = saveAdvice;
    }

    public static double getValueMax() {
        return VALUE_MAX;
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
        if(category != "Entertainment") {
            return;
        }
        saveAdvice = setUserSalary(userSalary);
        saveAdvice /= 2;
        if(expense > VALUE_MAX) {
            System.out.println("Hey man,you are spending over $3000 on " + nameExpense + " try to limit to" + saveAdvice);
        }
    }
}
