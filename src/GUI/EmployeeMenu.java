package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeMenu extends JFrame {

    private JPanel contentPane;
    private JButton btnCreateEmployee;
    private JButton btnReadEmployee;
    private JButton btnUpdateEmployee;
    private JButton btnDeleteEmployee;
    private JButton btnBack;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EmployeeMenu frame = new EmployeeMenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public EmployeeMenu() {
        initializeComponents();
        createEvents();
    }


    private void initializeComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 764, 572);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnCreateEmployee = new JButton("Create Employee");
        btnCreateEmployee.setBounds(56, 100, 245, 37);
        contentPane.add(btnCreateEmployee);

        btnReadEmployee = new JButton("Read Employee");
        btnReadEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                EmployeeRead.main(null);
            }
        });

        btnReadEmployee.setBounds(409, 100, 245, 37);
        contentPane.add(btnReadEmployee);

        btnUpdateEmployee = new JButton("Update Employee");
        btnUpdateEmployee.setBounds(56, 224, 245, 37);
        contentPane.add(btnUpdateEmployee);

        btnDeleteEmployee = new JButton("Delete Employee");
        btnDeleteEmployee.setBounds(409, 224, 245, 37);
        contentPane.add(btnDeleteEmployee);

        JButton btnExitProgram = new JButton("Exit Program");
        btnExitProgram.setBounds(227, 341, 239, 37);
        contentPane.add(btnExitProgram);

        btnBack = new JButton("Back");

        btnBack.setBounds(12, 13, 105, 37);
        contentPane.add(btnBack);
    }

    private void createEvents() {
        btnCreateEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EmployeeCreate createEmployee = new EmployeeCreate ();
                dispose();
                createEmployee.main(null);
            }
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
                dispose();
                mainMenu.main(null);
            }
        });

    }

}
