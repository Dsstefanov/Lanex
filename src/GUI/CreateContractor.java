package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ControlLayer.ContractorController;
import ControlLayer.Controller;
import Exceptions.ValidationException;
import ValidatorLayer.Validator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Font;

public class CreateContractor extends JFrame {

    private JPanel contentPane;
    private JTextField textFirstLastName;
    private JTextField textAddress;
    private JTextField textEmail;
    private JTextField textPhone;
    private JTextField textCity;
    private JTextField textCVR;
    private JButton btnAdd;
    private JButton btnBack;
    private ContractorController controller = new ContractorController();
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateContractor frame = new CreateContractor();
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
    public CreateContractor() {
        initializeComponents();
        createEvents();
    }

    private void createEvents() {
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String username = textFirstLastName.getText();
                String address = textAddress.getText();
                String email = textEmail.getText();
                String phone = textPhone.getText();
                String city = textCity.getText();
                int cvr = 0;

                try {
                    if(tryParseInt(textCVR.getText())) {
                        cvr = Integer.parseInt(textCVR.getText());
                    }
                } catch(NumberFormatException e) {
                    controller.getErrors().add(e.getMessage());
                }

                try {
                    if(controller.create(username, address, email, phone, city, cvr)) {
                        textFirstLastName.setText("");
                        textAddress.setText("");
                        textEmail.setText("");
                        textPhone.setText("");
                        textCity.setText("");
                        textCVR.setText("");
                        JOptionPane.showMessageDialog(null, "Operation has finished with success!");
                    }
                } catch (ValidationException iae) {
                    JOptionPane optionPane = new JOptionPane("You've got the following errors:\n" + iae.getMessage(), JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    controller.removeErrorMessages();
                }
            }
        });
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                ContractorMenu.main(null);
            }
        });


    }

    private boolean tryParseInt(Object object) {
        try {
            Integer.parseInt(object.toString());
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    private void initializeComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 200, 938, 612);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnBack = new JButton("Back <");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnBack.setBounds(12, 13, 116, 40);
        contentPane.add(btnBack);

        textFirstLastName = new JTextField();
        textFirstLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFirstLastName.setBounds(450, 116, 147, 40);
        contentPane.add(textFirstLastName);
        textFirstLastName.setColumns(10);

        textAddress = new JTextField();
        textAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textAddress.setColumns(10);
        textAddress.setBounds(450, 169, 147, 34);
        contentPane.add(textAddress);

        textEmail = new JTextField();
        textEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textEmail.setColumns(10);
        textEmail.setBounds(450, 216, 147, 34);
        contentPane.add(textEmail);

        textPhone = new JTextField();
        textPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textPhone.setColumns(10);
        textPhone.setBounds(450, 265, 147, 40);
        contentPane.add(textPhone);

        textCity = new JTextField();
        textCity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textCity.setColumns(10);
        textCity.setBounds(450, 324, 147, 40);
        contentPane.add(textCity);

        textCVR = new JTextField();
        textCVR.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textCVR.setColumns(10);
        textCVR.setBounds(450, 386, 147, 40);
        contentPane.add(textCVR);

        btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnAdd.setBounds(785, 512, 123, 40);
        contentPane.add(btnAdd);

        JLabel labelFirstLastName = new JLabel("First and Last name");
        labelFirstLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        labelFirstLastName.setBounds(279, 128, 147, 16);
        contentPane.add(labelFirstLastName);

        JLabel labelAddress = new JLabel("Address");
        labelAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        labelAddress.setBounds(279, 178, 116, 16);
        contentPane.add(labelAddress);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        labelEmail.setBounds(279, 225, 116, 16);
        contentPane.add(labelEmail);

        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        labelPhone.setBounds(279, 277, 116, 16);
        contentPane.add(labelPhone);

        JLabel labelCity = new JLabel("City");
        labelCity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        labelCity.setBounds(279, 336, 116, 16);
        contentPane.add(labelCity);

        JLabel labelCVR = new JLabel("CVR");
        labelCVR.setFont(new Font("Tahoma", Font.PLAIN, 16));
        labelCVR.setBounds(279, 398, 116, 16);
        contentPane.add(labelCVR);
    }
}
