package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    private JButton btnReadAll;
    private JButton btnExitProgram;

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
        btnReadEmployee.setBounds(409, 100, 245, 37);
        contentPane.add(btnReadEmployee);

        btnUpdateEmployee = new JButton("Update Employee");

        btnUpdateEmployee.setBounds(56, 224, 245, 37);
        contentPane.add(btnUpdateEmployee);

        btnDeleteEmployee = new JButton("Delete Employee");

        btnDeleteEmployee.setBounds(409, 224, 245, 37);
        contentPane.add(btnDeleteEmployee);

        btnExitProgram = new JButton("Exit Program");
        btnExitProgram.setBounds(467, 419, 239, 37);
        contentPane.add(btnExitProgram);

        btnBack = new JButton("Back");

        btnBack.setBounds(12, 13, 105, 37);
        contentPane.add(btnBack);

        btnReadAll = new JButton("Read All Employees");

        btnReadAll.setBounds(213, 320, 293, 41);
        contentPane.add(btnReadAll);
    }

    private void createEvents() {

        //Create
        btnCreateEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EmployeeCreate EmployeeCreate = new EmployeeCreate ();
                dispose();
                EmployeeCreate.main(null);
            }
        });

        // Read
        btnReadEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EmployeeRead EmployeeRead = new EmployeeRead ();
                dispose();
                EmployeeRead.main(null);
            }
        });

        //ReadAll
        btnReadAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EmployeeReadAll EmployeeReadAll = new EmployeeReadAll ();
                dispose();
                EmployeeReadAll.main(null);
            }
        });

        //Update
        btnUpdateEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EmployeeUpdate EmployeeUpdate = new EmployeeUpdate ();
                dispose();
                EmployeeUpdate.main(null);
            }
        });

        //Delete
        btnDeleteEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EmployeeDelete EmployeeDelete = new EmployeeDelete ();
                dispose();
                EmployeeDelete.main(null);
            }
        });


        //Back
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
                dispose();
                mainMenu.main(null);
            }
        });

        //EXIT
        btnExitProgram.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?");
                if(confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }

        });

    }
}
