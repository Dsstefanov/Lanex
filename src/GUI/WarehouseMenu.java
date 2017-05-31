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

public class WarehouseMenu extends JFrame {

	private JPanel contentPane;
	private JButton btnBack;
	private JButton btnCreateWarehouse;
	private JButton btnReadWarehouse;
	private JButton btnDeleteWarehouse;
	private JButton btnUpdateWarehouse;
	private JButton btnExitProgram;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WarehouseMenu frame = new WarehouseMenu();
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
	public WarehouseMenu() {
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
		
		btnCreateWarehouse = new JButton("Create Warehouse");
		btnCreateWarehouse.setBounds(47, 88, 150, 36);
		contentPane.add(btnCreateWarehouse);
		
		btnReadWarehouse = new JButton("Read Warehouse");
		btnReadWarehouse.setBounds(269, 88, 139, 36);
		contentPane.add(btnReadWarehouse);
		
		btnUpdateWarehouse = new JButton("Update Warehouse");
		btnUpdateWarehouse.setBounds(47, 175, 150, 36);
		contentPane.add(btnUpdateWarehouse);
		
		btnDeleteWarehouse = new JButton("Delete Warehouse");
		btnDeleteWarehouse.setBounds(269, 175, 139, 36);
		contentPane.add(btnDeleteWarehouse);
		
		btnExitProgram = new JButton("Exit Program");
		btnExitProgram.setBounds(164, 303, 139, 36);
		contentPane.add(btnExitProgram);
		
		btnBack = new JButton("Back");
		
		btnBack.setBounds(12, 13, 97, 25);
		contentPane.add(btnBack);
	}
	
	private void createEvents() {
		// TODO Auto-generated method stub
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu mainMenu = new MainMenu();
				dispose();
				mainMenu.main(null);
			}
		});
		
		btnCreateWarehouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateWarehouse createWarehouse = new CreateWarehouse();
				dispose();
				createWarehouse.main(null);
			}
		});
		
		btnReadWarehouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReadWarehouse readWarehouse = new ReadWarehouse();
				dispose();
				readWarehouse.main(null);
			}
		});
		btnDeleteWarehouse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DeleteWarehouse deleteWarehouse = new DeleteWarehouse();
				dispose();
				deleteWarehouse.main(null);
			}
		});
		btnUpdateWarehouse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				UpdateWarehouse updateWarehouse = new UpdateWarehouse();
				dispose();
				updateWarehouse.main(null);
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