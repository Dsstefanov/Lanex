package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ControlLayer.ContractorController;
import DBLayer.DBConnection;
import ModelLayer.Contractor;
import ValidatorLayer.SavedErrors;
import ValidatorLayer.Validator;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

public class UpdateContractor extends JFrame {

    private JPanel contentPane;
    private JTextField searchFieldCVR;
    private JButton btnBack;
    private JButton btnSearch;
    private JTextField textFieldIdNumber;
    private JTextField textFieldName;
    private JTextField textFieldAddress;
    private JTextField textFieldEmail;
    private JTextField textFieldPhone;
    private JTextField textFieldCity;
    private ContractorController conControl;
    private int cvr;
    private JButton btnUpdate;
    private String[] result = null;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateContractor frame = new UpdateContractor();
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
    public UpdateContractor() {
        initializeComponents();
        createEvents();
    }

    private void initializeComponents() {
        // TODO Auto-generated method stub
        conControl = new ContractorController();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 200, 938, 612);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        searchFieldCVR = new JTextField();
        searchFieldCVR.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchFieldCVR.setBounds(12, 90, 131, 35);
        contentPane.add(searchFieldCVR);
        searchFieldCVR.setColumns(10);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnBack.setBounds(12, 13, 112, 34);
        contentPane.add(btnBack);

        JLabel lblSearchByCpr = new JLabel("Search by CVR");
        lblSearchByCpr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSearchByCpr.setBounds(23, 60, 120, 22);
        contentPane.add(lblSearchByCpr);

        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnSearch.setBounds(39, 138, 104, 39);
        contentPane.add(btnSearch);

        JLabel lblResults = new JLabel("Results");
        lblResults.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblResults.setBounds(480, 90, 67, 22);
        contentPane.add(lblResults);

        JLabel lblCVR = new JLabel("ID number");
        lblCVR.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCVR.setBounds(323, 147, 112, 16);
        contentPane.add(lblCVR);

        JLabel lblName = new JLabel("Name ");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblName.setBounds(323, 201, 75, 16);
        contentPane.add(lblName);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAddress.setBounds(323, 259, 75, 16);
        contentPane.add(lblAddress);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmail.setBounds(323, 316, 75, 16);
        contentPane.add(lblEmail);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPhone.setBounds(323, 368, 75, 16);
        contentPane.add(lblPhone);

        JLabel lblCity = new JLabel("City");
        lblCity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCity.setBounds(323, 420, 75, 16);
        contentPane.add(lblCity);

        textFieldIdNumber = new JTextField();
        textFieldIdNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldIdNumber.setForeground(Color.BLACK);
        textFieldIdNumber.setBounds(447, 138, 155, 39);
        contentPane.add(textFieldIdNumber);
        textFieldIdNumber.setColumns(10);
        textFieldIdNumber.setEditable(false);
        textFieldIdNumber.setEnabled(true);

        textFieldName = new JTextField();
        textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldName.setBounds(447, 190, 155, 39);
        contentPane.add(textFieldName);
        textFieldName.setColumns(10);

        textFieldAddress = new JTextField();
        textFieldAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldAddress.setColumns(10);
        textFieldAddress.setBounds(447, 245, 155, 45);
        contentPane.add(textFieldAddress);

        textFieldEmail = new JTextField();
        textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldEmail.setColumns(10);
        textFieldEmail.setBounds(447, 305, 155, 39);
        contentPane.add(textFieldEmail);

        textFieldPhone = new JTextField();
        textFieldPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldPhone.setColumns(10);
        textFieldPhone.setBounds(447, 357, 155, 39);
        contentPane.add(textFieldPhone);

        textFieldCity = new JTextField();
        textFieldCity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldCity.setColumns(10);
        textFieldCity.setBounds(447, 409, 155, 39);
        contentPane.add(textFieldCity);

        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnUpdate.setBounds(785, 513, 123, 39);
        contentPane.add(btnUpdate);
    }

    private void createEvents() {
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                ContractorMenu.main(null);
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(tryParseInt(cvr)) {
                        cvr = Integer.parseInt(searchFieldCVR.getText());
                    }

                    try {
                        String splitContractor = conControl.read(cvr);
                        result = splitContractor.split(",");
                        if(result.length != 0) {
                            textFieldName.setText(result[0].substring(5));
                            textFieldAddress.setText(result[1].substring(8));
                            textFieldEmail.setText(result[2].substring(6));
                            textFieldPhone.setText(result[3].substring(6));
                            textFieldCity.setText(result[4].substring(5));
                            textFieldIdNumber.setText(result[5].substring(3));
                        }
                    } catch(NullPointerException npe) {
                        JOptionPane optionPane = new JOptionPane("You've got the following error:\n" + SavedErrors.getInstance().getErrors().get("WRONG_CONTRACTOR"), JOptionPane.ERROR_MESSAGE);
                        JDialog dialog = optionPane.createDialog("Failure");
                        dialog.setAlwaysOnTop(true);
                        dialog.setVisible(true);
                    }
                } catch (NumberFormatException ee) {
                    setFieldsToNull();
                    JOptionPane optionPane = new JOptionPane("You've got the following error:\n" + SavedErrors.getInstance().getErrors().get("WRONG_CVR"), JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }



            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String username = textFieldName.getText();
                String address = textFieldAddress.getText();
                String email = textFieldEmail.getText();
                String phone = textFieldPhone.getText();
                String city = textFieldCity.getText();


                try {
                    if(conControl.update(new Contractor(username, address, email, phone, city), cvr)) {
                        clearFields();
                        JOptionPane.showMessageDialog(null, "Operation finished with success!!");
                    }
                } catch(IllegalArgumentException e) {
                    //conControl.getErrors().add(e.getMessage());
                    JOptionPane optionPane = new JOptionPane("You've got the following error:\n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                    conControl.removeErrorMessages();
                }
            }

            private void clearFields() {
                textFieldName.setText("");
                textFieldAddress.setText("");
                textFieldEmail.setText("");
                textFieldPhone.setText("");
                textFieldCity.setText("");
                textFieldIdNumber.setText("");
            }
        });
    }
    private void setFieldsToNull() {
        textFieldName.setText(null);
        textFieldAddress.setText(null);
        textFieldEmail.setText(null);
        textFieldPhone.setText(null);
        textFieldCity.setText(null);
        textFieldIdNumber.setText(null);
    }

    private boolean tryParseInt(Object object) {
        try {
            Integer.parseInt(object.toString());
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
