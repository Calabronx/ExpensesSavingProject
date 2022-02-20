/**
 * This program was developed by me and should not be sold or trade for any march
 * Copyright (C) 2021 Sebastian Calabro
 * ---------- Calabronx -----
 */

import ExpensProcess.ExpensesTable;
import Model.Person;

import java.io.*;

public class Main {

    public static void enterSalary(ExpensesTable ex) {
        ex.getSalary();
    }

    public static void savingPercent(ExpensesTable ex) {
        //ex.addExpenses();
        ex.calculateSavingPercent();
    }

    public static void printList(ExpensesTable ex) {
        ex.printList();
    }

    public static void addExpense(ExpensesTable ex) {
        ex.add();
    }

    public static void save(ExpensesTable ex) {
        ex.saveForMonth();
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Person user = new Person();

        ExpensesTable expenses = new ExpensesTable(user);
        System.out.println("--------------------------------------");
        System.out.println();


        System.out.println("--------------------------------------");
        addExpense(expenses);
        System.out.println("--------------------------------------");
        savingPercent(expenses);
        save(expenses);
        System.out.println();
        expenses.statisticsInConsole();
        printList(expenses);
        System.out.println("--------------------------------------");
        //expenses.statisticsExpenses();
        System.out.println();
    }
}

