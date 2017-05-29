package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ControlLayer.ContractorController;
import DBLayer.DBConnection;
import ValidatorLayer.SavedErrors;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;

public class ReadContractor extends JFrame {

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

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReadContractor frame = new ReadContractor();
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
    public ReadContractor() {
        initializeComponents();
        createEvents();
    }

    private void initializeComponents() {
        // TODO Auto-generated method stub
        conControl = new ContractorController();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 200, 938, 612);
        contentPane = new JPanel();
        contentPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        searchFieldCVR = new JTextField();
        searchFieldCVR.setFont(new Font("Tahoma", Font.PLAIN, 16));
        searchFieldCVR.setBounds(12, 103, 137, 35);
        contentPane.add(searchFieldCVR);
        searchFieldCVR.setColumns(10);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnBack.setBounds(12, 13, 116, 35);
        contentPane.add(btnBack);

        JLabel lblSearchByCpr = new JLabel("Search by CVR");
        lblSearchByCpr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSearchByCpr.setBounds(12, 79, 116, 16);
        contentPane.add(lblSearchByCpr);

        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnSearch.setBounds(52, 154, 97, 35);
        contentPane.add(btnSearch);

        JLabel lblResults = new JLabel("Results");
        lblResults.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblResults.setBounds(412, 50, 75, 25);
        contentPane.add(lblResults);

        JLabel lblCVR = new JLabel("ID number");
        lblCVR.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCVR.setBounds(253, 103, 92, 24);
        contentPane.add(lblCVR);

        JLabel lblName = new JLabel("Name ");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblName.setBounds(253, 151, 75, 43);
        contentPane.add(lblName);

        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAddress.setBounds(253, 207, 75, 42);
        contentPane.add(lblAddress);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmail.setBounds(253, 262, 75, 39);
        contentPane.add(lblEmail);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPhone.setBounds(253, 314, 75, 39);
        contentPane.add(lblPhone);

        JLabel lblCity = new JLabel("City");
        lblCity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCity.setBounds(253, 366, 75, 35);
        contentPane.add(lblCity);

        textFieldIdNumber = new JTextField();
        textFieldIdNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldIdNumber.setBounds(369, 100, 189, 38);
        contentPane.add(textFieldIdNumber);
        textFieldIdNumber.setColumns(10);

        textFieldName = new JTextField();
        textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldName.setBounds(369, 151, 189, 38);
        contentPane.add(textFieldName);
        textFieldName.setColumns(10);

        textFieldAddress = new JTextField();
        textFieldAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldAddress.setColumns(10);
        textFieldAddress.setBounds(369, 202, 189, 38);
        contentPane.add(textFieldAddress);

        textFieldEmail = new JTextField();
        textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldEmail.setColumns(10);
        textFieldEmail.setBounds(369, 253, 189, 44);
        contentPane.add(textFieldEmail);

        textFieldPhone = new JTextField();
        textFieldPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldPhone.setColumns(10);
        textFieldPhone.setBounds(369, 310, 189, 39);
        contentPane.add(textFieldPhone);

        textFieldCity = new JTextField();
        textFieldCity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textFieldCity.setColumns(10);
        textFieldCity.setBounds(369, 362, 189, 44);
        contentPane.add(textFieldCity);
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
                        String[] result = splitContractor.split(",");
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
