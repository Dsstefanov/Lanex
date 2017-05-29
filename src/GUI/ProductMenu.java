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
import javax.swing.AbstractAction;
import javax.swing.Action;

public class ProductMenu extends JFrame {

    private JPanel contentPane;
    private JButton btnBack;
    private JButton btnCreateProduct;
    private JButton btnReadProduct;
    private JButton btnDeleteProduct;
    private JButton btnUpdateProduct;
    private JButton btnExitProgram;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProductMenu frame = new ProductMenu();
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
    public ProductMenu() {
        initializeComponents();
        createEvents();
    }

    private void initializeComponents() {
        // TODO Auto-generated method stub
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 478, 405);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnCreateProduct = new JButton("Create Product");
        btnCreateProduct.setBounds(47, 88, 123, 36);
        contentPane.add(btnCreateProduct);

        btnReadProduct = new JButton("Read Product");
        btnReadProduct.setBounds(269, 88, 123, 36);
        contentPane.add(btnReadProduct);

        btnUpdateProduct = new JButton("Update Product");
        btnUpdateProduct.setBounds(47, 175, 123, 36);
        contentPane.add(btnUpdateProduct);

        btnDeleteProduct = new JButton("Delete Product");
        btnDeleteProduct.setBounds(269, 175, 123, 36);
        contentPane.add(btnDeleteProduct);

        btnExitProgram = new JButton("Exit Program");
        btnExitProgram.setBounds(269, 247, 123, 36);
        contentPane.add(btnExitProgram);

        btnBack = new JButton("Back");

        btnBack.setBounds(12, 13, 97, 25);
        contentPane.add(btnBack);

        JButton btnReadAllProducts = new JButton("Read all products");
        btnReadAllProducts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                ReadAllProducts.main(null);
            }
        });
        btnReadAllProducts.setBounds(47, 247, 123, 36);
        contentPane.add(btnReadAllProducts);
    }

    private void createEvents() {
        // TODO Auto-generated method stub
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainMenu.main(null);
            }
        });
        //TODO
        btnCreateProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                CreateProduct.main(null);
            }
        });

        btnReadProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                ReadProduct.main(null);
            }
        });
        btnDeleteProduct.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                DeleteProduct.main(null);
            }
        });
        btnUpdateProduct.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                UpdateProduct.main(null);
            }
        });

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