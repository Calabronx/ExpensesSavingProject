package ExpensProcess; /**
 * This program was developed by me and should not be sold or trade for any march
 * Copyright (C) 2021 Sebastian Calabro
 * ---------- Calabronx -----
 */

import Model.Expenses;
import Model.Person;
import Utils.ExpensesConstants;
import Utils.FormattedPrices;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.List;

public class ExpensesTable extends Person {
    private List<Expenses> expensesList = new ArrayList<>();
    private List<Double> list = new ArrayList<>();
    private List<String> listNames = new ArrayList<>();
    private Person person;
    private Scanner sc = new Scanner(System.in);
    private Scanner numbers = new Scanner(System.in);
    private DecimalFormat formatter = new DecimalFormat("0.00");
    private boolean emptyData = false;
    private boolean otherEmptyData = false;
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
    private String level = null;
    private String monthName = null;
    private double perMonth = 0.0;
    private double savings = 0.0;
    private double savingTotal = 0.0;
    private double calculateSaveAmount = 0.0;

    private FormattedPrices formattedPrices = new FormattedPrices();
    private ExpensesConstants constants;




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
        System.out.println("Are you actual working somewhere? Y/N");
        String response = " ";
        response = sc.next();
        if (response.equals("N") || response.equals("n")) {
            isWorkActive = false;
        }
        if (isWorkActive) {
            System.out.println("--Salary Ticket--");
            getSalary();

        } else {
            System.out.println("--Saving Ticket--");
            getSavingsOnly();
        }
    }

    public void expenseInfo(String name, double amount) {
        name = nameExpense;
        amount = expenses;
        BigDecimal decimal = BigDecimal.valueOf(amount);
        String result = NumberFormat.getNumberInstance(Locale.US).format(decimal);
        System.out.println(name + ":" + "$" + result);
    }

    //Cada gasto ingresado que analize segun su categoria, si gasta demasiado o no. Al final comparar lo que podria ganar con sueldo-gastosTotal/2.
    public void add() throws InputMismatchException, NullPointerException {
        int counter = 0;
        boolean hasFailed = false;
        String input_flag = "";
        //String categoryLetter;
        int countTries = 0;
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
            if (input_flag.equals(constants.PROPERLY_PROCESS)) {
                //input_flag = "SUCCESS";
                nameExpense = sc.next();
                nameExpense += sc.nextLine();
            }
            if (nameExpense.equals("0")) { // puede lanzar nullpointerexception
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
                list.add(expenses);
                listNames.add(nameExpense);
                count++;
                index++;
                input_flag = "SUCCESS";
                hasFailed = false;
            } catch (InputMismatchException e) {
                countTries++;
                input_flag = constants.FAILED_INPUT;
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

    public void formattingExpenses(double calculate_salary, double percentage) {
        calculateSaving = calculate_salary - totalAmount;
        calculateAmount = calculate_salary / calculateSaving;
        calculateActualSave = calculate_salary - totalAmount;
        savingsExpenses = savings - totalAmount;
        savingTotal = savings + calculateSaving;
        double noWorkTotal = savings + calculateActualSave;
        calculateSaveAmount = savingsExpenses / savings;

        BigDecimal decimal = BigDecimal.valueOf(calculateSaving);
        formattedPrices.resultSaveExpenses = NumberFormat.getNumberInstance(Locale.US).format(decimal);

        //System.out.println("Total mony saved: $" + resultSaveExpenses);
        BigDecimal decimal_2 = BigDecimal.valueOf(totalAmount);
        formattedPrices.resultExpenses = NumberFormat.getNumberInstance(Locale.US).format(decimal_2);
        BigDecimal decimal_3 = BigDecimal.valueOf(calculateSaving);
        formattedPrices.resultSave = NumberFormat.getNumberInstance(Locale.US).format(decimal_3);
        BigDecimal decimal_4 = BigDecimal.valueOf(calculateActualSave);
        formattedPrices.resultNoWork = NumberFormat.getNumberInstance(Locale.US).format(decimal_4);
        BigDecimal decimal_5 = BigDecimal.valueOf(savingTotal);
        formattedPrices.resultTotal = NumberFormat.getNumberInstance(Locale.US).format(decimal_5);
        BigDecimal decimal_6 = BigDecimal.valueOf(noWorkTotal);
        formattedPrices.resultSavedNosalary = NumberFormat.getNumberInstance(Locale.US).format(decimal_6);
        BigDecimal decimal_7 = BigDecimal.valueOf(savings);
        formattedPrices.resultSavingsNoWork = NumberFormat.getNumberInstance(Locale.US).format(decimal_7);
        BigDecimal decimal_8 = BigDecimal.valueOf(savingsExpenses);
        formattedPrices.resulyOfSaveOnly = NumberFormat.getNumberInstance(Locale.US).format(decimal_8);
        BigDecimal decimal_9 = BigDecimal.valueOf(percentage);
        formattedPrices.resultPercentSave = NumberFormat.getNumberInstance(Locale.US).format(decimal_9);
    }

    public void calculateSavingPercent() {
        double calculate = salary;
        double half_salary = salary / 2;
        double percent = salary * (34.0 / 100);
        double percentSavings = savings * (34.0 / 100);
        double percent_minusSave = savings * (20.0 / 100);
        double percentMinus = salary * (20.0 / 100);
        double half_savings = savings / 2;

        calculateSaving = calculate - totalAmount;
        calculateAmount = calculate / calculateSaving;
        calculateSaveAmount = savingsExpenses / savings;

        formattingExpenses(salary, calculateAmount);

        if (person.isWorkActive) {
            if (calculateSaving >= half_salary) {
                level = constants.SAVING_LEVEL_MAX;
            } else if (calculateSaving >= percent) {
                level = constants.SAVING_LEVEL_MEDIUM;
            } else if (calculateSaving <= percentMinus) {
                level = constants.SAVING_LEVEL_LOW;
            }

        } else if (!person.isWorkActive) {
            if (savingsExpenses >= half_savings) {
                level = constants.SAVING_LEVEL_MAX;
            } else if (calculateSaving >= percentSavings) {
                level = constants.SAVING_LEVEL_MEDIUM;
            } else if (calculateSaving <= percent_minusSave) {
                level = constants.SAVING_LEVEL_LOW;
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
                    //System.out.println(formatter.format(salary));
                    anotherDecimal = BigDecimal.valueOf(salary);
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
        if (salary <= 0) {
            System.out.println("Sorry a 0 or negative number is not allowed...");
            System.exit(0);
            System.out.print(" Salary: $" + salary);
        }
    }

    public void getSavingsOnly() {
        int counter_saves = 0;
        int counter = 0;
        BigDecimal decimal;
        isWorkActive = false;
        while (counter_saves < 4) {
            boolean successSaves = true;
            try {
                System.out.println("Enter your current savings that you have");
                if (counter < 3) {
                    savings = numbers.nextDouble();
                    BigDecimal decimal_1 = BigDecimal.valueOf(savings);
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
            decimal = BigDecimal.valueOf(savings);
            formattedPrices.resultSavings = NumberFormat.getNumberInstance(Locale.US).format(decimal);
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
            formattedPrices.resultPermonth = NumberFormat.getNumberInstance(Locale.US).format(decimal_8);
            System.out.println("Saving $" + formattedPrices.resultSave + " each month for " + monthName + " will reach: $" + formattedPrices.resultPermonth);
//        } else if(!person.isWorkActive) {
//            System.out.println("Saving $" + resulyOfSaveOnly + " each month for " + monthName + " will reach: $" + perMonth);
//            BigDecimal decimal_9 = BigDecimal.valueOf(perMonth);
//            resultPermonth = NumberFormat.getNumberInstance(Locale.US).format(decimal_9);
//        }
        }
        return perMonth;

    }

    private void saveFile() {
        File file = null;
        JFileChooser saveFile = new JFileChooser();
        System.out.println("-------------------------------");
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
            System.out.println("INGRESOS Y GASTOS MENSUALES");
            System.out.println("---------------");
            System.out.println("Actual savings: $" + formattedPrices.resultSavings);
            System.out.println("Savings after expenses: $" + formattedPrices.resulyOfSaveOnly);
            System.out.println();
            System.out.println("--------Expenses List--------");
            System.out.println("ACTUAL SITUATION ->");
        } else {
            System.out.println("INGRESOS Y GASTOS MENSUALES");
            System.out.println("Salary: $" + formattedPrices.resultSalary);
            System.out.println("---------------");
            //System.out.println("Actual savings: $" + resultSavingsNoWork);
            System.out.println("Savings after expenses: $" + formattedPrices.resulyOfSaveOnly);
            System.out.println();
            System.out.println("--------Expenses List--------");
            System.out.println("ACTUAL SITUATION ->");
        }

        int anotherCount = 0;
        int i = 0;

        try {
            if (namesArray[i] != null && expenseArray[anotherCount] != null) {
                for (i = 0; i < namesArray.length; i++) {
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
            System.out.println("Username: " + person.getName());
            System.out.println("--------------------------------------");
            System.out.println("The total mensual expenses: $" + formattedPrices.resultExpenses);
            System.out.println("Mensual Saving in ARS : $" + formattedPrices.resultSave);
            System.out.println("Percent Saving over Income in ARS : %" + formattedPrices.resultPercentSave);
            if (monthNumber != 22) {
                System.out.println("Saving $" + formattedPrices.resultSaveExpenses + " each month for " + monthName + " will reach: $" + formattedPrices.resultPermonth);
            }
            System.out.println("Total Money Saved will be : $" + formattedPrices.resultTotal);
            System.out.println("SAVING LEVEL: " + level);
        } else {
            System.out.println("Actual Date: " + zdt.getDayOfMonth() + " " + zdt.getMonth() + " of " + zdt.getYear());
            System.out.println("Username: " + person.getName());
            System.out.println("--------------------------------------");
            System.out.println("The total mensual expenses: $" + formattedPrices.resultExpenses);
            System.out.println("Mensual Saving in ARS : $" + formattedPrices.resulyOfSaveOnly);
            System.out.println("Percent Saving over Income in ARS : %" + formattedPrices.resultPercentSave);
            //System.out.println("Saving $" + resulyOfSaveOnly + " each month for " + monthName + " will reach: $" + resultPermonth);
            System.out.println("Total Money Saved will be : $" + formattedPrices.resultSavedNosalary);
            System.out.println("SAVING LEVEL: " + level);
        }
    }

    public void statisticsInConsole() {
        try {
            if (!person.isWorkActive) {
                System.out.println("INGRESOS Y GASTOS MENSUALES");
                System.out.println("---------------");
                System.out.println("Actual savings: $" + formattedPrices.resultSavings);
                System.out.println("Savings after expenses: $" + formattedPrices.resulyOfSaveOnly);
                System.out.println();
                System.out.println("--------Expenses List--------");
                System.out.println("ACTUAL SITUATION ->");
            } else {
                System.out.println("INGRESOS Y GASTOS MENSUALES");
                System.out.println("Salary: $" + formattedPrices.resultSalary);
                System.out.println("---------------");
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
}

