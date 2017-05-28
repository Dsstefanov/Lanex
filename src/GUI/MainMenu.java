package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {

    private JPanel contentPane;
    private JMenuItem mntmLogout;
    private JButton btWarehouse;
    private JButton btEmployee;
    private JButton btProduct;
    private JButton btContractor;
    private JButton btExit;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainMenu frame = new MainMenu();
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
    public MainMenu() {
        initializeComponents();
        createEvents();
    }

    private void initializeComponents() {
        setTitle("Main Menu");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 579, 447);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        mntmLogout = new JMenuItem("Logout");

        mnFile.add(mntmLogout);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btWarehouse = new JButton("Warehouse Menu");

        btWarehouse.setBounds(310, 79, 158, 36);
        contentPane.add(btWarehouse);

        btEmployee = new JButton("Employee Menu");
        btEmployee.setBounds(79, 79, 158, 36);
        contentPane.add(btEmployee);

        btProduct = new JButton("Product Menu");

        btProduct.setBounds(79, 187, 158, 36);
        contentPane.add(btProduct);

        btContractor = new JButton("Contractor Menu");


        btContractor.setBounds(310, 187, 158, 36);
        contentPane.add(btContractor);

        btExit = new JButton("Exit Program");


        btExit.setBounds(197, 293, 158, 36);
        contentPane.add(btExit);
    }

    private void createEvents() {
        mntmLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout ?");
                if(confirm == JOptionPane.YES_OPTION) {
                    dispose();
                    Login.main(null);
                }
            }
        });

        btWarehouse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
               // WarehouseMenu.main(null); TODO
            }
        });

        btEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
               // EmployeeMenu.main(null); TODO
            }
        });

        btProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
               // ProductMenu.main(null); TODO
            }
        });

        btExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?");
                if(confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        btContractor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                ContractorMenu.main(null);
            }
        });
    }
}
