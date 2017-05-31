package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import DBLayer.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Font;


public class EmployeeReadAll extends JFrame {


    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement ps = null;
    private JPanel contentPane;
    private JTable tableResult;
    private JPanel panel;
    private JPanel panel_1;
    private JScrollPane scrollPane;
    private JButton btnRead;
    private JButton btnBack;

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

    private void design() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 964, 585);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnBack = new JButton("Back");
        btnBack.setBounds(26, 28, 171, 41);
        contentPane.add(btnBack);

        JPanel panel = new JPanel();
        panel.setBounds(12, 95, 696, 299);
        contentPane.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "JPanel title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(-6, -43, 907, 416);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 76, 908, 383);
        contentPane.add(scrollPane);

        JTable tableResult = new JTable();
        scrollPane.setViewportView(tableResult);

        btnRead = new JButton("READ EMPLOYEE");
        btnRead.setFont(new Font("Tahoma", Font.BOLD, 27));
        btnRead.setBounds(643, 28, 263, 41);
        contentPane.add(btnRead);
    }

    private void actions() {

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                EmployeeMenu.main(null);
            }
        });

        btnRead.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                conn = DBConnection.getInstance().getDBcon();
                fetchAllEmployees();
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
}
