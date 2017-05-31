package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ControlLayer.EmployeeController;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeRead extends JFrame {


    private JPanel contentPane;
    private JTextField tfName;
    private JTextField tfAddress;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfCity;
    private JTextField tfWorkId;
    private EmployeeController empController;
    private int currentId;
    private JButton btnSearch;
    private JButton btnBack;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EmployeeRead frame = new EmployeeRead();
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
    private EmployeeRead() {
        design();
        actions();

    }

    private void actions() {
        EmployeeController empController = new EmployeeController();

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
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
                    String splitEmployee = empController.read(currentId);
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




    private void design() {



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 845, 494);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblName = new JLabel("Employee Full Name :");
        lblName.setBounds(26, 127, 259, 33);
        contentPane.add(lblName);



        JLabel lblAddress = new JLabel("Employee Address :");
        lblAddress.setBounds(26, 181, 259, 33);
        contentPane.add(lblAddress);


        JLabel lblEmail = new JLabel("Employee Email :");
        lblEmail.setBounds(26, 234, 259, 33);
        contentPane.add(lblEmail);



        JLabel lblPhone = new JLabel("Employee Phone :");
        lblPhone.setBounds(26, 289, 259, 33);
        contentPane.add(lblPhone);



        JLabel lblCity = new JLabel("Employee City :");
        lblCity.setBounds(26, 345, 259, 33);
        contentPane.add(lblCity);



        JLabel lblWorkId = new JLabel("Enter Employee ID :");
        lblWorkId.setFont(new Font("Tahoma", Font.BOLD, 27));
        lblWorkId.setBounds(26, 73, 339, 33);
        contentPane.add(lblWorkId);

        tfName = new JTextField();
        tfName.setBounds(329, 121, 458, 39);
        contentPane.add(tfName);
        tfName.setColumns(10);

        tfAddress = new JTextField();
        tfAddress.setColumns(10);
        tfAddress.setBounds(329, 175, 458, 39);
        contentPane.add(tfAddress);

        tfEmail = new JTextField();
        tfEmail.setColumns(10);
        tfEmail.setBounds(329, 228, 458, 39);
        contentPane.add(tfEmail);

        tfPhone = new JTextField();
        tfPhone.setColumns(10);
        tfPhone.setBounds(329, 283, 458, 39);
        contentPane.add(tfPhone);

        tfCity = new JTextField();
        tfCity.setColumns(10);
        tfCity.setBounds(329, 339, 458, 39);
        contentPane.add(tfCity);

        tfWorkId = new JTextField();
        tfWorkId.setColumns(10);
        tfWorkId.setBounds(329, 70, 280, 39);
        contentPane.add(tfWorkId);

        btnBack = new JButton("Back");
        btnBack.setBounds(26, 17, 171, 41);
        contentPane.add(btnBack);

        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 27));
        btnSearch.setBounds(616, 69, 171, 41);
        contentPane.add(btnSearch);

    }
}
