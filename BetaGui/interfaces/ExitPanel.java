package interfaces;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitPanel extends JFrame {
    private JButton exitButton;
    private JPanel exitpnl;
    private JLabel obigif;

    public ExitPanel() {
        setContentPane(exitpnl);
        setTitle("Expenses Saving Proyect");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        ImageIcon img = new ImageIcon("C:\\Users\\sebic\\OneDrive\\Escritorio\\Programexp\\ExpensesSavingsProject\\src\\source\\ExpensesProgramLogo.png");
        setIconImage(img.getImage());
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });
    }
}
