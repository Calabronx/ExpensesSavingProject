import interfaces.InicialPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JFrame {


    private JPanel main;
    private JButton continueButton;

    public WelcomePanel() {
        setContentPane(main);
        setTitle("Expenses Program - First step");
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        ImageIcon img = new ImageIcon("C:\\Users\\sebic\\OneDrive\\Escritorio\\Programexp\\ExpensesSavingsProject\\src\\source\\ExpensesProgramLogo.png");
        setIconImage(img.getImage());

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InicialPanel ip = new InicialPanel();
                setVisible(false);
            }
        });
    }
}
