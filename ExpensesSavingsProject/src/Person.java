/**
 * This program was developed by me and should not be sold or trade for any march
 *  Copyright (C) 2021 Sebastian Calabro
 *   ---------- Calabronx -----
 */
public class Person {
    protected static double percentOverIncome = 0.0;
    protected static double calculateSaving = 0.0;
    protected static double total = 0.0;
    protected static String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public static double getPercentOverIncome() {
        return percentOverIncome;
    }

    public static void setPercentOverIncome(double percentOverIncome) {
        Person.percentOverIncome = percentOverIncome;
    }

    public static double getCalculateSaving() {
        return calculateSaving;
    }

    public static void setCalculateSaving(double calculateSaving) {
        Person.calculateSaving = calculateSaving;
    }

    public static double getTotal() {
        return total;
    }

    public static void setTotal(double total) {
        Person.total = total;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Person.name = name;
    }
}
