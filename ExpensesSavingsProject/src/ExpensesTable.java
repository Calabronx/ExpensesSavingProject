/**
 * This program was developed by me and should not be sold or trade for any march
 *  Copyright (C) 2021 Sebastian Calabro
 *   ---------- Calabronx -----
 */
import java.io.File;
import java.io.PrintStream;
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
    private double expenses = 0.0;
    private String nameExpense = null;
    private double totalAmount = 0.0;
    private double salary = 0.0;
    private double calculateAmount = 0.0;
    private int expensesQuant = 0;
    private int count = 1;
    private String[] namesArray = new String[100];
    private double[] expenseArray = new double[100];
    private int index = 0;
    private int monthNumber = 0;
    private static final String SAVING_LEVEL_MAX = "HIGH";
    private static final String SAVING_LEVEL_LOW = "LOW";
    private String level = null;
    private String monthName = null;
    private double perMonth = 0.0;

    public ExpensesTable(Person person) {
        this.person = person;
        System.out.println("Welcome to my ExpenseSaving program!!");
        System.out.println("Dedicado para los gatos mas gatos que ahorran como el culo");
        System.out.println("-- A partir de ahora el programa va a estar en ingles");
        System.out.println("--------------------------------------");
        System.out.println();
        System.out.println("Enter user name please");
        try {
            person.name = sc.next();
        }catch(java.util.InputMismatchException e) {
            System.out.println("ERROR: Invalid input");
            e.printStackTrace();
        }
        System.out.println("Client expense table name: " + person.getName());
    }

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
        while(counter<4) {
            success = true;
            try {
                if(counter <3) {
                    expensesQuant = sc.nextInt();
                }
            } catch (InputMismatchException e) {
                success = false;
                counter++;
                if(counter == 4) {
                    System.out.println();
                } else {
                    System.out.println("A letter is a invalid input!");
                    System.out.println("Sorry, try again");
                    System.out.println("Enter quantity of expenses");
                }
                sc.nextLine();

                if(counter == 4) {
                    System.out.println("To much wrong attemps, exiting program");
                    sc.close();
                    System.exit(0);
                }

            }
            if(success) {
                break;
            }
        }
        while (count <= expensesQuant) {
            System.out.println(" -- Enter the name expense " + count + " --");
            nameExpense = sc.next();
            namesArray[index] = nameExpense;
            System.out.println("-- Enter Expense amount --");
            expenses = sc.nextDouble();
            expenseArray[index] = expenses;
            totalAmount += expenses;
            expenseInfo(nameExpense, expenses);
            list.add(expenses);
            listNames.add(nameExpense);
            count++;
            index++;
            cs = new CategorySaving();
            cs.toMuchExpense(expenses,nameExpense);

        }

    }

    public void calculateSavingPercent() {
        double calculate = salary;

        calculateSaving = calculate - totalAmount;
        calculateAmount = calculate / calculateSaving;
        if (calculateSaving >= 10000) {
            level = SAVING_LEVEL_MAX;
        } else {
            level = SAVING_LEVEL_LOW;
        }
    }

// generar try cath por si el usuario ingresa 0, eso haria lanzar una excepcion fatal y cerrar el programa de mala manera si no se maneja
    public void getSalary() {
        int counter = 0;
        boolean success = true;
        System.out.println("Enter your salary amount");
        while (counter < 4) {
            success = true;
            try {
                if (counter < 3) {
                    salary = sc.nextDouble();
                }
            } catch (InputMismatchException e) {
                success = false;
                counter++;
                if (counter == 4) {
                    System.out.println();
                } else {
                    System.out.println("A letter is a invalid input!");
                    System.out.println("Sorry, try again");
                    System.out.println("Enter your salary amount");
                }
                sc.nextLine();

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
            if (salary <= 0) {
            System.out.println("Sorry a 0 or negative number is not allowed...");
            System.exit(0);
            System.out.print(" Salary: $" + salary);
        }
    }

    // hacer que el mes sea ingresado por teclado para mayor fluidez con el usuario en este metodo

    public double saveForMonth() {
        ZoneId z = ZoneId.of( "America/Buenos_Aires" );
        ZonedDateTime zdt = ZonedDateTime.now( z );
        System.out.println("Actual Month: " + zdt.getMonth());
        System.out.println("Until which month would you like to save your money?");
        System.out.println("Please choose a number acording to the month or enter 20 to avoid this calculation");
        System.out.println("1. January 2. Febrary 3. March 4. April 5. May 6. June .7 July 8. Agost 9. September 10. October 11. November 12 . December 22. Ignore step");

        try {
        int monthNumber = sc.nextInt();
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
        }catch (InputMismatchException in) {
            in.printStackTrace();
            System.out.println("Invalid input entered, maybe cause you entered a letter instead a number...");
            System.exit(0);
        }
        if(monthNumber != 22) {
            System.out.println("Saving $" + calculateSaving + " each month for " + monthName + " will reach: $" + perMonth);
        }
        return perMonth;
    }

    public void printList() {
        System.out.println("-------------------------------");
        System.out.println("--Enter the path location to save the ticket expenses");
        String src = null;
        try {
            src = sc.next();
        } catch (InputMismatchException e) {
            System.out.println("ERROR : Invalid path enter");
            System.out.println("You tried to enter a bad input");
        }

        try {
            System.setOut(new PrintStream(new File(src + "\\Ticket.txt")));
        }catch (Exception e) {

        }
        System.out.println("INGRESOS Y GASTOS MENSUALES");
        System.out.println("Salary: " + salary);
        System.out.println();
        System.out.println("--------Expenses List--------");
        System.out.println("ACTUAL SITUATION ->");

        int anotherCount = 0;

        for (int i = 0; i < namesArray.length; i++) {
            System.out.println(namesArray[i] + ": " + "$" + expenseArray[anotherCount]);
            anotherCount++;
            if (namesArray[i] == null) {
                break;
            }
            if (expenseArray[anotherCount] == 0.0) {
                break;
            }
        }
        sc.close();
    }

    public void statisticsExpenses () {
        ZoneId z = ZoneId.of( "America/Buenos_Aires" );
        ZonedDateTime zdt = ZonedDateTime.now( z );
        System.out.println("Actual Date: " + zdt.getDayOfMonth() + " " + zdt.getMonth() + " of " + zdt.getYear());
        System.out.println("Username: " + person.getName());
        System.out.println("--------------------------------------");
        System.out.println("The total mensual expenses: $" + totalAmount);
        System.out.println("Mensual Saving in ARS : $" + calculateSaving);
        System.out.println("Percent Saving over Income in ARS : %" + calculateAmount);
        System.out.println("Saving $" + calculateSaving + " each month for " + monthName + " will reach: $" + perMonth);
        System.out.println("SAVING LEVEL: " + level);
    }

    public void statisticsInConsole() {
        try {
            PrintStream ps_console = System.out;
            System.setOut(ps_console);
            System.out.println("INGRESOS Y GASTOS MENSUALES");
            System.out.println("Salary: " + salary);
            System.out.println();
            System.out.println("--------Expenses List--------");
            System.out.println("ACTUAL SITUATION ->");

            int anotherCount = 0;

            for (int i = 0; i < namesArray.length; i++) {
                System.out.println(namesArray[i] + ": " + "$" + expenseArray[anotherCount]);
                anotherCount++;
                if (namesArray[i] == null) {
                    break;
                }
                if (expenseArray[anotherCount] == 0.0) {
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

