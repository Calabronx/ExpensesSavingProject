package interfaces;

import ExpensProcess.ExpensesTable;
import Model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicialPanel extends JFrame {
    private static Person person = new Person();
    private static ExpensesTable ex = new ExpensesTable();
    private JPanel panel1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton cnfButton;
    private JButton continueButton;
    private JButton cancelButton;
    private int flag = -1;


    public InicialPanel() {

        System.out.println(ex);
        setContentPane(panel1);
        setTitle("Expenses Program - First step");
        setSize(300, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        ImageIcon img = new ImageIcon("C:\\Users\\sebic\\OneDrive\\Escritorio\\Programexp\\ExpensesSavingsProject\\src\\source\\ExpensesProgramLogo.png");
        setIconImage(img.getImage());

        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });

        cnfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameUser = textField1.getText();
                person.setName(nameUser);
                System.out.println(person.getName());
                String ticket_choose = String.valueOf(comboBox1.getSelectedItem());
                System.out.println(ticket_choose);

                if (ticket_choose.equals("Salary Ticket")) {
                    //return 1 for expense response
                    ex.setResponse("1");
                    System.out.println("is " + ex.getResponse());
                } else if (ticket_choose.equals("Saving Ticket")) {
                    //return 2 for expense response
                    ex.setResponse("2");
                    System.out.println("is " + ex.getResponse());
                } else if (ticket_choose.equals("")) {
                    JOptionPane optionPane = new JOptionPane("Data not entered", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Fields are in blank");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    flag = -1;
                }

                if (flag != -1 | !ticket_choose.equals("")) {
                    flag = 1;
                    System.out.println("user data entered");
                }
                //PanelExpenses mainPanel = new PanelExpenses();
            }
        });
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(ex);
                if (flag != -1) {
                    if (ex.getResponse().equals("1")) {
                        PanelExpenses panel = new PanelExpenses(ex);
                    } else if (ex.getResponse().equals("2")) {
                        SavingOnlypn pnl = new SavingOnlypn(ex);
                    }
                    setVisible(false);
                } else {
                    JOptionPane optionPane = new JOptionPane("Data not entered", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Confirm data before start");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }
            }
        });
    }

    public static void main(String[] args) {
        InicialPanel ip = new InicialPanel();

    }
}
