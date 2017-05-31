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

public class EmployeeReadAll extends JFrame {

    private JPanel contentPane;
    private JTable tableResult;
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private JButton btnReadAll;
    private JScrollPane scrollPane;
    private JPanel panel;
    private JButton btnBack;
    private JPanel panel_1;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EmployeeReadAll frame = new EmployeeReadAll();
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
    public EmployeeReadAll() {
        design();
        actions();


    }

    private void actions() {
        btnReadAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                conn = DBConnection.getInstance().getDBcon();
                fetchAllEmployees();
            }
        });


        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                EmployeeMenu.main(null);
            }
        });
    }

    private void fetchAllEmployees(){
        try{
            String sql = "SELECT Person.id,Person.name,Person.address,Person.email,Person.city,Person.phone,Employee.work_id " +
                    "FROM Person " +
                    "INNER JOIN Employee ON " +
                    "Person.id = Employee.person_id";
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

    private void design() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 200, 938, 612);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnReadAll = new JButton("Read all Employees");
        btnReadAll.setFont(new Font("Tahoma", Font.BOLD, 27));

        btnReadAll.setBounds(561, 28, 345, 37);
        contentPane.add(btnReadAll);

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 27));

        btnBack.setBounds(12, 13, 140, 37);
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

        scrollPane = new JScrollPane();
        scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
        scrollPane.setBounds(0, 76, 908, 383);
        contentPane.add(scrollPane);

        tableResult = new JTable();
        scrollPane.setViewportView(tableResult);

    }
}