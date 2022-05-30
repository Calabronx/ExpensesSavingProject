package interfaces;

import ExpensProcess.ExpensesTable;
import Model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class SavingOnlypn extends JFrame {
    private static ExpensesTable ex;
    private static InicialPanel inicial;
    double totalAmount = 0.0;
    double expenses = 0.0;
    private JTextField savingstxt;
    private JButton Continuebtn;
    private Person person = new Person();
    private JButton addAmountsButton;
    private JButton continueButton;
    private JPanel mainPanel;
    private JButton returnButton;
    private JPanel addExpenses;
    private JTextField textSavings;
    private JTextField textField1;
    private JTextField textSalary;
    private String[] namesArray = new String[100];
    private String[] expenseArray = new String[100];
    private int status = 0;

    public SavingOnlypn(ExpensesTable expenses) {
        this.ex = expenses;
        System.out.println(person.getName());
        setContentPane(mainPanel);
        setTitle("Expenses Saving Proyect");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        ImageIcon img = new ImageIcon("C:\\Users\\sebic\\OneDrive\\Escritorio\\Programexp\\ExpensesSavingsProject\\src\\source\\ExpensesProgramLogo.png");
        setIconImage(img.getImage());
        savingstxt.setText("");
        Continuebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //agregar ventana emergente que pregunte si los savings estan bien, igual que en salary ticket con sus amounts

                String savings = savingstxt.getText();

                try {
                    status = ex.getSavingsOnly(savingstxt);
                    System.out.println("savings: " + person.getSavings());

                    if (savings.equals("")) {
                        status = -1;
                    }

                    System.out.println("savings: " + person.getSavings());
                    System.out.println();

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
            }
        });
        addAmountsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    NumberFormat nf = NumberFormat.getInstance(Locale.US);
                    double savings = nf.parse(savingstxt.getText()).doubleValue();
                    System.out.println(savings);


                    ex.getSavingsOnly(savingstxt);

                    System.out.println("savings: " + person.getSavings());
                    System.out.println();

                } catch (ParseException pe) {
                    System.out.println("error parsing");
                    pe.printStackTrace();
                } catch (NumberFormatException en) {
                    en.printStackTrace();
                    System.out.println("Error al parsear int");
                } catch (NullPointerException en) {
                    en.printStackTrace();
                    System.out.println("null...");
                }
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InicialPanel returnInicial = new InicialPanel();
                setVisible(false);
            }
        });
    }

    public Person getPerson() {
        return person;
    }

    public JTextField getSavingstxt() {
        return savingstxt;
    }

    public void setSavingstxt(JTextField savingstxt) {
        this.savingstxt = savingstxt;
    }

    public JTextField getTextSavings() {
        return textSavings;
    }

    public void setTextSavings(JTextField textSavings) {
        this.textSavings = textSavings;
    }

    public JTextField getTextField4() {
        return textSavings;
    }

    public void setTextField4(JTextField textField4) {
        this.textSavings = textField4;
    }
}
