package interfaces;

import ExpensProcess.ExpensesTable;
import Model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class PanelExpenses extends JFrame {

    private static ExpensesTable ex;
    private static InicialPanel inicial;
    int status = 0;
    double totalAmount = 0.0;
    double expenses = 0.0;
    private Person person = new Person();
    private JButton addAmountsButton;
    private JButton continueButton;
    private JPanel mainPanel;
    private JPanel addExpenses;
    private JTextField textSavings;
    private JTextField textField1;
    private JTextField textSalary;
    private String[] namesArray = new String[100];
    private String[] expenseArray = new String[100];
    private BigDecimal decimal;
    private BigDecimal decimalSalary;

    public PanelExpenses(ExpensesTable expenses) {
        this.ex = expenses;
        System.out.println(person.getName());
        setContentPane(mainPanel);
        setTitle("Expenses Saving Proyect");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        ImageIcon img = new ImageIcon("C:\\Users\\sebic\\OneDrive\\Escritorio\\Programexp\\ExpensesSavingsProject\\src\\source\\ExpensesProgramLogo.png");
        setIconImage(img.getImage());
        textSalary.setText("0.0");
        textSavings.setText("0.0");

        addAmountsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InicialPanel returnInicial = new InicialPanel();
                setVisible(false);
            }
        });
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String username = textField1.getText();
                //System.out.println(username);
                String savings = textSavings.getText();
                String salary = textSalary.getText();
                try {

                    status = ex.getSalary(textSalary, textSavings);
                    System.out.println("salary:" + person.getThisSalary());
                    System.out.println("savings: " + person.getSavings());
                    System.out.println();
                    //setTextSalary(upd);
                    if (savings.equals("")) {
                        status = -1;
                    }
                    if (salary.equals("")) {
                        status = -1;
                    }
                } catch (NumberFormatException en) {
                    en.printStackTrace();
                    System.out.println("Error al parsear int");
                } catch (NullPointerException en) {
                    en.printStackTrace();
                    System.out.println("null...");
                }

                if (status == -1) {
                    JOptionPane optionPane = new JOptionPane("Enter crucial fields, please...", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                } else if (status == 1) {
                    addExpensesPanel add = new addExpensesPanel(ex);
                    //test();
                    setVisible(false);
                }

//            } else if(ticket_choose.equals("")) {
//                JOptionPane optionPane = new JOptionPane("Data not entered", JOptionPane.ERROR_MESSAGE);
//                JDialog dialog = optionPane.createDialog("Fields are in blank");
//                dialog.setAlwaysOnTop(true);
//                dialog.setVisible(true);
//                flag = -1;
//            }
//
//                if(flag != -1 | !ticket_choose.equals("")) {
//                flag = 1;
//                System.out.println("user data entered");
//            }
            }
        });
    }

    public static void main(String[] args) {
        System.out.println();
    }

    public JTextField getTextSavings() {
        return textSavings;
    }

    public void setTextSavings(JTextField textSavings) {
        this.textSavings = textSavings;
    }

    public JTextField getTextSalary() {
        return textSalary;
    }

    public void setTextSalary(String text) {
        this.textSalary = textSalary;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public JTextField getTextField3() {
        return textSavings;
    }

    public void setTextField3(JTextField textField3) {
        this.textSavings = textField3;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JTextField getTextField4() {
        return textSalary;
    }

    public void setTextField4(JTextField textField4) {
        this.textSalary = textField4;
    }


}
