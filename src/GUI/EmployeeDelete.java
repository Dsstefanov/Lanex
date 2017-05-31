package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ControlLayer.EmployeeController;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeDelete extends JFrame {

    private JPanel contentPane;
    private JTextField tfWorkId;
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfAddress;
    private JTextField tfPhone;
    private JTextField tfCity;
    private JButton btnBack;
    private JButton btnSearch;
    private JButton btnDelete;
    private EmployeeController empController;
    private int currentId;
    private int personId;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EmployeeDelete frame = new EmployeeDelete();
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
    public EmployeeDelete() {
        design();
        actions();

    }

    private void design() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 869, 580);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnBack = new JButton("Back");
        btnBack.setBounds(26, 28, 171, 41);
        contentPane.add(btnBack);

        JLabel lblWordId = new JLabel("Enter Employee ID :");
        lblWordId.setBounds(26, 97, 240, 33);
        contentPane.add(lblWordId);

        tfWorkId = new JTextField();
        tfWorkId.setBounds(284, 94, 344, 39);
        contentPane.add(tfWorkId);
        tfWorkId.setColumns(10);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(640, 93, 171, 41);
        contentPane.add(btnSearch);

        JLabel lblName = new JLabel("Employee Name :");
        lblName.setBounds(26, 144, 246, 33);
        contentPane.add(lblName);

        JLabel lblAddress = new JLabel("Employee Address :");
        lblAddress.setBounds(26, 247, 240, 33);
        contentPane.add(lblAddress);

        JLabel lblPhone = new JLabel("Employee Phone :");
        lblPhone.setBounds(26, 296, 240, 33);
        contentPane.add(lblPhone);

        JLabel lblCity = new JLabel("Employee City :");
        lblCity.setBounds(26, 350, 240, 33);
        contentPane.add(lblCity);

        JLabel lblEmail = new JLabel("Employee Email :");
        lblEmail.setBounds(26, 198, 240, 33);
        contentPane.add(lblEmail);

        tfName = new JTextField();
        tfName.setColumns(10);
        tfName.setBounds(284, 141, 344, 39);
        contentPane.add(tfName);

        tfEmail = new JTextField();
        tfEmail.setColumns(10);
        tfEmail.setBounds(284, 195, 344, 39);
        contentPane.add(tfEmail);

        tfAddress = new JTextField();
        tfAddress.setColumns(10);
        tfAddress.setBounds(284, 244, 344, 39);
        contentPane.add(tfAddress);

        tfPhone = new JTextField();
        tfPhone.setColumns(10);
        tfPhone.setBounds(284, 293, 344, 39);
        contentPane.add(tfPhone);

        tfCity = new JTextField();
        tfCity.setColumns(10);
        tfCity.setBounds(284, 347, 344, 39);
        contentPane.add(tfCity);

        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 27));
        btnDelete.setBounds(640, 414, 171, 41);
        contentPane.add(btnDelete);
    }


    private void actions() {
        EmployeeController empController = new EmployeeController();

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

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try{

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

                try{
                    int workIdToDelete = Integer.parseInt(tfWorkId.getText());
                    empController.delete(workIdToDelete);
                    JOptionPane.showMessageDialog(null, "You have successfully Deleted Employee : " + tfName.getText());
                    setFieldsToNull();
                }catch(NullPointerException eee) {
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
}
