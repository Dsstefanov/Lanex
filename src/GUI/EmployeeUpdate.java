package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ControlLayer.EmployeeController;
import ModelLayer.Employee;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeUpdate extends JFrame {

    private JPanel contentPane;
    private JTextField tfWorkId;
    private JTextField tfName;
    private JTextField tfAddress;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfCity;
    private JButton btnSearch;
    private JButton btnBack;
    private JButton btnUpdate;
    private int currentId;
    private EmployeeController empControl;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EmployeeUpdate frame = new EmployeeUpdate();
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
    private EmployeeUpdate() {
        design();
        actions();
    }

    private void actions(){

        EmployeeController empControl = new EmployeeController();

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                EmployeeMenu.main(null);
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {

                    if (tryParseInt(currentId)) {
                        currentId = Integer.parseInt(tfWorkId.getText());
                    }
                }catch (NumberFormatException ee) {
                    setFieldsToNull();
                    JOptionPane optionPane = new JOptionPane("You've got the following error:\n" + "Work ID field cannot be empty or letters!", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);

                }catch (NullPointerException npe) {
                    setFieldsToNull();
                    JOptionPane optionPane = new JOptionPane("You've got the following error:\n" + "There is no such user!", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }

                try {
                    String splitEmployee = empControl.read(currentId);
                    String[] result = splitEmployee.split(",");
                    if(result.length != 0) {
                        tfName.setText(result[0].substring(5));
                        tfAddress.setText(result[1].substring(8));
                        tfEmail.setText(result[2].substring(6));
                        tfPhone.setText(result[3].substring(6));
                        tfCity.setText(result[4].substring(5));
                    }

                }catch(NullPointerException npe){
                    JOptionPane optionPane = new JOptionPane("You've got the following error:\n" + "There is no such user!!", JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }
            }

        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentName = tfName.getText();
                String currentAddress = tfAddress.getText();
                String currentEmail = tfEmail.getText();
                String currentPhone = tfPhone.getText();
                String currentCity = tfCity.getText();

                try{


                    if(empControl.update(new Employee(currentName,currentAddress,currentEmail,currentPhone,currentCity),currentId));
                    clearFields();
                    JOptionPane.showMessageDialog(null, "Operation finished with success!!");

                }catch(Exception ee) {
                    //empControl.getErrors().add(e.getMessage());
                    JOptionPane optionPane = new JOptionPane("You've got the following error:\n" + ee.getMessage(), JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    //empControl.removeErrorMessages();
                }

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


    private void setFieldsToNull() {
        tfName.setText(null);
        tfAddress.setText(null);
        tfEmail.setText(null);
        tfPhone.setText(null);
        tfCity.setText(null);
        tfWorkId.setText(null);
    }

    private void clearFields () {
        tfName.setText("");
        tfAddress.setText("");
        tfEmail.setText("");
        tfPhone.setText("");
        tfCity.setText("");
        tfWorkId.setText("");
    }

    private void design(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 569);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblWorkId = new JLabel("Type Employee Work ID :");
        lblWorkId.setBounds(26, 80, 305, 33);
        contentPane.add(lblWorkId);

        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 27));
        btnSearch.setBounds(621, 76, 171, 41);
        contentPane.add(btnSearch);

        tfWorkId = new JTextField();
        tfWorkId.setBounds(359, 77, 236, 39);
        contentPane.add(tfWorkId);
        tfWorkId.setColumns(10);

        JLabel lblName = new JLabel("Employee Name :");
        lblName.setBounds(26, 145, 210, 33);
        contentPane.add(lblName);

        JLabel lblAddress = new JLabel("Employee Address :");
        lblAddress.setBounds(26, 200, 236, 33);
        contentPane.add(lblAddress);

        JLabel lblEmail = new JLabel("Employee Email :");
        lblEmail.setBounds(26, 251, 210, 33);
        contentPane.add(lblEmail);

        JLabel lblPhone = new JLabel("Employee Phone :");
        lblPhone.setBounds(19, 306, 217, 33);
        contentPane.add(lblPhone);

        JLabel lblCity = new JLabel("Employee City :");
        lblCity.setBounds(26, 360, 185, 33);
        contentPane.add(lblCity);

        btnBack = new JButton("Back");
        btnBack.setBounds(26, 28, 171, 41);
        contentPane.add(btnBack);

        tfName = new JTextField();
        tfName.setBounds(262, 142, 530, 39);
        contentPane.add(tfName);
        tfName.setColumns(10);

        tfAddress = new JTextField();
        tfAddress.setBounds(262, 197, 530, 39);
        contentPane.add(tfAddress);
        tfAddress.setColumns(10);

        tfEmail = new JTextField();
        tfEmail.setBounds(262, 248, 530, 39);
        contentPane.add(tfEmail);
        tfEmail.setColumns(10);

        tfPhone = new JTextField();
        tfPhone.setBounds(262, 303, 530, 39);
        contentPane.add(tfPhone);
        tfPhone.setColumns(10);

        tfCity = new JTextField();
        tfCity.setBounds(262, 357, 530, 39);
        contentPane.add(tfCity);
        tfCity.setColumns(10);

        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 27));
        btnUpdate.setBounds(621, 412, 171, 41);
        contentPane.add(btnUpdate);

    }
}
