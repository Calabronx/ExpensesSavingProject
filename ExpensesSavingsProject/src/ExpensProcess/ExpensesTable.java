package ExpensProcess; /**
 * This program was developed by me and should not be sold or trade for any march
 * Copyright (C) 2021 Sebastian Calabro
 * ---------- Calabronx -----
 */

import Model.Person;
import Utils.ExpensesConstants;
import Utils.FormattedPrices;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class ExpensesTable {
    private Person person;

    private final Scanner sc = new Scanner(System.in);
    private final Scanner numbers = new Scanner(System.in);

    private DecimalFormat formatter = new DecimalFormat("0.00");
    private HashMap<String, String> map = new HashMap<>();

    private boolean emptyData = false;
    private boolean isZeroSalary = false;

    private String level = null;
    private String monthName = null;
    private String nameExpense = null;
    private final String[] namesArray = new String[100];
    private final String[] expenseArray = new String[100];

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
            String input_name = sc.next();
            person.setName(input_name);
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
            person.setWorkActive(true);
        }
        if (response.equals("2")) {
            person.setWorkActive(false);
        }

        if (person.isWorkActive()) {
            System.out.println("--Salary Ticket--");
            getSalary();
        }

        if (!person.isWorkActive()) {
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

    public void formattingExpenses(double calculate_salary, double percentage) {
        double calculateActualSave = calculate_salary - totalAmount;
        double noWorkTotal = person.getSavings() + calculateActualSave;
        person.setCalculateSaving(calculate_salary - totalAmount);
        //calculateAmount = calculate_salary / calculateSaving;
        savingsExpenses = person.getSavings() - totalAmount;

        savingTotal = person.getSavings() + person.getCalculateSaving();
        if (person.getSavings() < totalAmount) {
            noWorkTotal = 0.0;
        }
        calculateSaveAmount = savingsExpenses / person.getSavings();

        BigDecimal decimal = BigDecimal.valueOf(person.getCalculateSaving());
        formattedPrices.resultSaveExpenses = NumberFormat.getNumberInstance(Locale.US).format(decimal);

        //System.out.println("Total mony saved: $" + resultSaveExpenses);
        BigDecimal decimal_2 = BigDecimal.valueOf(totalAmount);
        formattedPrices.resultExpenses = NumberFormat.getNumberInstance(Locale.US).format(decimal_2);

        if (isZeroSalary) {
            formattedPrices.resultSave = "0.0";
        } else {
            BigDecimal decimal_3 = BigDecimal.valueOf(person.getCalculateSaving());
            formattedPrices.resultSave = NumberFormat.getNumberInstance(Locale.US).format(decimal_3);
        }

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
            if (isZeroSalary) {
                formattedPrices.resultPercentSave = "0";
                formattedPrices.resultPermonth = "0";
                formattedPrices.resultForMonth = "0";
            } else if (calculateAmount == Double.POSITIVE_INFINITY || calculateAmount == Double.NEGATIVE_INFINITY) {
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
        double calculate = person.getThisSalary();
        double half_salary = person.getThisSalary() / 2;
        double percent = person.getThisSalary() * (34.0 / 100);
        double percentSavings = person.getSavings() * (34.0 / 100);
        double percent_minusSave = person.getSavings() * (20.0 / 100);
        double percentMinus = person.getThisSalary() * (20.0 / 100);
        double half_savings = person.getSavings() / 2;

        person.setCalculateSaving(totalAmount - calculate);
        if (person.getCalculateSaving() <= 0) {
            person.setCalculateSaving(0);
        }

        if (person.getSalary() - totalAmount <= 0) {
            BigDecimal total_amnt = BigDecimal.valueOf(totalAmount);
            String parseTotal = NumberFormat.getNumberInstance(Locale.US).format(total_amnt);
            System.out.println("TOTAL EXPENSES YOU HAVE $: " + parseTotal);
            person.setSalary(0);
            System.out.println("Your expenses amount of $" + parseTotal + " are higher than your salary so you are not saving nothing");
            System.out.println("Salary rest is $: " + person.getSalary());
            isZeroSalary = true;
        }

        calculateAmount = calculate / totalAmount;

        calculateSaveAmount = savingsExpenses / person.getSavings();
        DecimalFormat dec = new DecimalFormat("0.0");
        dec.format(totalAmount);
        formattingExpenses(person.getThisSalary(), calculateAmount);

        if (person.isWorkActive()) {
            if (isZeroSalary) {
                level = ExpensesConstants.SAVING_LEVEL_ZERO;
            } else if (person.getCalculateSaving() >= half_salary) {
                level = ExpensesConstants.SAVING_LEVEL_MAX;
            } else if (person.getCalculateSaving() >= percent) {
                level = ExpensesConstants.SAVING_LEVEL_MEDIUM;
            } else if (person.getCalculateSaving() <= percentMinus) {
                level = ExpensesConstants.SAVING_LEVEL_LOW;
            } else {
                level = ExpensesConstants.SAVING_LEVEL_LOW;
            }

        } else if (!person.isWorkActive()) {
            if (savingsExpenses >= half_savings) {
                level = ExpensesConstants.SAVING_LEVEL_MAX;
            } else if (person.getCalculateSaving() >= percentSavings) {
                level = ExpensesConstants.SAVING_LEVEL_MEDIUM;
            } else if (person.getCalculateSaving() <= percent_minusSave) {
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

    public void getSavingsOnly() {
        int counter_saves = 0;
        int counter = 0;
        double savings = 0.0;
        BigDecimal decimal;
        person.setWorkActive(false);
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

    // hacer que el mes sea ingresado por teclado para mayor fluidez con el usuario en este metodo

    public double saveForMonth() {
        if (person.isWorkActive()) {
            ZoneId z = ZoneId.of("America/Buenos_Aires");
            ZonedDateTime zdt = ZonedDateTime.now(z);
            System.out.println("Actual Month: " + zdt.getMonth());
            System.out.println("Until which month would you like to save your money?");
            System.out.println("Please choose a number acording to the month or enter 20 to avoid this calculation");
            System.out.println("1. January 2. Febrary 3. March 4. April 5. May 6. June .7 July 8. Agost 9. September 10. October 11. November 12 . December 22. Ignore step");

            int monthsLeft = 0;

            try {
                monthNumber = numbers.nextInt();
                switch (monthNumber) {
                    case 1:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = person.getCalculateSaving() * monthsLeft;
                        monthName = "January";
                        break;
                    case 2:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = person.getCalculateSaving() * monthsLeft;
                        monthName = "February";
                        break;
                    case 3:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = person.getCalculateSaving() * monthsLeft;
                        monthName = "March";
                        break;
                    case 4:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = person.getCalculateSaving() * monthsLeft;
                        monthName = "April";
                        break;
                    case 5:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = person.getCalculateSaving() * monthsLeft;
                        monthName = "May";
                        break;
                    case 6:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = person.getCalculateSaving() * monthsLeft;
                        monthName = "June";
                        break;
                    case 7:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = person.getCalculateSaving() * monthsLeft;
                        monthName = "July";
                        break;
                    case 8:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = person.getCalculateSaving() * monthsLeft;
                        monthName = "August";
                        break;
                    case 9:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = person.getCalculateSaving() * monthsLeft;
                        monthName = "September";
                        break;
                    case 10:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = person.getCalculateSaving() * monthsLeft;
                        monthName = "October";
                        break;
                    case 11:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = person.getCalculateSaving() * monthsLeft;
                        monthName = "November";
                        break;
                    case 12:
                        monthsLeft = monthNumber - zdt.getMonthValue();
                        perMonth = person.getCalculateSaving() * monthsLeft;
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
                double forMonth = perMonth + savingsExpenses;
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
        if (!person.isWorkActive()) {
            System.out.println("--------------------------------------");
            System.out.println("INGRESOS Y GASTOS MENSUALES");
            System.out.println("--------------------------------------");
            System.out.println("Username: " + person.getName());
            System.out.println("----------Wallet amounts--------------");
            System.out.println("Actual savings: $" + formattedPrices.resultSavings);
            System.out.println("--------------------------------------");
            System.out.println("Savings after expenses: $" + formattedPrices.resulyOfSaveOnly);
            System.out.println("--------------------------------------");
            System.out.println();
            System.out.println("--------Expenses List-----------------");
            System.out.println("ACTUAL SITUATION ->");
        } else {
            System.out.println("--------------------------------------");
            System.out.println("INGRESOS Y GASTOS MENSUALES");
            System.out.println("--------------------------------------");
            System.out.println("Username: " + person.getName());
            System.out.println("----------Wallet amounts--------------");
            System.out.println("Salary: $" + formattedPrices.resultSalary);
            System.out.println("Actual savings: $" + formattedPrices.resultSavings);
            System.out.println("Savings after expenses: $" + formattedPrices.resulyOfSaveOnly);
            System.out.println("--------------------------------------");
            //System.out.println("Actual savings: $" + resultSavingsNoWork);
            System.out.println();
            System.out.println("--------Expenses List------------");
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
        if (person.isWorkActive()) {
            System.out.println("END SITUATION ->");
            System.out.println("---------------------------------\n" +
                    "--------------------------------------");
            System.out.println("Actual Date: " + zdt.getDayOfMonth() + " " + zdt.getMonth() + " of " + zdt.getYear());
            System.out.println("--------------------------------------");
            System.out.println("The total mensual expenses: $" + formattedPrices.resultExpenses);
            System.out.println("Mensual Saving in ARS : $" + formattedPrices.resultSave);
            System.out.println("Percent Saving over Income in ARS : %" + formattedPrices.resultPercentSave);
            if (monthNumber != 22 && !isZeroSalary) {
                System.out.println("Saving $" + formattedPrices.resultSaveExpenses + " each month for " + monthName + " will reach: $" + formattedPrices.resultPermonth);
                System.out.println("And for that month, the total sum of saving with these monthly expenses will be: " + "$" + formattedPrices.resultForMonth);
//                var replace_coma = formattedPrices.resultForMonth.contains(",");
//                if(replace_coma) {
//
//                }
                String parse_amount = formattedPrices.resultForMonth;
                if (parse_amount.length() > 6 && !isZeroSalary) {
                    System.out.println();
                    System.out.println("--------------------------------------");
                    System.out.println("** Dear user " + person.getName());
                    System.out.println("** Try to adjust your maximum expenses to $" + formattedPrices.resultExpenses);
                    System.out.println("** So you will reach a really good save money higher than $100,000 in only a few months");
                }
            }
            System.out.println("Total Money Saved until now : $" + formattedPrices.resulyOfSaveOnly);
            System.out.println("SAVING LEVEL: " + level);
            System.out.println("--------------------------------------");
            System.out.println("--------------------------------------");
        } else {
            System.out.println("END SITUATION ->");
            System.out.println("---------------------------------\n" +
                    "--------------------------------------");
            System.out.println("Actual Date: " + zdt.getDayOfMonth() + " " + zdt.getMonth() + " of " + zdt.getYear());
            System.out.println("--------------------------------------");
            System.out.println("The total mensual expenses: $" + formattedPrices.resultExpenses);
            System.out.println("Mensual Saving in ARS : $" + formattedPrices.resulyOfSaveOnly);
            System.out.println("Percent Saving over Income in ARS : %" + formattedPrices.resultPercentSave);
            //System.out.println("Saving $" + resulyOfSaveOnly + " each month for " + monthName + " will reach: $" + resultPermonth);
            System.out.println("Total Money Saved until now: $" + formattedPrices.resultSavedNosalary);
            System.out.println("SAVING LEVEL: " + level);
            System.out.println("--------------------------------------");
            System.out.println("--------------------------------------");
        }
    }

    public void statisticsInConsole() {
        try {
            if (!person.isWorkActive()) {
                System.out.println("--------------------------------------");
                System.out.println("INGRESOS Y GASTOS MENSUALES");
                System.out.println("--------------------------------------");
                System.out.println("Username: " + person.getName());
                System.out.println("----------Wallet amounts--------------");
                System.out.println("Actual savings: $" + formattedPrices.resultSavings);
                System.out.println("--------------------------------------");
                System.out.println("Savings after expenses: $" + formattedPrices.resulyOfSaveOnly);
                System.out.println();
                System.out.println("--------------------------------------");
                System.out.println("--------Expenses List--------");
                System.out.println("ACTUAL SITUATION ->");
            } else {
                System.out.println("--------------------------------------");
                System.out.println("INGRESOS Y GASTOS MENSUALES");
                System.out.println("--------------------------------------");
                System.out.println("Username: " + person.getName());
                System.out.println("----------Wallet amounts--------------");
                System.out.println("Salary: $" + formattedPrices.resultSalary);
                System.out.println("--------------------------------------");
                System.out.println("Actual savings: $" + formattedPrices.resultSavingsNoWork);
                System.out.println("--------------------------------------");
                System.out.println("Savings after expenses: $" + formattedPrices.resulyOfSaveOnly);
                System.out.println("--------------------------------------");
                System.out.println();
                System.out.println("--------Expenses List--------------------------------------");
                System.out.println("ACTUAL SITUATION ->");
            }

            int anotherCount = 0;
            emptyData = false;
            int i = 0;

            if (namesArray[i] != null && expenseArray[anotherCount] != null) {
                for (i = 0; i < namesArray.length; i++) {
                    System.out.println("* " + namesArray[i] + ": " + "$" + expenseArray[anotherCount]);
                    //map.put(namesArray[i], expenseArray[anotherCount]);
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
}

