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
import java.awt.Font;

public class ContractorMenu extends JFrame {

    private JPanel contentPane;
    private JButton btnCreateContractor;
    private JButton btnReadContractor;
    private JButton btnUpdateContractor;
    private JButton btnBack;
    private JButton btnReadAllInfo;
    private JButton btnExitProgram;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ContractorMenu frame = new ContractorMenu();
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
    public ContractorMenu() {
        initializeComponents();
        createEvents();
    }


    private void initializeComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 200, 938, 612);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnCreateContractor = new JButton("Create Contractor");
        btnCreateContractor.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCreateContractor.setBounds(225, 165, 164, 52);
        contentPane.add(btnCreateContractor);

        btnReadContractor = new JButton("Read Contractor");
        btnReadContractor.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnReadContractor.setBounds(491, 165, 159, 52);
        contentPane.add(btnReadContractor);

        btnUpdateContractor = new JButton("Update Contractor");
        btnUpdateContractor.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnUpdateContractor.setBounds(225, 316, 164, 52);
        contentPane.add(btnUpdateContractor);

        btnExitProgram = new JButton("Exit Program");
        btnExitProgram.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnExitProgram.setBounds(376, 436, 146, 52);
        contentPane.add(btnExitProgram);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnBack.setBounds(12, 13, 109, 36);
        contentPane.add(btnBack);

        btnReadAllInfo = new JButton("Read all info");
        btnReadAllInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnReadAllInfo.setBounds(491, 316, 159, 52);
        contentPane.add(btnReadAllInfo);
    }

    private void createEvents() {
        btnExitProgram.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?");
                if(confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        btnCreateContractor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                CreateContractor.main(null);
            }
        });

        btnUpdateContractor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                UpdateContractor.main(null);
            }
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainMenu.main(null);
            }
        });

        btnReadContractor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                ReadContractor.main(null);
            }
        });

        btnReadAllInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                ReadAllContractors.main(null);
            }
        });
    }

}
