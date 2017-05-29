package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ControlLayer.ProductController;
import Exceptions.ValidationException;

public class CreateProduct extends JFrame{
    private JPanel contentPane;
    private JTextField textBarcode;
    private JTextField textHeight;
    private JTextField textLength;
    private JTextField textWidth;
    private JTextField textdailyConsumption;
    private JTextField textcurrentQuantity;
    private JTextField textCVR;
    private JTextField textName;
    private JButton btnAdd;
    private JButton btnBack;
    private ProductController controller = new ProductController();

    /**
     * Launch the application.
     */
    //TODO MODIFY ALL THE CLASS!!!!!!!!!!!!
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateProduct frame = new CreateProduct();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CreateProduct() {
        initializeComponents();
        createEvents();
    }

    private void createEvents() {
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String barcode = textBarcode.getText();
                String name = textName.getText();
                double height = 0;
                double lenght = 0;
                double width = 0;
                int currentQuantity = 0;
                int dailyConsumption = 0;
                int cvr = 0;

                /*try {
                    if(tryParseInt(textCVR.getText())) {
                        cvr = Integer.parseInt(textCVR.getText());
                    }
                } catch(NumberFormatException e) {
                    //controller.getErrors().add(e.getMessage());
                }

                try {
                    if(tryParseInt(textcurrentQuantity.getText())) {
                        currentQuantity = Integer.parseInt(textcurrentQuantity.getText());
                    }
                } catch(NumberFormatException e) {
                    //controller.getErrors().add(e.getMessage());
                }

                try {
                    if(tryParseInt(textdailyConsumption.getText())) {
                        dailyConsumption = Integer.parseInt(textdailyConsumption.getText());
                    }
                } catch(NumberFormatException e) {
                    //controller.getErrors().add(e.getMessage());
                }

                try {
                    if(tryParseDouble(textHeight.getText())) {
                        height = Double.parseDouble(textHeight.getText());
                    }
                } catch(NumberFormatException e) {
                    //controller.getErrors().add(e.getMessage());
                }

                try {
                    if(tryParseDouble(textLength.getText())) {
                        lenght = Double.parseDouble(textLength.getText());
                    }
                } catch(NumberFormatException e) {
                    //controller.getErrors().add(e.getMessage());
                }

                try {
                    if(tryParseDouble(textWidth.getText())) {
                        width = Double.parseDouble(textWidth.getText());
                    }
                } catch(NumberFormatException e) {
                    //controller.getErrors().add(e.getMessage());
                }*/

                try {
                    if(controller.create(height,lenght,width,barcode,currentQuantity*37,currentQuantity*74,currentQuantity,dailyConsumption,name,cvr)) {
                        textBarcode.setText("");
                        textHeight.setText("");
                        textLength.setText("");
                        textWidth.setText("");
                        textdailyConsumption.setText("");
                        textcurrentQuantity.setText("");
                        textCVR.setText("");
                        textName.setText("");
                        JOptionPane.showMessageDialog(null, "Operation has finished with success!");
                    }
                } catch (IllegalArgumentException iae) {
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
                ProductMenu.main(null);
            }
        });


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

        textBarcode = new JTextField();
        textBarcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textBarcode.setBounds(450, 100, 147, 40);
        contentPane.add(textBarcode);
        textBarcode.setColumns(10);

        textHeight = new JTextField();
        textHeight.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textHeight.setColumns(10);
        textHeight.setBounds(450, 150, 147, 34);
        contentPane.add(textHeight);

        textLength = new JTextField();
        textLength.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textLength.setColumns(10);
        textLength.setBounds(450, 200, 147, 34);
        contentPane.add(textLength);

        textWidth = new JTextField();
        textWidth.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textWidth.setColumns(10);
        textWidth.setBounds(450, 250, 147, 40);
        contentPane.add(textWidth);

        textdailyConsumption = new JTextField();
        textdailyConsumption.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textdailyConsumption.setColumns(10);
        textdailyConsumption.setBounds(450, 300, 147, 40);
        contentPane.add(textdailyConsumption);

        textcurrentQuantity = new JTextField();
        textcurrentQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textcurrentQuantity.setColumns(10);
        textcurrentQuantity.setBounds(450, 350, 147, 40);
        contentPane.add(textcurrentQuantity);

        textCVR = new JTextField();
        textCVR.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textCVR.setColumns(10);
        textCVR.setBounds(450, 400, 147, 40);
        contentPane.add(textCVR);

        textName = new JTextField();
        textName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textName.setColumns(10);
        textName.setBounds(450, 450, 147, 40);
        contentPane.add(textName);

        btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnAdd.setBounds(785, 500, 123, 40);
        contentPane.add(btnAdd);

        JLabel labelFirstLastName = new JLabel("Barcode");
        labelFirstLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        labelFirstLastName.setBounds(279, 115, 147, 16);
        contentPane.add(labelFirstLastName);

        JLabel labelAddress = new JLabel("Height");
        labelAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        labelAddress.setBounds(279, 165, 116, 16);
        contentPane.add(labelAddress);

        JLabel labelEmail = new JLabel("Length");
        labelEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        labelEmail.setBounds(279, 215, 116, 16);
        contentPane.add(labelEmail);

        JLabel labelPhone = new JLabel("Width");
        labelPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        labelPhone.setBounds(279, 265, 116, 16);
        contentPane.add(labelPhone);

        JLabel labelCity = new JLabel("Daily Consumption");
        labelCity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        labelCity.setBounds(279, 315, 152, 16);
        contentPane.add(labelCity);

        JLabel labelCVR = new JLabel("Current Quantity");
        labelCVR.setFont(new Font("Tahoma", Font.PLAIN, 16));
        labelCVR.setBounds(279, 365, 147, 16);
        contentPane.add(labelCVR);

        JLabel lblContractorsCvr = new JLabel("Contractor's CVR");
        lblContractorsCvr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblContractorsCvr.setBounds(279, 415, 147, 16);
        contentPane.add(lblContractorsCvr);

        JLabel lblNameOfThe = new JLabel("Name of the product");
        lblNameOfThe.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNameOfThe.setBounds(279, 465, 147, 16);
        contentPane.add(lblNameOfThe);
    }
    private boolean tryParseInt(Object object) {
        try {
            Integer.parseInt(object.toString());
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
    private boolean tryParseDouble(Object object) {
        try {
            Double.parseDouble(object.toString());
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
