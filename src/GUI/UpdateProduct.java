package GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ControlLayer.ProductController;
import ModelLayer.Contractor;
import ValidatorLayer.SavedErrors;
import ValidatorLayer.Validator;

public class UpdateProduct extends JFrame{
    private JPanel contentPane;
    private JTextField textBarcode1;
    private JTextField textHeight;
    private JTextField textLength;
    private JTextField textWidth;
    private JTextField textdailyConsumption;
    private JTextField textcurrentQuantity;
    private JTextField textCVR;
    private JTextField textName;
    private JButton btnBack;
    private JButton btnSearch;
    private String barcode;
    private ProductController controller = new ProductController();
    private JLabel lblCvr;
    private JButton btnUpdate;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateProduct frame = new UpdateProduct();
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
    public UpdateProduct() {
        initializeComponents();
        createEvents();
    }

    private void initializeComponents() {
        // TODO Auto-generated method stub
        controller = new ProductController();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 200, 938, 612);
        contentPane = new JPanel();
        contentPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textBarcode1 = new JTextField();
        textBarcode1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textBarcode1.setBounds(12, 103, 137, 35);
        contentPane.add(textBarcode1);
        textBarcode1.setColumns(10);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnBack.setBounds(12, 13, 116, 35);
        contentPane.add(btnBack);

        JLabel lblSearchByBarcode = new JLabel("Search by barcode");
        lblSearchByBarcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblSearchByBarcode.setBounds(12, 79, 137, 16);
        contentPane.add(lblSearchByBarcode);

        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnSearch.setBounds(52, 154, 97, 35);
        contentPane.add(btnSearch);

        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnUpdate.setBounds(539, 524, 97, 35);
        contentPane.add(btnUpdate);

        JLabel lblResults = new JLabel("Results");
        lblResults.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblResults.setBounds(412, 50, 75, 25);
        contentPane.add(lblResults);

        JLabel lblHeight = new JLabel("Height");
        lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblHeight.setBounds(253, 151, 75, 30);
        contentPane.add(lblHeight);

        JLabel lblLength = new JLabel("Length");
        lblLength.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblLength.setBounds(253, 207, 75, 30);
        contentPane.add(lblLength);

        JLabel lblWidth = new JLabel("Width");
        lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblWidth.setBounds(253, 262, 75, 30);
        contentPane.add(lblWidth);

        JLabel lbldailyConsumption = new JLabel("Daily Consumption");
        lbldailyConsumption.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbldailyConsumption.setBounds(216, 314, 143, 30);
        contentPane.add(lbldailyConsumption);

        JLabel lblcurrentQuantity = new JLabel("Current Quantity");
        lblcurrentQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblcurrentQuantity.setBounds(216, 366, 143, 30);
        contentPane.add(lblcurrentQuantity);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblName.setBounds(253, 419, 75, 30);
        contentPane.add(lblName);

        textHeight = new JTextField();
        textHeight.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textHeight.setBounds(369, 151, 189, 44);
        contentPane.add(textHeight);
        textHeight.setColumns(10);

        textLength = new JTextField();
        textLength.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textLength.setColumns(10);
        textLength.setBounds(369, 202, 189, 44);
        contentPane.add(textLength);

        textWidth = new JTextField();
        textWidth.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textWidth.setColumns(10);
        textWidth.setBounds(369, 253, 189, 44);
        contentPane.add(textWidth);

        textdailyConsumption = new JTextField();
        textdailyConsumption.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textdailyConsumption.setColumns(10);
        textdailyConsumption.setBounds(369, 310, 189, 44);
        contentPane.add(textdailyConsumption);

        textcurrentQuantity = new JTextField();
        textcurrentQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textcurrentQuantity.setColumns(10);
        textcurrentQuantity.setBounds(369, 362, 189, 44);
        contentPane.add(textcurrentQuantity);

        textName = new JTextField();
        textName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textName.setColumns(10);
        textName.setBounds(369, 414, 189, 44);
        contentPane.add(textName);

        textCVR = new JTextField();
        textCVR.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textCVR.setBounds(369, 469, 189, 44);
        contentPane.add(textCVR);

        lblCvr = new JLabel("CVR");
        lblCvr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCvr.setBounds(253, 478, 75, 30);
        contentPane.add(lblCvr);

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

    private void createEvents() {
        // TODO Auto-generated method stub
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                ProductMenu.main(null);
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                double height = Double.parseDouble(textHeight.getText());
                double length = Double.parseDouble(textLength.getText());
                double width = Double.parseDouble(textWidth.getText());
                int dailyConsumption = Integer.parseInt(textdailyConsumption.getText());
                int currentQuantity = Integer.parseInt(textcurrentQuantity.getText());
                String name = textName.getText();
                int cvr = Integer.parseInt(textCVR.getText());


                try {
                    controller.update(height,length,width,barcode,37*dailyConsumption,74*dailyConsumption,currentQuantity,dailyConsumption,name,cvr);
                    clearFields();
                    JOptionPane.showMessageDialog(null, "Operation finished with success!!");

                } catch(Exception e) {
                    JOptionPane optionPane = new JOptionPane("You've got the following error:\n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }
            }

            private void clearFields() {
                textBarcode1.setText("");
                textHeight.setText("");
                textLength.setText("");
                textWidth.setText("");
                textdailyConsumption.setText("");
                textcurrentQuantity.setText("");
                textCVR.setText("");
                textName.setText("");
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    barcode = textBarcode1.getText();
                    Validator.validateBarcode(barcode);

                    try {
                        ArrayList<String> productDetails = controller.read(barcode).allDetails();

                        if(productDetails.size() != 0) {

                            textHeight.setText(productDetails.get(1));
                            textLength.setText(productDetails.get(2));
                            textWidth.setText(productDetails.get(3));
                            textdailyConsumption.setText(productDetails.get(4));
                            textcurrentQuantity.setText(productDetails.get(5));
                            textName.setText(productDetails.get(7));
                            textCVR.setText(productDetails.get(6));
                        }
                    } catch(NullPointerException npe) {
                        JOptionPane optionPane = new JOptionPane("You've got the following error:\n" + SavedErrors.getInstance().getErrors().get("EMPTY_PRODUCT"), JOptionPane.ERROR_MESSAGE);
                        JDialog dialog = optionPane.createDialog("Failure");
                        dialog.setAlwaysOnTop(true);
                        dialog.setVisible(true);
                    }
                }
                catch (Exception ee) {
                    setFieldsToNull();
                    JOptionPane optionPane = new JOptionPane("You've got the following error:\n" + ee.getMessage(), JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog("Failure");
                    dialog.setAlwaysOnTop(true);
                    dialog.setVisible(true);
                }



            }
        });
    }
    private void setFieldsToNull() {
        textBarcode1.setText("");
        textHeight.setText("");
        textLength.setText("");
        textWidth.setText("");
        textdailyConsumption.setText("");
        textcurrentQuantity.setText("");
        textCVR.setText("");
        textName.setText("");
    }
}


