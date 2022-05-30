package interfaces;

import ExpensProcess.ExpensesTable;
import Model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LastConfigPanel extends JFrame {
    private static Person person;
    private static ExpensesTable ex;
    private JComboBox comboBox1;
    private JButton confirmButton;
    private JPanel mainPanel;
    private JButton generateTicketButton;
    private JTextField expensesNametxt;
    private JTextField expensesAmountxt;

    public LastConfigPanel(ExpensesTable exObj) {
        //System.out.println(person.getName());
        this.ex = exObj;
        setContentPane(mainPanel);
        setTitle("Expenses Saving Proyect");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        ImageIcon img = new ImageIcon("C:\\Users\\sebic\\OneDrive\\Escritorio\\Programexp\\ExpensesSavingsProject\\src\\source\\ExpensesProgramLogo.png");
        setIconImage(img.getImage());
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ticket_choose = String.valueOf(comboBox1.getSelectedItem());
                int monthNumber = 0;
                System.out.println(ticket_choose);


                if (ticket_choose == "JANUARY") {
                    monthNumber = 1;
                    ex.saveForMonth(ticket_choose, monthNumber);
                } else if (ticket_choose == "FEBRUARY") {
                    monthNumber = 2;
                    ex.saveForMonth(ticket_choose, monthNumber);
                } else if (ticket_choose == "MARCH") {
                    monthNumber = 3;
                    ex.saveForMonth(ticket_choose, monthNumber);
                } else if (ticket_choose == "APRIL") {
                    monthNumber = 4;
                    ex.saveForMonth(ticket_choose, monthNumber);
                } else if (ticket_choose == "MAY") {
                    monthNumber = 5;
                    ex.saveForMonth(ticket_choose, monthNumber);
                } else if (ticket_choose == "JUNE") {
                    monthNumber = 6;
                    ex.saveForMonth(ticket_choose, monthNumber);
                } else if (ticket_choose == "JULY") {
                    monthNumber = 7;
                    ex.saveForMonth(ticket_choose, monthNumber);
                } else if (ticket_choose == "AGOST") {
                    monthNumber = 8;
                    ex.saveForMonth(ticket_choose, monthNumber);
                } else if (ticket_choose == "SEPTEMBER") {
                    monthNumber = 9;
                    ex.saveForMonth(ticket_choose, monthNumber);
                } else if (ticket_choose == "OCTOBER") {
                    monthNumber = 10;
                    ex.saveForMonth(ticket_choose, monthNumber);
                } else if (ticket_choose == "NOVEMBER") {
                    monthNumber = 11;
                    ex.saveForMonth(ticket_choose, monthNumber);
                } else if (ticket_choose == "DECEMBER") {
                    monthNumber = 12;
                    ex.saveForMonth(ticket_choose, monthNumber);
                } else {
                    monthNumber = 22;
                }


            }
        });
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
