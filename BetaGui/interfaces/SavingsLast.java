package interfaces;

import ExpensProcess.ExpensesTable;
import Model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavingsLast extends JFrame {
    private static Person person;
    private static ExpensesTable ex;
    private JButton generateTicketButton;
    private JComboBox comboBox1;
    private JButton confirmButton;
    private JPanel main;
    private JTextField expensesNametxt;
    private JTextField expensesAmountxt;

    public SavingsLast(ExpensesTable exObj) {
        //System.out.println(person.getName());
        this.ex = exObj;
        setContentPane(main);
        setTitle("Expenses Saving Proyect");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        ImageIcon img = new ImageIcon("C:\\Users\\sebic\\OneDrive\\Escritorio\\Programexp\\ExpensesSavingsProject\\src\\source\\ExpensesProgramLogo.png");
        setIconImage(img.getImage());
        generateTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ex.statisticsInConsole();
                ex.printList();
                ExitPanel exitpn = new ExitPanel();
                setVisible(false);
            }
        });
    }
}

