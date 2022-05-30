package interfaces;

import ExpensProcess.ExpensesTable;
import Model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class addExpensesPanel extends JFrame {
    static HashMap<String, String> map = new HashMap<>();
    private static Person person;
    private static ExpensesTable ex;
    private JTextField expensesNametxt;
    private JTextField expensesAmountxt;
    private JButton add;
    private JPanel panelMain;
    private JButton finishButton;
    private JTextField consoleText;


    private JTextField totaltxt;
    private int flag = 2;

    public addExpensesPanel(ExpensesTable exObj) {
        this.ex = exObj;
        setContentPane(panelMain);
        setTitle("Expenses Saving Proyect");
        setSize(900, 900);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        ImageIcon img = new ImageIcon("C:\\Users\\sebic\\OneDrive\\Escritorio\\Programexp\\ExpensesSavingsProject\\src\\source\\ExpensesProgramLogo.png");
        setIconImage(img.getImage());
        totaltxt.setText("$0.0");


        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String expensesName = expensesNametxt.getText();
                String expenseValue = expensesAmountxt.getText();
                String totalSum = "";


                double amount = 0.0;
                double total = 0.0;

                try {
                    amount = Double.parseDouble(expenseValue);

                } catch (NumberFormatException nf) {
                    nf.printStackTrace();

                }

                if (expensesName.equals("")) {
                    JOptionPane optionPane = new JOptionPane("Data not entered", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Please enter the name of the expense!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    flag = -1;
                } else if (amount == 0.0) {
                    JOptionPane optionPane = new JOptionPane("Data not entered", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Please enter the value of the expense!");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    flag = -1;
                } else if (expensesName.equals("") & amount == 0.0) {
                    JOptionPane optionPane = new JOptionPane("Both fields are in blank", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Data not entered");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    flag = -1;
                } else {
                    flag = 1;
                }

                if (flag == 1) {
                    ex.add(expensesNametxt, expensesAmountxt, totaltxt);
                    String nameExp = ex.getNameExpense();
                    String value = String.valueOf(ex.getExpenses());
                    String totalAmt = "$" + String.valueOf(ex.getTotalAmount());
                    addInPanel(nameExp, value);
                    System.out.println("boton :" + expensesName);
                    System.out.println("boton :" + amount);
                    expensesNametxt.setText("");
                    expensesAmountxt.setText("");
                    totaltxt.setText(totalAmt);

                    consoleText.setText(map.toString());

                    System.out.println();
                }

            }
        });
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String status = "0";
                ex.setNameExpense(status);
                System.out.println(ex.getNameExpense());
                if (ex.getNameExpense().equals("0")) {
                    ex.calculateSavingPercent();
                }
                if (flag == 2) {
                    JOptionPane optionPane = new JOptionPane("Expenses not added", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("You have to enter fields to continue");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                } else if (person.isWorkActive) {
                    LastConfigPanel last = new LastConfigPanel(ex);
                    setVisible(false);
                } else {
                    SavingsLast last = new SavingsLast(ex);
                    setVisible(false);
                }
            }
        });
    }

    public static void addInPanel(String nameExp, String value) {

        if (!nameExp.equals("") & !value.equals("")) {
            map.put(nameExp, "$" + value);

            Set keysSet = map.entrySet();

            Collection collection = map.values();

            for (int i = 0; i < map.size(); i++) {
                System.out.println(keysSet + ": " + " $ " + collection + "\n");

            }


            System.out.println("total : " + "\n" + keysSet + "\n");
            System.out.println("totalMap: " + map);


            System.out.println("Innecesario ingresar expensa vacia");
        }
    }

    public static String print(String s1, String s2) {
        String printconsole = "hola " +
                " ";
        String enter = "";
        enter += printconsole;

        System.out.println(enter);

        return enter;
    }

    public static void main(String[] args) {
        print("s", "ss");
        addExpensesPanel add = new addExpensesPanel(ex);
        if (add.add.isSelected()) {
            System.out.println("PRESSED");


        }
    }

    public JTextField getConsoleText() {
        return consoleText;
    }

    public void setConsoleText(JTextField consoleText) {
        this.consoleText = consoleText;
    }
}
