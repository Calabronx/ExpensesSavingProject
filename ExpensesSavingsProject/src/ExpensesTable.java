/**
 * This program was developed by me and should not be sold or trade for any march
 * Copyright (C) 2021 Sebastian Calabro
 * ---------- Calabronx -----
 */

import javax.swing.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class ExpensesTable extends Person {
    private List<Expenses> expensesList = new ArrayList<>();
    private List<Double> list = new ArrayList<>();
    private List<String> listNames = new ArrayList<>();
    private CategorySaving cs;
    private Person person;
    private Scanner sc = new Scanner(System.in);
    private Scanner numbers = new Scanner(System.in);
    private DecimalFormat formatter = new DecimalFormat("0.00");
    private double expenses = 0.0;
    private String nameExpense = null;
    private double totalAmount = 0.0;
    public double salary = 0.0;
    private double calculateAmount = 0.0;
    private double calculateActualSave = 0.0;
    private double savingsExpenses = 0.0;
    private int expensesQuant = 0;
    private int count = 1;
    private String[] namesArray = new String[100];
    private String[] expenseArray = new String[100];
    private int index = 0;
    private int monthNumber = 0;
    private static final String SAVING_LEVEL_MAX = "HIGH";
    private static final String SAVING_LEVEL_LOW = "LOW";
    private String level = null;
    private String monthName = null;
    private double perMonth = 0.0;
    private double savings = 0.0;
    private double savingTotal = 0.0;
    private double calculateSaveAmount = 0.0;

    private String response = " ";
    private String resultSavings ="";
    private String resultSalary ="";
    private String resultSaveExpenses = "";
    private String resultExpenses ="";
    private String resultSave ="";
    private String resultNoWork ="";
    private String resultTotal ="";
    private String resultSavedNosalary ="";
    private String resultSavingsNoWork ="";
    private String resultPermonth ="";
    private String resulyOfSaveOnly ="";

    public ExpensesTable(Person person) {
        this.person = person;
        System.out.println("Welcome to my ExpenseSaving program!!");
        System.out.println("Dedicado para los gatos mas gatos que ahorran como el culo");
        System.out.println("-- A partir de ahora el programa va a estar en ingles");
        System.out.println("demo");
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
        System.out.println("Are you actual working somewhere? Y/N");
        response = sc.next();
        if (response.equals("N") || response.equals("n")) {
            isWorkActive = false;
            try {
                System.out.println("Enter your current savings that you have");
                savings = sc.nextDouble();
                //System.out.println(formatter.format(savings));
            } catch (InputMismatchException ex) {
                ex.getCause();
                System.out.println("invalid input entered....");
            }
        }
    }
  //  }

    public void expenseInfo(String name, double amount) {
        name = nameExpense;
        amount = expenses;
        System.out.println(name + ":" + "$" + amount);
    }

    //Cada gasto ingresado que analize segun su categoria, si gasta demasiado o no. Al final comparar lo que podria ganar con sueldo-gastosTotal/2.
    public void add() {
        int counter = 0;
        boolean success = true;
        String categoryLetter;
        System.out.println("Please enter quantity of expenses that you have");
        while (counter < 4) {
            success = true;
            try {
                if (counter < 3) {
                    expensesQuant = numbers.nextInt();
                }
            } catch (InputMismatchException e) {
                success = false;
                counter++;
                if (counter == 4) {
                    System.out.println();
                } else {
                    System.out.println("A letter is a invalid input!");
                    System.out.println("Sorry, try again");
                    System.out.println("Enter quantity of expenses");
                }
                numbers.nextLine();

                if (counter == 4) {
                    System.out.println("To much wrong attemps, exiting program");
                    sc.close();
                    System.exit(0);
                }

            }
            if (success) {
                break;
            }
        }
        int countTries = 0;
        String spaces = "";
        while (count <= expensesQuant && countTries <= 3) {
            System.out.println(" -- Enter the name expense " + count + " --");
            nameExpense = sc.next();
            nameExpense+= sc.nextLine();
           // spaces+=sc.next();
            namesArray[index] = nameExpense;
            try {
                System.out.println("-- Enter Expense amount --");
                expenses = numbers.nextDouble();
                BigDecimal decimal = BigDecimal.valueOf(expenses);
                String result = NumberFormat.getNumberInstance(Locale.US).format(decimal);
                //System.out.println("Format ex:" + result);
                expenseArray[index] = result;
                totalAmount += expenses;
                expenseInfo(nameExpense, expenses);
                list.add(expenses);
                listNames.add(nameExpense);
                count++;
                index++;
            } catch (InputMismatchException e) {
                countTries++;
                System.out.println("Wrong input, try again");
            }
            //cs = new CategorySaving();
            // cs.toMuchExpense(expenses, nameExpense);

        }

    }

    public void calculateSavingPercent() {
        double calculate = salary;
        double half_salary = salary / 2;
        double half_savings = savings / 2;

        calculateSaving = calculate - totalAmount;
        calculateAmount = calculate / calculateSaving;
        calculateActualSave = calculate - totalAmount;
        savingsExpenses = savings - totalAmount;
        savingTotal = savings + calculateSaving;
        double noWorkTotal = savings + calculateActualSave;
        calculateSaveAmount = savingsExpenses / savings;

        BigDecimal decimal = BigDecimal.valueOf(calculateSaving);
        resultSaveExpenses = NumberFormat.getNumberInstance(Locale.US).format(decimal);
        //System.out.println("Total mony saved: $" + resultSaveExpenses);
        BigDecimal decimal_2 = BigDecimal.valueOf(totalAmount);
        resultExpenses = NumberFormat.getNumberInstance(Locale.US).format(decimal_2);
        BigDecimal decimal_3 = BigDecimal.valueOf(calculateSaving);
        resultSave = NumberFormat.getNumberInstance(Locale.US).format(decimal_3);
        BigDecimal decimal_4 = BigDecimal.valueOf(calculateActualSave);
        resultNoWork = NumberFormat.getNumberInstance(Locale.US).format(decimal_4);
        BigDecimal decimal_5 = BigDecimal.valueOf(savingTotal);
        resultTotal = NumberFormat.getNumberInstance(Locale.US).format(decimal_5);
        BigDecimal decimal_6 = BigDecimal.valueOf(noWorkTotal);
        resultSavedNosalary = NumberFormat.getNumberInstance(Locale.US).format(decimal_6);
        BigDecimal decimal_7 = BigDecimal.valueOf(savings);
        resultSavingsNoWork = NumberFormat.getNumberInstance(Locale.US).format(decimal_7);
        BigDecimal decimal_8 = BigDecimal.valueOf(savingsExpenses);
        resulyOfSaveOnly = NumberFormat.getNumberInstance(Locale.US).format(decimal_8);
       // System.out.println("de:"+ savingsExpenses);
        //System.out.println(resulyOfSaveOnly);
        //System.out.println(half_salary);
        if(person.isWorkActive) {
            if (calculateSaving >= half_salary) {
                level = SAVING_LEVEL_MAX;
            } else {
                level = SAVING_LEVEL_LOW;
            }
        }else if(!person.isWorkActive) {
            if (savingsExpenses >= half_savings) {
                level = SAVING_LEVEL_MAX;
            } else {
                level = SAVING_LEVEL_LOW;
            }
        }
    }

    // generar try cath por si el usuario ingresa 0, eso haria lanzar una excepcion fatal y cerrar el programa de mala manera si no se maneja
    public void getSalary() {
        BigDecimal decimal;
        BigDecimal anotherDecimal;
        savings = 0.0;
        int counter = 0;
        int counter_saves = 0;
        boolean success = true;
        while (counter_saves < 4) {
            boolean successSaves = true;
            try {
                System.out.println("Enter your current savings that you have");
                if (counter < 3) {
                    savings = numbers.nextDouble();
                    System.out.println(formatter.format(savings));
                    decimal = BigDecimal.valueOf(savings);
                    resultSavings = NumberFormat.getNumberInstance(Locale.US).format(decimal);
                    System.out.println("--Savings : $" + resultSavings);
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
                    //System.out.println(formatter.format(salary));
                    anotherDecimal = BigDecimal.valueOf(salary);
                    resultSalary = NumberFormat.getNumberInstance(Locale.US).format(anotherDecimal);
                    System.out.println("--Your salary is : $"+ resultSalary);
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
        if (salary <= 0) {
            System.out.println("Sorry a 0 or negative number is not allowed...");
            System.exit(0);
            System.out.print(" Salary: $" + salary);
        }
    }

    public void getSavingsOnly() {
        BigDecimal decimal;
        try {
            //System.out.println(formatter.format(savings));
            decimal = BigDecimal.valueOf(savings);
            resultSavings = NumberFormat.getNumberInstance(Locale.US).format(decimal);
        } catch (InputMismatchException ex) {
            ex.getCause();
            System.out.println("invalid input entered....");
        }
    }

    public double bringSalary() {
        return this.salary;
    }

    // hacer que el mes sea ingresado por teclado para mayor fluidez con el usuario en este metodo

    public double saveForMonth() {
        ZoneId z = ZoneId.of("America/Buenos_Aires");
        ZonedDateTime zdt = ZonedDateTime.now(z);
        System.out.println("Actual Month: " + zdt.getMonth());
        System.out.println("Until which month would you like to save your money?");
        System.out.println("Please choose a number acording to the month or enter 20 to avoid this calculation");
        System.out.println("1. January 2. Febrary 3. March 4. April 5. May 6. June .7 July 8. Agost 9. September 10. October 11. November 12 . December 22. Ignore step");

        try {
            monthNumber = numbers.nextInt();
            switch (monthNumber) {
                case 1:
                    perMonth = calculateSaving * monthNumber;
                    monthName = "January";
                    break;
                case 2:
                    perMonth = calculateSaving * monthNumber;
                    monthName = "February";
                    break;
                case 3:
                    perMonth = calculateSaving * monthNumber;
                    monthName = "March";
                    break;
                case 4:
                    perMonth = calculateSaving * monthNumber;
                    monthName = "April";
                    break;
                case 5:
                    perMonth = calculateSaving * monthNumber;
                    monthName = "May";
                    break;
                case 6:
                    perMonth = calculateSaving * monthNumber;
                    monthName = "June";
                    break;
                case 7:
                    perMonth = calculateSaving * monthNumber;
                    monthName = "July";
                    break;
                case 8:
                    perMonth = calculateSaving * monthNumber;
                    monthName = "August";
                    break;
                case 9:
                    perMonth = calculateSaving * monthNumber;
                    monthName = "September";
                    break;
                case 10:
                    perMonth = calculateSaving * monthNumber;
                    monthName = "October";
                    break;
                case 11:
                    perMonth = calculateSaving * monthNumber;
                    monthName = "November";
                    break;
                case 12:
                    perMonth = calculateSaving * monthNumber;
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
            BigDecimal decimal_8 = BigDecimal.valueOf(perMonth);
            resultPermonth = NumberFormat.getNumberInstance(Locale.US).format(decimal_8);
            System.out.println("Saving $" + resultSave + " each month for " + monthName + " will reach: $" + resultPermonth);
        /*} else if(!person.isWorkActive) {
            System.out.println("Saving $" + resulyOfSaveOnly + " each month for " + monthName + " will reach: $" + perMonth);
            BigDecimal decimal_9 = BigDecimal.valueOf(perMonth);
            resultPermonth = NumberFormat.getNumberInstance(Locale.US).format(decimal_9);
        }*/
        }
        return perMonth;

    }

    public void printList() {
        File file = null;
        JFileChooser saveFile = new JFileChooser();
        System.out.println("-------------------------------");
        //System.out.println("--Enter the path location to save the ticket expenses");
        try {
            String src = JOptionPane.showInputDialog("Enter a name for the ticket file, and a type extension that you prefer");

            saveFile.setSelectedFile(new File(src));
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
                System.out.println("Something went wrong...");
            } catch (NullPointerException e) {
                e.getMessage();
            }
        }
        try {
            System.setOut(new PrintStream(file));
        } catch (FileNotFoundException f) {
            System.out.println("File not found");
        } catch (NullPointerException e) {
            e.getMessage();
        }
        if (!person.isWorkActive) {
            System.out.println("INGRESOS Y GASTOS MENSUALES");
            System.out.println("---------------");
            System.out.println("Actual savings: $" + resultSavings);
            System.out.println("Savings after expenses: $" + resulyOfSaveOnly);
            System.out.println();
            System.out.println("--------Expenses List--------");
            System.out.println("ACTUAL SITUATION ->");
        } else {
            System.out.println("INGRESOS Y GASTOS MENSUALES");
            System.out.println("Salary: $" + resultSalary);
            System.out.println("---------------");
            System.out.println("Actual savings: $" + resultSavingsNoWork);
            System.out.println("Savings after expenses: $" + resulyOfSaveOnly);
            System.out.println();
            System.out.println("--------Expenses List--------");
            System.out.println("ACTUAL SITUATION ->");
        }

        int anotherCount = 0;

        for (int i = 0; i < namesArray.length; i++) {
            System.out.println(namesArray[i] + ": " + "$" + expenseArray[anotherCount]);
            anotherCount++;
            if (namesArray[i] == null) {
                break;
            }
            if (expenseArray[anotherCount] == null) {
                break;
            }
        }
        statisticsExpenses();
        sc.close();
        numbers.close();
    }

    public void statisticsExpenses() {
        //formatter.format(savings);
        formatter.format(calculateAmount);
        ZoneId z = ZoneId.of("America/Buenos_Aires");
        ZonedDateTime zdt = ZonedDateTime.now(z);
        if (person.isWorkActive) {
            System.out.println("Actual Date: " + zdt.getDayOfMonth() + " " + zdt.getMonth() + " of " + zdt.getYear());
            System.out.println("Username: " + person.getName());
            System.out.println("--------------------------------------");
            System.out.println("The total mensual expenses: $" + resultExpenses);
            System.out.println("Mensual Saving in ARS : $" + resultSave);
            System.out.println("Percent Saving over Income in ARS : %" + calculateAmount);
            System.out.println("Saving $" + resultSaveExpenses + " each month for " + monthName + " will reach: $" + resultPermonth);
            System.out.println("Total Money Saved will be : $" + resultTotal);
            System.out.println("SAVING LEVEL: " + level);
        } else {
            System.out.println("Actual Date: " + zdt.getDayOfMonth() + " " + zdt.getMonth() + " of " + zdt.getYear());
            System.out.println("Username: " + person.getName());
            System.out.println("--------------------------------------");
            System.out.println("The total mensual expenses: $" + resultExpenses);
            System.out.println("Mensual Saving in ARS : $" + resulyOfSaveOnly);
            System.out.println("Percent Saving over Income in ARS : %" + calculateSaveAmount);
            //System.out.println("Saving $" + resulyOfSaveOnly + " each month for " + monthName + " will reach: $" + resultPermonth);
            System.out.println("Total Money Saved will be : $" + resultSavedNosalary);
            System.out.println("SAVING LEVEL: " + level);
        }
    }

    public void statisticsInConsole() {
        try {
            if (!person.isWorkActive) {
                System.out.println("INGRESOS Y GASTOS MENSUALES");
                System.out.println("---------------");
                System.out.println("Actual savings: $" + resultSavings);
                System.out.println("Savings after expenses: $" + resulyOfSaveOnly);
                System.out.println();
                System.out.println("--------Expenses List--------");
                System.out.println("ACTUAL SITUATION ->");
            }else {
                System.out.println("INGRESOS Y GASTOS MENSUALES");
                System.out.println("Salary: $" + resultSalary);
                System.out.println("---------------");
                System.out.println("Actual savings: $" + resultSavingsNoWork);
                System.out.println("Savings after expenses: $" + resulyOfSaveOnly);
                System.out.println();
                System.out.println("--------Expenses List--------");
                System.out.println("ACTUAL SITUATION ->");
            }

            int anotherCount = 0;

            for (int i = 0; i < namesArray.length; i++) {
                System.out.println(namesArray[i] + ": " + "$" + expenseArray[anotherCount]);
                anotherCount++;
                if (namesArray[i] == null) {
                    break;
                }
                if (expenseArray[anotherCount] == null) {
                    break;
                }
            }
            statisticsExpenses();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("--------------------------------------");
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println("This program was developed by me and should not be sold or trade for any march");
        System.out.println("Copyright (C) 2021 Sebastian Calabro");
        System.out.println();
        System.out.println("---------- Calabronx -----");
    }
}

