package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import DBLayer.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.Font;

public class ReadAllContractors extends JFrame {

    private JPanel contentPane;
    private JTable tableResult;
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private JButton btnReadAll;
    private JScrollPane scrollPane;
    private JPanel panel;
    private JButton btnContractorsProducts;
    private JButton btnBack;
    private JPanel panel_1;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ReadAllContractors frame = new ReadAllContractors();
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
    public ReadAllContractors() {
        initializeComponents();
        createEvents();


    }

    private void createEvents() {
        btnReadAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                conn = DBConnection.getInstance().getDBcon();
                fetchAllContractors();
            }
        });

        btnContractorsProducts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                conn = DBConnection.getInstance().getDBcon();
                fetchContractorsAndProducts();
            }
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                ContractorMenu.main(null);
            }
        });
    }

    private void fetchContractorsAndProducts(){
        try{
            String sql = "SELECT Person.name,Person.address,Person.email,Person.city,Person.phone,Contractor.cvr,Product.barcode,Product.currentQuantity " +
                    "FROM ((Person " +
                    "INNER JOIN Contractor ON " +
                    "Person.id = Contractor.person_id)" +
                    "INNER JOIN Product ON " +
                    "Product.cvr = Contractor.cvr)";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            tableResult.setModel(DbUtils.resultSetToTableModel(rs));

        } catch(SQLException ex) {
            JOptionPane optionPane = new JOptionPane("Something went wrong with fetching", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Failure");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        }
    }

    private void fetchAllContractors(){
        try{
            String sql = "SELECT Person.id,Person.name,Person.address,Person.email,Person.city,Person.phone,Contractor.cvr " +
                    "FROM Person " +
                    "INNER JOIN Contractor ON " +
                    "Person.id = Contractor.person_id";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            tableResult.setModel(DbUtils.resultSetToTableModel(rs));

        } catch(SQLException ex) {
            JOptionPane optionPane = new JOptionPane("Something went wrong with fetching", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Failure");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
        }
    }

    private void initializeComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 200, 938, 612);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnReadAll = new JButton("Read all contrators");
        btnReadAll.setFont(new Font("Tahoma", Font.PLAIN, 15));

        btnReadAll.setBounds(753, 494, 155, 37);
        contentPane.add(btnReadAll);

        btnBack = new JButton("Back <");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));

        btnBack.setBounds(12, 13, 105, 37);
        contentPane.add(btnBack);

        panel = new JPanel();
        panel.setBounds(12, 95, 696, 299);
        contentPane.add(panel);
        panel.setLayout(null);

        panel_1 = new JPanel();
        panel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel_1.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(-6, -43, 907, 416);
        panel.add(panel_1);
        panel_1.setLayout(null);

        btnContractorsProducts = new JButton("Read contractors with products");
        btnContractorsProducts.setFont(new Font("Tahoma", Font.PLAIN, 15));

        btnContractorsProducts.setBounds(472, 494, 236, 37);
        contentPane.add(btnContractorsProducts);

        scrollPane = new JScrollPane();
        scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
        scrollPane.setBounds(0, 76, 908, 383);
        contentPane.add(scrollPane);

        tableResult = new JTable();
        scrollPane.setViewportView(tableResult);

    }
}
