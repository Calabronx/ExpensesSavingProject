package ExpensProcess; /**
 * This program was developed by me and should not be sold or trade for any march
 * Copyright (C) 2021 Sebastian Calabro
 * ---------- Calabronx -----
 */

import Model.Expenses;
import Model.Person;
import Utils.ExpensesConstants;
import Utils.FormattedPrices;
import interfaces.addExpensesPanel;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class ExpensesTable extends Person {
    private static Person person = new Person();
    private static Expenses exObj = new Expenses();

    private final Scanner sc = new Scanner(System.in);
    private final Scanner numbers = new Scanner(System.in);
    private final String[] namesArray = new String[100];
    private final String[] expenseArray = new String[100];
    boolean isEntered = true;
    boolean isEnterSalary = true;
    boolean hasFailed = false;
    String input_flag = "";
    String total = "";
    //String categoryLetter;
    int countTries = 0;
    int count = 1;
    int index = 0;
    boolean exit = false;
    HashMap<String, String> map = new HashMap<>();
    private DecimalFormat formatter = new DecimalFormat("0.00");
    private boolean emptyData = false;
    private String level = null;
    private String monthName = null;
    private String nameExpense = "";
    private String response = "";
    private int monthNumber = 0;
    private double totalAmount = 0.0;
    private double expenses = 0.0;
    private double perMonth = 0.0;
    private double savingTotal = 0.0;
    private double calculateSaveAmount = 0.0;
    private double calculateAmount = 0.0;
    private double savingsExpenses = 0.0;
    private FormattedPrices formattedPrices = new FormattedPrices();

    public ExpensesTable(Person person) {
        this.person = person;
        System.out.println("Starting ExpensesProgram.....");
        System.out.println();
        System.out.println("Welcome to my ExpenseSaving program!!");
        System.out.println("--------------------------------------");
        System.out.println();
        System.out.println("Enter user name please");
        try {
            person.name = sc.next();
        } catch (java.util.InputMismatchException e) {
            System.out.println("ERROR: Invalid input");
            e.printStackTrace();
        }
        System.out.println("Client expense table name: " + person.getName());
        System.out.println();
        System.out.println("This program will offer to make 2 types of request, a ticket that calculates from " +
                "\n 1. Your savings and your salary,(with mensual income). \n" +
                " 2. Or a ticket that calculates from your currents savings");
        System.out.println("Choose the ticket that you want to make, 1 or 2 ");
        System.out.println("1.Salary Ticket   2.Saving Ticket");
        String response = " ";
        response = sc.next();
        if (response.equals("1")) {
            isWorkActive = true;
        }
        if (response.equals("2")) {
            isWorkActive = false;
        }

        if (isWorkActive) {
            System.out.println("--Salary Ticket--");
            getSalary();
        }

        if (!isWorkActive) {
            System.out.println("--Saving Ticket--");
            getSavingsOnly();
        }
    }


    public ExpensesTable() {
        if (response.equals("1")) {
            isWorkActive = true;
        }
        if (response.equals("2")) {
            isWorkActive = false;
        }

        if (isWorkActive) {
            System.out.println("--Salary Ticket--");
            //getSalary();
        }

        if (!isWorkActive) {
            System.out.println("--Saving Ticket--");
            //getSavingsOnly();
        }
    }

    public ExpensesTable(Person person, String name) {
        this.person = person;
        this.person.setName(name);
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public String getNameExpense() {
        return nameExpense;
    }

    public void setNameExpense(String nameExpense) {
        this.nameExpense = nameExpense;
    }

    public boolean isEntered() {
        return isEntered;
    }

    public void setEntered(boolean entered) {
        isEntered = entered;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void expenseInfo(String name, double amount) {
        name = nameExpense;
        amount = expenses;
        BigDecimal decimal = BigDecimal.valueOf(amount);
        String result = NumberFormat.getNumberInstance(Locale.US).format(decimal);
        System.out.println(name + ":" + "$" + result);
        String data = name + ":" + "$" + result;
//        System.out.println("Do you want to set an objective?");
//        objective = sc.next();
//        System.out.println("How much is it?");
//        objectiveAmnt = sc.nextDouble();
//        System.out.println("Objective: " + objective + ": $" + objectiveAmnt);
    }

    //Cada gasto ingresado que analize segun su categoria, si gasta demasiado o no. Al final comparar lo que podria ganar con sueldo-gastosTotal/2.
    public void add() throws InputMismatchException, NullPointerException {
        List<Double> listExpenses = new ArrayList<>();
        List<String> listNames = new ArrayList<>();
        boolean hasFailed = false;
        String input_flag = "";
        //String categoryLetter;
        int countTries = 0;
        int count = 0;
        int index = 0;
        count = 1;
        System.out.println("--EXPENSES ENTER VALUES --");
        System.out.println("Enter 0 to exit the segment");
        while (countTries <= 5) {
            if (!hasFailed) {
                input_flag = "WORKING";
                System.out.println(" -- Enter the name expense " + count + " --");
            }
               /* if(input_flag.equals(constants.FAILED_INPUT)) {
                    //nameExpense += sc.nextLine();;
                } else {
                    input_flag = "SUCCESS";
                }*/

            //manejar correctamente el flag para el flujo del proceso
            if (input_flag.equals(ExpensesConstants.PROPERLY_PROCESS)) {
                //input_flag = "SUCCESS";
                nameExpense = sc.next();
                nameExpense += sc.nextLine();
            }
            if (nameExpense.equals("0")) { // puede lanzar nullpointerexception
                count--;
                BigDecimal decimal = BigDecimal.valueOf(totalAmount);
                String result = NumberFormat.getNumberInstance(Locale.US).format(decimal);
                System.out.println("----------------------------");
                System.out.println("--- End of Expense Table ---");
                System.out.println("* Quantity: " + count);
                System.out.println("* Total Expenses: $" + result);
                System.out.println("----------------------------");
                break;
            }
            // spaces+=sc.next();
            namesArray[index] = nameExpense;
            try {
                System.out.println("-- Enter Expense amount of " + nameExpense + " --");
                //numbers.next();
                //numbers.nextDouble();
                if (hasFailed) {
                    BigDecimal decimal = BigDecimal.valueOf(expenses);
                    String result = NumberFormat.getNumberInstance(Locale.US).format(decimal);
                    result = numbers.next();
                    result = numbers.nextLine();
                    result = "0";
                    double passed = Double.parseDouble(result);
                    expenses = passed;
                    //System.out.println("Passed:" + expenses);
                }
                expenses = numbers.nextDouble();

                //expenses += numbers.
                if (expenses == 0) {
                    break;
                }
                BigDecimal decimal = BigDecimal.valueOf(expenses);
                String result = NumberFormat.getNumberInstance(Locale.US).format(decimal);
                //System.out.println("Format ex:" + result);
                expenseArray[index] = result;
                totalAmount += expenses;
                expenseInfo(nameExpense, expenses);
                listExpenses.add(expenses);
                listNames.add(nameExpense);
                count++;
                index++;
                input_flag = "SUCCESS";
                hasFailed = false;
            } catch (InputMismatchException e) {
                countTries++;
                input_flag = ExpensesConstants.FAILED_INPUT;
                hasFailed = true;
                System.out.println("Wrong input, try again");
            } catch (NullPointerException e) {
                System.out.println("NullPointerException was thrown");
                e.getMessage();
            }
            //cs = new Utils.CategorySaving();
            // cs.toMuchExpense(expenses, nameExpense);

//            if(quit) {
//                break;
//            }
        }

    }

    // }

    public void add(JTextField nametxt, JTextField amountxt, JTextField totaltxt) throws InputMismatchException, NullPointerException {
        List<Double> listExpenses = new ArrayList<>();
        List<String> listNames = new ArrayList<>();

        //String categoryLetter;


        System.out.println("--EXPENSES ENTER VALUES --");
        System.out.println("Enter 0 to exit the segment");
//        while (countTries <= 5) {
        if (!hasFailed) {
            input_flag = "WORKING";
            System.out.println(" -- Enter the name expense " + count + " --");
        }
               /* if(input_flag.equals(constants.FAILED_INPUT)) {
                    //nameExpense += sc.nextLine();;
                } else {
                    input_flag = "SUCCESS";
                }*/

        //manejar correctamente el flag para el flujo del proceso
        if (input_flag.equals(ExpensesConstants.PROPERLY_PROCESS)) {
            //input_flag = "SUCCESS";
//                if(nameExpense.equals("")) {
//                    nameExpense = nametxt.getText();
//                    //continue;
//                }
            nameExpense = nametxt.getText();

            //index++;
            //nameExpense += sc.nextLine();
        }
        if (nameExpense.equals("0")) { // puede lanzar nullpointerexception
            count--;
            BigDecimal decimal = BigDecimal.valueOf(totalAmount);
            String result = NumberFormat.getNumberInstance(Locale.US).format(decimal);
            System.out.println("----------------------------");
            System.out.println("--- End of Expense Table ---");
            System.out.println("* Quantity: " + count);
            System.out.println("* Total Expenses: $" + result);
            System.out.println("----------------------------");
            //break;
        }
        // spaces+=sc.next();
        namesArray[index] = nameExpense;
        //nameExpense = "";
        try {
            System.out.println("-- Enter Expense amount of " + nameExpense + " --");
            //numbers.next();
            //numbers.nextDouble();
            if (hasFailed) {
                BigDecimal decimal = BigDecimal.valueOf(expenses);
                String result = NumberFormat.getNumberInstance(Locale.US).format(decimal);
                System.out.println("failiure");
                //result = numbers.next();
                //result = numbers.nextLine();
                result = "0";
                double passed = Double.parseDouble(result);
                expenses = passed;
                //System.out.println("Passed:" + expenses);
            }
            //expenses = numbers.nextDouble();
            //savings = Double.parseDouble(savingstxt.getText());
            //                    person.setSavings(savings);
            int index_new = 0;
            double ex_obtained = 0.0;

            try {
                NumberFormat nf = NumberFormat.getInstance(Locale.US);
                expenses = nf.parse(amountxt.getText()).doubleValue();
            } catch (ParseException e) {
                System.out.println("Parse error");
            }

            try {
                NumberFormat nf = NumberFormat.getInstance(Locale.US);
                totalAmount = nf.parse(totaltxt.getText()).doubleValue();
            } catch (ParseException e) {
                System.out.println("Parse error");
            }

            while (index_new == 5) {
                System.out.println(expenses);
                //expenses = Double.parseDouble(amountxt.getText());
                System.out.println(expenses + "second");
                index_new++;
                if (amountxt.getText().equals(null)) {
                    System.out.println("NULL ES");
                    exit = true;
                }
                if (exit) {
                    break;
                }
            }
            //expenses = ex_obtained;

            System.out.println("EXPENSE: $" + expenses);
            //expenses += numbers.
            if (expenses == 0) {
                // break;
                exit = true;
            }
            BigDecimal decimal = BigDecimal.valueOf(expenses);
            String result = NumberFormat.getNumberInstance(Locale.US).format(decimal);
            //System.out.println("Format ex:" + result);
            expenseArray[index] = result;
            totalAmount += expenses;
            total = totaltxt.getText();
            System.out.println("TOTAL : $" + totalAmount);
            expenseInfo(nameExpense, expenses);
            listExpenses.add(expenses);
            listNames.add(nameExpense);
            count++;
            index++;
            input_flag = "SUCCESS";
            hasFailed = false;
        } catch (InputMismatchException e) {
            countTries++;
            input_flag = ExpensesConstants.FAILED_INPUT;
            hasFailed = true;
            System.out.println("Wrong input, try again");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException was thrown");
            e.getMessage();
        }
        //cs = new Utils.CategorySaving();
        // cs.toMuchExpense(expenses, nameExpense);

//            if(quit) {
//                break;
//            }
    }

    public void formattingExpenses(double calculate_salary, double percentage) {
        double calculateActualSave = calculate_salary - totalAmount;
        double noWorkTotal = person.getSavings() + calculateActualSave;
        calculateSaving = calculate_salary - totalAmount;
        //calculateAmount = calculate_salary / calculateSaving;
        savingsExpenses = person.getSavings() - totalAmount;

        savingTotal = person.getSavings() + calculateSaving;
        if (person.getSavings() < totalAmount) {
            noWorkTotal = 0.0;
        }
        calculateSaveAmount = savingsExpenses / person.getSavings();

        BigDecimal decimal = BigDecimal.valueOf(calculateSaving);
        formattedPrices.resultSaveExpenses = NumberFormat.getNumberInstance(Locale.US).format(decimal);

        //System.out.println("Total mony saved: $" + resultSaveExpenses);
        BigDecimal decimal_2 = BigDecimal.valueOf(totalAmount);
        formattedPrices.resultExpenses = NumberFormat.getNumberInstance(Locale.US).format(decimal_2);
        BigDecimal decimal_3 = BigDecimal.valueOf(calculateSaving);
        formattedPrices.resultSave = NumberFormat.getNumberInstance(Locale.US).format(decimal_3);
        BigDecimal decimal_4 = BigDecimal.valueOf(calculateActualSave);
        formattedPrices.resultNoWork = NumberFormat.getNumberInstance(Locale.US).format(decimal_4);

        BigDecimal decimal_6 = BigDecimal.valueOf(noWorkTotal);
        formattedPrices.resultSavedNosalary = NumberFormat.getNumberInstance(Locale.US).format(decimal_6);
        BigDecimal decimal_7 = BigDecimal.valueOf(person.getSavings());
        formattedPrices.resultSavingsNoWork = NumberFormat.getNumberInstance(Locale.US).format(decimal_7);

        try {
            if (savingsExpenses <= 0) {
                savingsExpenses = 0;
                savingTotal = 0;
                formattedPrices.resulyOfSaveOnly = "0";
                formattedPrices.resultPercentSave = "0";
                formattedPrices.resultTotal = "0";
            } else {
                BigDecimal decimal_8 = BigDecimal.valueOf(savingsExpenses);
                formattedPrices.resulyOfSaveOnly = NumberFormat.getNumberInstance(Locale.US).format(decimal_8);

                BigDecimal decimal_5 = BigDecimal.valueOf(savingTotal);
                formattedPrices.resultTotal = NumberFormat.getNumberInstance(Locale.US).format(decimal_5);
            }

            if (calculateAmount == Double.POSITIVE_INFINITY || calculateAmount == Double.NEGATIVE_INFINITY) {
                System.out.println("Value is infinite");
                percentage = 10;
                BigDecimal decimal_9 = BigDecimal.valueOf(percentage);
                formattedPrices.resultPercentSave = NumberFormat.getNumberInstance(Locale.US).format(decimal_9);
            } else {
                BigDecimal decimal_9 = BigDecimal.valueOf(percentage);
                formattedPrices.resultPercentSave = NumberFormat.getNumberInstance(Locale.US).format(decimal_9);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Error de formateo, arreglar...");
            System.out.println(percentage);
        }
//        System.out.println(percentage);
//        System.out.println(savingTotal);
    }

    public void calculateSavingPercent() {
        //manejar que el porcentaje de ticket savings sea acorde al monto, para que los levels sean coherentes
        double calculate = person.getThisSalary();
        double half_salary = person.getThisSalary() / 2;
        double percent = person.getThisSalary() * (34.0 / 100);
        double percentSavings = person.getSavings() * (10.0 / 100);
        double percent_minusSave = person.getSavings() * (20.0 / 100);
        double percentMinus = person.getThisSalary() * (20.0 / 100);
        double half_savings = person.getSavings() / 2;


        calculateSaving = totalAmount - calculate;
        double objectiveToReach = calculateSaving - objectiveAmnt;
        System.out.println("Te faltan  $" + objectiveToReach + "de tus ahorros actuales para poder adquirir " + objective);


//        if (calculateSaving <= 0) {
//            calculateSaving = 0;
//        }

        //deberia ser el salario luego de las expensas || ahorros luego de expensas


        calculateAmount = calculate / totalAmount;
        double income = calculate - calculateSaving;

        double difference = income / calculate;
        //double cast =  Math.round(difference);
        System.out.println("dif NOT FORMAT " + difference);
        //System.out.println(cast);
        DecimalFormat decimalformat = new DecimalFormat("0.0");
        decimalformat.format(difference);
        System.out.println("f" + difference);


        calculateSaveAmount = savingsExpenses / person.getSavings();
        DecimalFormat dec = new DecimalFormat("0.0");
        dec.format(totalAmount);

        calculateAmount = difference;
        formattingExpenses(person.getThisSalary(), calculateAmount);

        if (person.isWorkActive) {
            if (calculateSaving >= half_salary) {
                level = ExpensesConstants.SAVING_LEVEL_MAX;
            } else if (calculateSaving >= percent) {
                level = ExpensesConstants.SAVING_LEVEL_MEDIUM;
            } else if (calculateSaving <= percentMinus) {
                level = ExpensesConstants.SAVING_LEVEL_LOW;
            } else {
                level = ExpensesConstants.SAVING_LEVEL_LOW;
            }

        } else if (!person.isWorkActive) {
            if (savingsExpenses >= half_savings) {
                level = ExpensesConstants.SAVING_LEVEL_MAX;
            } else if (calculateSaving >= percentSavings) {
                level = ExpensesConstants.SAVING_LEVEL_MEDIUM;
            } else if (calculateSaving <= percent_minusSave) {
                level = ExpensesConstants.SAVING_LEVEL_LOW;
            } else {
                level = ExpensesConstants.SAVING_LEVEL_LOW;
            }
        }
    }

    // generar try cath por si el usuario ingresa 0, eso haria lanzar una excepcion fatal y cerrar el programa de mala manera si no se maneja
    public void getSalary() {
        BigDecimal decimal;
        BigDecimal anotherDecimal;
        double savings = 0.0;
        int counter = 0;
        int counter_saves = 0;
        double salary = 0.0;
        boolean success = true;
        while (counter_saves < 4) {
            boolean successSaves = true;
            try {
                System.out.println("Enter your current savings that you have");
                if (counter < 3) {
                    savings = numbers.nextDouble();
                    person.setSavings(savings);
                    System.out.println(formatter.format(person.getSavings()));
                    decimal = BigDecimal.valueOf(person.getSavings());
                    formattedPrices.resultSavings = NumberFormat.getNumberInstance(Locale.US).format(decimal);
                    System.out.println("--Savings : $" + formattedPrices.resultSavings);
                }
            } catch (InputMismatchException e) {
                successSaves = false;
                counter_saves++;
                System.out.println("invalid input entered....");
            }
            numbers.nextLine();

            if (counter_saves == 4) {
                System.out.println("To much wrong attemps, exiting program");
                sc.close();
                System.exit(0);
            }

            if (successSaves) {
                break;
            }
        }
        System.out.println("Enter your salary amount");
        while (counter < 4) {
            success = true;
            try {
                if (counter < 3) {
                    salary = numbers.nextDouble();
                    person.setSalary(salary);
                    //System.out.println(formatter.format(salary));
                    anotherDecimal = BigDecimal.valueOf(person.getThisSalary());
                    formattedPrices.resultSalary = NumberFormat.getNumberInstance(Locale.US).format(anotherDecimal);
                    System.out.println("--Your salary is : $" + formattedPrices.resultSalary);
                }
            } catch (InputMismatchException e) {
                success = false;
                counter++;
            }
//                if (counter == 4) {
//                    System.out.println();
//                } else {
//                    System.out.println("A letter is a invalid input!");
//                    System.out.println("Sorry, try again");
//                    System.out.println("Enter your salary amount");
//                }
            numbers.nextLine();

            if (counter == 4) {
                System.out.println("To much wrong attemps, exiting program");
                sc.close();
                System.exit(0);
            }

            if (success) {
                break;
            }
        }
        if (person.getThisSalary() <= 0) {
            System.out.println("Sorry a 0 or negative number is not allowed...");
            System.exit(0);
            System.out.print(" Salary: $" + person.getThisSalary());
        }
    }

    public int getSalary(JTextField salarytxt, JTextField savingstxt) {
        BigDecimal decimal;
        BigDecimal anotherDecimal;

        double savings = 0.0;
        int counter = 0;
        int counter_saves = 0;
        int flag = 1;
        double salary = 0.0;
        boolean success = false;
        while (counter_saves < 4) {
            if (success) {
                break;
            }

            boolean successSaves = true;
            try {
                System.out.println("Enter your current savings that you have");
                if (counter < 3) {
                    //while(!isEntered) {
                    System.out.println(savings);
                    try {
                        NumberFormat nf = NumberFormat.getInstance(Locale.US);
                        savings = nf.parse(savingstxt.getText()).doubleValue();
                        System.out.println(savings);
                    } catch (ParseException e) {
                        System.out.println("errorr");
                    }

                    //savings = Double.parseDouble(savingstxt.getText());
                    person.setSavings(savings);
                    decimal = BigDecimal.valueOf(person.getSavings());
                    formattedPrices.resultSavings = NumberFormat.getNumberInstance(Locale.US).format(decimal);
                    success = true;
                }
//                if (savings == 0.0) {
//                    flag = -1;
//                }
                //}

                System.out.println("--Savings : $" + formattedPrices.resultSavings);


            } catch (InputMismatchException e) {
                success = false;
                counter_saves++;
                System.out.println("invalid input entered....");
            }
            //numbers.nextLine();

//            if (counter_saves == 4) {
//                System.out.println("To much wrong attemps, exiting program");
//                sc.close();
//                System.exit(0);
//            }

//            if (successSaves) {
//                break;
//            }

            System.out.println("Enter your salary amount");
            while (counter < 4) {
                success = true;
                try {
                    if (counter < 3) {
                        try {
                            NumberFormat nf = NumberFormat.getInstance(Locale.US);
                            salary = nf.parse(salarytxt.getText()).doubleValue();
                        } catch (ParseException e) {
                            System.out.println("Error parsing");
                        }

                        person.setSalary(salary);
                        //System.out.println(formatter.format(salary));
                        anotherDecimal = BigDecimal.valueOf(person.getThisSalary());
                        formattedPrices.resultSalary = NumberFormat.getNumberInstance(Locale.US).format(anotherDecimal);
                        System.out.println("--Your salary is : $" + formattedPrices.resultSalary);
                    }
                    if (salary == 0.0) {
                        flag = -1;
                    }
                } catch (InputMismatchException e) {
                    success = false;
                    counter++;
                }
//                if (counter == 4) {
//                    System.out.println();
//                } else {
//                    System.out.println("A letter is a invalid input!");
//                    System.out.println("Sorry, try again");
//                    System.out.println("Enter your salary amount");
//                }
                //numbers.nextLine();

                if (counter == 4) {
                    System.out.println("To much wrong attemps, exiting program");
                    sc.close();
                    System.exit(0);
                }

                if (success) {
                    break;
                }
            }
//            if (person.getThisSalary() <= 0) {
//                System.out.println("Sorry a 0 or negative number is not allowed...");
//                System.exit(0);
//                System.out.print(" Salary: $" + person.getThisSalary());
//            }

        }
        return flag;
    }

    public int getSavingsOnly(JTextField savingstxt) {
        int counter_saves = 0;
        int counter = 0;
        int flag = 1;
        double savings = 0.0;
        BigDecimal decimal;
        isWorkActive = false;
        while (counter_saves < 4) {
            boolean successSaves = true;
            try {
                NumberFormat nf = NumberFormat.getInstance(Locale.US);
                savings = nf.parse(savingstxt.getText()).doubleValue();
                System.out.println(savings);
                person.setSavings(savings);
                BigDecimal decimal_1 = BigDecimal.valueOf(savings);
                formattedPrices.resultSavings = NumberFormat.getNumberInstance(Locale.US).format(decimal_1);
                System.out.println("--Savings : $" + formattedPrices.resultSavings);
            } catch (ParseException e) {
                System.out.println("errorr");

            } catch (InputMismatchException e) {
                successSaves = false;
                counter_saves++;
                System.out.println("invalid input entered....");
            }
            //numbers.nextLine();

            if (counter_saves == 4) {
                System.out.println("To much wrong attemps, exiting program");
                //sc.close();
                System.exit(0);
            }

            if (successSaves) {
                break;
            }
            if (savings == 0.0) {
                flag = -1;
            }
        }

        return flag;
    }

    // hacer que el mes sea ingresado por teclado para mayor fluidez con el usuario en este metodo

    public void getSavingsOnly() {
        int counter_saves = 0;
        int counter = 0;
        double savings = 0.0;
        BigDecimal decimal;
        isWorkActive = false;
        while (counter_saves < 4) {
            boolean successSaves = true;
            try {
                System.out.println("Enter your current savings that you have");
                if (counter < 3) {
                    savings = numbers.nextDouble();
                    person.setSavings(savings);
                    BigDecimal decimal_1 = BigDecimal.valueOf(person.getSavings());
                    formattedPrices.resultSavings = NumberFormat.getNumberInstance(Locale.US).format(decimal_1);
                    System.out.println("--Savings : $" + formattedPrices.resultSavings);
                }
            } catch (InputMismatchException e) {
                successSaves = false;
                counter_saves++;
                System.out.println("invalid input entered....");
            }
            numbers.nextLine();

            if (counter_saves == 4) {
                System.out.println("To much wrong attemps, exiting program");
                sc.close();
                System.exit(0);
            }

            if (successSaves) {
                break;
            }
        }
        try {
            //System.out.println(formatter.format(savings));
            decimal = BigDecimal.valueOf(person.getSavings());
            formattedPrices.resultSavings = NumberFormat.getNumberInstance(Locale.US).format(decimal);
        } catch (InputMismatchException ex) {
            ex.getCause();
            System.out.println("invalid input entered....");
        }
    }

    public double saveForMonth(String chooseMonth, int monthNum) {
        if (isWorkActive) {
            ZoneId z = ZoneId.of("America/Buenos_Aires");
            ZonedDateTime zdt = ZonedDateTime.now(z);
            System.out.println("Actual Month: " + zdt.getMonth());
            System.out.println("Until which month would you like to save your money?");
            System.out.println("Please choose a number acording to the month or enter 20 to avoid this calculation");
            System.out.println("1. January 2. Febrary 3. March 4. April 5. May 6. June .7 July 8. Agost 9. September 10. October 11. November 12 . December 22. Ignore step");

            int monthsLeft = 0;
            monthNumber = monthNum;
            try {
                //monthNumber = numbers.nextInt();
                switch (monthNumber) {
                    case 1:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = monthsLeft * calculateSaving;
                        monthName = "January";
                        break;
                    case 2:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = monthsLeft * calculateSaving;
                        monthName = "February";
                        break;
                    case 3:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = monthsLeft * calculateSaving;
                        monthName = "March";
                        break;
                    case 4:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = monthsLeft * calculateSaving;
                        monthName = "April";
                        break;
                    case 5:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = monthsLeft * calculateSaving;
                        monthName = "May";
                        break;
                    case 6:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = monthsLeft * calculateSaving;
                        monthName = "June";
                        break;
                    case 7:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = monthsLeft * calculateSaving;
                        monthName = "July";
                        break;
                    case 8:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = monthsLeft * calculateSaving;
                        monthName = "August";
                        break;
                    case 9:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = monthsLeft * calculateSaving;
                        monthName = "September";
                        break;
                    case 10:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = monthsLeft * calculateSaving;
                        monthName = "October";
                        break;
                    case 11:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = monthsLeft * calculateSaving;
                        monthName = "November";
                        break;
                    case 12:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = monthsLeft * calculateSaving;
                        monthName = "December";
                        break;
                    case 22:
                        break;
                    default:
                        monthName = "Invalid month entered, try again";
                        break;
                }
            } catch (InputMismatchException in) {
                in.printStackTrace();
                System.out.println("Invalid input entered, maybe cause you entered a letter instead a number...");
                //System.exit(0);
            }
            if (monthNumber != 22) {
                double forMonth = 0.0;
                if (perMonth < 0) {
                    forMonth = perMonth + savingsExpenses;
                } else {
                    forMonth = savingsExpenses + perMonth;
                }
                BigDecimal decimal_8 = BigDecimal.valueOf(perMonth);
                formattedPrices.resultPermonth = NumberFormat.getNumberInstance(Locale.US).format(decimal_8);
                BigDecimal decimal_9 = BigDecimal.valueOf(forMonth);
                formattedPrices.resultForMonth = NumberFormat.getNumberInstance(Locale.US).format(decimal_9);

                System.out.println("Saving $" + formattedPrices.resultSave + " each month for " + monthName + " will reach: $" + formattedPrices.resultPermonth);
                System.out.println("And for that month, the total will be: " + "$" + formattedPrices.resultForMonth);
//        } else if(!person.isWorkActive) {
//            System.out.println("Saving $" + resulyOfSaveOnly + " each month for " + monthName + " will reach: $" + perMonth);
//            BigDecimal decimal_9 = BigDecimal.valueOf(perMonth);
//            resultPermonth = NumberFormat.getNumberInstance(Locale.US).format(decimal_9);
//        }
            }
        }
        return perMonth;

    }

    private void saveFile() {
        File file = null;
        JFileChooser saveFile = new JFileChooser();
        System.out.println("-------------------------------");
        try {
            String src = JOptionPane.showInputDialog("Enter a name for the ticket file");
            saveFile.setSelectedFile(new File(src + ".txt"));
            //saveFile.showSaveDialog(saveFile);
        } catch (NullPointerException e) {
            System.out.println("Save file option canceled");
        }
        int sf = saveFile.showSaveDialog(null);
        file = saveFile.getSelectedFile();
        if (sf == JFileChooser.APPROVE_OPTION) {

            //src = sc.next()

            try (PrintStream out = new PrintStream((new FileOutputStream(file)))) {

            } catch (FileNotFoundException ex) {
                System.out.println("Something went wrong finding the file...");
                System.out.println(ex.getMessage());
            } catch (NullPointerException e) {
                e.getMessage();
            }
        }
        try {
            System.setOut(new PrintStream(file));
        } catch (FileNotFoundException f) {
            System.out.println("File not found");
        } catch (NullPointerException e) {
            System.out.println("The original path or source or the fail doesnt exist..");
            e.getMessage();
        } finally {
            sc.close();
            numbers.close();
        }
    }

    public void printList() {
        if (emptyData) {
            System.out.println("Empty expenses list, not making file.txt");
        } else if (!emptyData) {
            saveFile();
        }
        if (!person.isWorkActive) {
            System.out.println("-------------------------------");
            System.out.println("INGRESOS Y GASTOS MENSUALES");
            System.out.println("-------------------------------");
            System.out.println("Username: " + person.getName());
            System.out.println("----------Wallet amounts-----------");
            System.out.println("Actual savings: $" + formattedPrices.resultSavings);
            System.out.println("Savings after expenses: $" + formattedPrices.resulyOfSaveOnly);
            System.out.println("-------------------------------");
            System.out.println();
            System.out.println("--------Expenses List--------");
            System.out.println("ACTUAL SITUATION ->");
        } else {
            System.out.println("-------------------------------");
            System.out.println("INGRESOS Y GASTOS MENSUALES");
            System.out.println("-------------------------------");
            System.out.println("Username: " + person.getName());
            System.out.println("----------Wallet amounts-----------");
            System.out.println("Salary: $" + formattedPrices.resultSalary);
            System.out.println("Actual savings: $" + formattedPrices.resultSavings);
            System.out.println("Savings after expenses: $" + formattedPrices.resulyOfSaveOnly);
            System.out.println("-------------------------------");
            //System.out.println("Actual savings: $" + resultSavingsNoWork);
            System.out.println();
            System.out.println("--------Expenses List--------");
            System.out.println("ACTUAL SITUATION ->");
        }

        int anotherCount = 0;
        int i = 0;

        try {
            if (namesArray[i] != null && expenseArray[anotherCount] != null) {
                for (i = 0; i < namesArray.length; i++) {
                    System.out.println("* " + namesArray[i] + ": " + "$" + expenseArray[anotherCount]);
                    anotherCount++;
                    if (namesArray[i] == null || expenseArray[anotherCount] == null) {
                        break;
                    }
                }

                statisticsExpenses();
            } else {
                System.out.println("Empty expenses list, not making file.txt");
            }
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            exception.getMessage();
        } finally {
            sc.close();
            numbers.close();
        }
    }

    public void statisticsExpenses() {
        //formatter.format(savings);
        formatter.format(calculateAmount);
        ZoneId z = ZoneId.of("America/Buenos_Aires");
        ZonedDateTime zdt = ZonedDateTime.now(z);
        if (person.isWorkActive) {
            System.out.println("Actual Date: " + zdt.getDayOfMonth() + " " + zdt.getMonth() + " of " + zdt.getYear());
            System.out.println("--------------------------------------");
            System.out.println("The total mensual expenses: $" + formattedPrices.resultExpenses);
            System.out.println("Mensual Saving in ARS : $" + formattedPrices.resultSave);
            System.out.println("Percent Saving over Income in ARS : %" + formattedPrices.resultPercentSave);
            if (monthNumber != 22) {
                System.out.println("Saving $" + formattedPrices.resultSaveExpenses + " each month for " + monthName + " will reach: $" + formattedPrices.resultPermonth);
            }
            System.out.println("Total Money Saved until now : $" + formattedPrices.resultTotal);
            System.out.println("Total Money Saved until next paycheck: $" + formattedPrices.resultSavedNosalary);

            System.out.println("SAVING LEVEL: " + level);
            System.out.println("-------------------------------");
        } else {
            System.out.println("Actual Date: " + zdt.getDayOfMonth() + " " + zdt.getMonth() + " of " + zdt.getYear());
            System.out.println("--------------------------------------");
            System.out.println("The total mensual expenses: $" + formattedPrices.resultExpenses);
            System.out.println("Mensual Saving in ARS : $" + formattedPrices.resulyOfSaveOnly);
            System.out.println("Percent Saving over Income in ARS : %" + formattedPrices.resultPercentSave);
            //System.out.println("Saving $" + resulyOfSaveOnly + " each month for " + monthName + " will reach: $" + resultPermonth);
            System.out.println("Total Money Saved until now : $" + formattedPrices.resulyOfSaveOnly);
            System.out.println("SAVING LEVEL: " + level);
            System.out.println("-------------------------------");
        }
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }

    public String[] getNamesArray() {
        return namesArray;
    }

    public String[] getExpenseArray() {
        return expenseArray;
    }

    public void statisticsInConsole() {
        try {
            if (!person.isWorkActive) {
                System.out.println("-------------------------------");
                System.out.println("INGRESOS Y GASTOS MENSUALES");
                System.out.println("-------------------------------");
                System.out.println("Username: " + person.getName());
                System.out.println("----------Wallet amounts-----------");
                System.out.println("Actual savings: $" + formattedPrices.resultSavings);
                System.out.println("Savings after expenses: $" + formattedPrices.resulyOfSaveOnly);
                System.out.println();
                System.out.println("--------Expenses List--------");
                System.out.println("ACTUAL SITUATION ->");
            } else {
                System.out.println("-------------------------------");
                System.out.println("INGRESOS Y GASTOS MENSUALES");
                System.out.println("-------------------------------");
                System.out.println("Username: " + person.getName());
                System.out.println("----------Wallet amounts-----------");
                System.out.println("Salary: $" + formattedPrices.resultSalary);
                System.out.println("Actual savings: $" + formattedPrices.resultSavingsNoWork);
                System.out.println("Savings after expenses: $" + formattedPrices.resulyOfSaveOnly);
                System.out.println();
                System.out.println("--------Expenses List--------");
                System.out.println("ACTUAL SITUATION ->");
            }
            int anotherCount = 0;
            emptyData = false;
            int i = 0;

            if (namesArray[i] != null && expenseArray[anotherCount] != null) {
                for (i = 0; i < namesArray.length; i++) {
                    System.out.println("* " + namesArray[i] + ": " + "$" + expenseArray[anotherCount]);
                    map.put(namesArray[i], expenseArray[anotherCount]);
                    anotherCount++;
                    if (namesArray[i] == null || expenseArray[anotherCount] == null) {
                        break;
                    }
                }
                statisticsExpenses();
            } else {
                System.out.println(" ---No expenses added in list--- ");
                emptyData = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            sc.close();
            numbers.close();
        }
        System.out.println("--------------------------------------");
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println("This program was developed by me and should not be sold or trade for any march");
        System.out.println("Copyright (C) 2021-2022 Sebastian Calabro");
        System.out.println();
        System.out.println("---------- Calabronx -----");
    }

    public void printToGui(addExpensesPanel panel) {

    }
}

