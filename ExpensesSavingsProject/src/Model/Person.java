package Model;

/**
 * This program was developed by me and should not be sold or trade for any march
 * Copyright (C) 2021 Sebastian Calabro
 * ---------- Calabronx -----
 */
public class Person {
    private String name;
    private double percentOverIncome = 0.0;
    private double calculateSaving = 0.0;
    private double total = 0.0;
    private double salary = 0.0;
    private double savings = 0.0;
    private boolean isWorkActive = true;

    public Person() {
    }

    public double getSalary() {
        return salary;
    }

    public Person(String name) {
        this.name = name;
    }

    public double getPercentOverIncome() {
        return percentOverIncome;
    }

    public void setPercentOverIncome(double percentOverIncome) {
        this.percentOverIncome = percentOverIncome;
    }

    public double getCalculateSaving() {
        return calculateSaving;
    }

    public void setCalculateSaving(double calculateSaving) {
        this.calculateSaving = calculateSaving;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWorkActive() {
        return isWorkActive;
    }

    public void setWorkActive(boolean workActive) {
        isWorkActive = workActive;
    }

    public double getThisSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }
}
