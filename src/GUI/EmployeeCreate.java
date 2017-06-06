package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ControlLayer.EmployeeController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Font;



public class EmployeeCreate extends JFrame {

    private JPanel contentPane;
    private JTextField tfName;
    private JTextField tfAddress;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfCity;
    private JTextField tfWorkId;
    private JButton btnCreate;
    private JButton btnBack;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EmployeeCreate frame = new EmployeeCreate();
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
    public EmployeeCreate()
    {
        design();
        actions();
    }

    public void actions()
    {

        EmployeeController employeeController = new EmployeeController();

        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String currentName = tfName.getText();
                String currentAddress = tfAddress.getText();
                String currentEmail = tfEmail.getText();
                String currentPhone = tfPhone.getText();
                String currentCity = tfCity.getText();
                int currentId = 0;

                try {
                    if (tryParseInt(tfWorkId.getText())) {
                        currentId = Integer.parseInt(tfWorkId.getText());
                    }
                } catch (NumberFormatException e) {
                    e.getMessage();
                    employeeController.getErrors().add(e.getMessage());
                }

                try {
                    employeeController.create(currentName, currentAddress, currentEmail, currentPhone, currentCity, currentId);
                    tfName.setText("");
                    tfAddress.setText("");
                    tfEmail.setText("");
                    tfPhone.setText("");
                    tfCity.setText("");
                    tfWorkId.setText("");
                    JOptionPane.showMessageDialog(null, "You have successfully created an Employee account");


                }catch  (IllegalArgumentException iae) {
                    JOptionPane optionPane = new JOptionPane("You've got the following errors:\n" + iae.getMessage(), JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    employeeController.removeErrorMessages();
                }
            }
        });



        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
                dispose();
                EmployeeMenu.main(null);
            }
        });

    }


    private boolean tryParseInt(Object object)
    {
        try
        {
            Integer.parseInt(object.toString());
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }

    public void design() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 913, 576);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblName = new JLabel("Employee Full Name :");
        lblName.setBounds(26, 87, 259, 33);
        contentPane.add(lblName);

        tfName = new JTextField();
        tfName.setBounds(311, 84, 544, 39);
        contentPane.add(tfName);
        tfName.setColumns(10);

        JLabel lblAddress = new JLabel("Employee Address :");
        lblAddress.setBounds(26, 139, 259, 33);
        contentPane.add(lblAddress);

        tfAddress = new JTextField();
        tfAddress.setColumns(10);
        tfAddress.setBounds(311, 136, 544, 39);
        contentPane.add(tfAddress);

        JLabel lblEmail = new JLabel("Employee Email :");
        lblEmail.setBounds(26, 192, 259, 33);
        contentPane.add(lblEmail);

        tfEmail = new JTextField();
        tfEmail.setColumns(10);
        tfEmail.setBounds(311, 189, 544, 39);
        contentPane.add(tfEmail);

        JLabel lblPhone = new JLabel("Employee Phone :");
        lblPhone.setBounds(26, 247, 259, 33);
        contentPane.add(lblPhone);

        tfPhone = new JTextField();
        tfPhone.setColumns(10);
        tfPhone.setBounds(311, 244, 544, 39);
        contentPane.add(tfPhone);

        JLabel lblCity = new JLabel("Employee City :");
        lblCity.setBounds(26, 303, 259, 33);
        contentPane.add(lblCity);

        tfCity = new JTextField();
        tfCity.setColumns(10);
        tfCity.setBounds(311, 300, 544, 39);
        contentPane.add(tfCity);

        JLabel lblWorkId = new JLabel("Employee Work ID :");
        lblWorkId.setBounds(26, 361, 259, 33);
        contentPane.add(lblWorkId);

        tfWorkId = new JTextField();
        tfWorkId.setColumns(10);
        tfWorkId.setBounds(311, 358, 544, 39);
        contentPane.add(tfWorkId);

        btnCreate = new JButton("CREATE");
        btnCreate.setFont(new Font("Tahoma", Font.BOLD, 27));
        btnCreate.setBounds(617, 425, 238, 55);
        contentPane.add(btnCreate);

        btnBack = new JButton("BACK");
        btnBack.setBounds(26, 16, 238, 55);
        contentPane.add(btnBack);
    }


}
