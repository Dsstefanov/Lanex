package GUI;
import java.awt.EventQueue;

import ControlLayer.WarehouseController;
import Exceptions.ValidationException;
import ValidatorLayer.SavedErrors;

import javax.swing.*;

public class CreateWarehouse extends JFrame {
	private WarehouseController warehouseController = new WarehouseController();
	private JTextField length;
	private JTextField width;
	private JTextField height;
	private JTextArea errorsArea;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateWarehouse frame = new CreateWarehouse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CreateWarehouse() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 530);
		length = new JTextField();
		length.setBounds(80, 43, 116, 22);
		getContentPane().add(length);
		length.setColumns(10);

		width = new JTextField();
		width.setColumns(10);
		width.setBounds(80, 78, 116, 22);
		getContentPane().add(width);

		height = new JTextField();
		height.setColumns(10);
		height.setBounds(80, 113, 116, 22);
		getContentPane().add(height);

		errorsArea = new JTextArea();
		errorsArea.setColumns(10);
		JScrollPane scrollErrors = new JScrollPane(errorsArea);
		scrollErrors.setBounds(12, 280, 300, 150);
		getContentPane().add(scrollErrors);

		JLabel lengthLabel = new JLabel("Length");
		lengthLabel.setBounds(12, 46, 56, 16);
		getContentPane().add(lengthLabel);

		JLabel widthLabel = new JLabel("Width");
		widthLabel.setBounds(12, 81, 56, 16);
		getContentPane().add(widthLabel);

		JLabel heightLabel = new JLabel("Height");
		heightLabel.setBounds(12, 116, 56, 16);
		getContentPane().add(heightLabel);

		JButton btnNewButton_2 = new JButton("Add");
		btnNewButton_2.addActionListener(e -> {
			try {
				int l = Integer.parseInt(length.getText());
				int w = Integer.parseInt(width.getText());
				int h = Integer.parseInt(height.getText());
				try {
					new WarehouseController().create(l, w, h);
					errorsArea.setText("Successfully added");
				} catch (ValidationException er) {
					errorsArea.setText(er.getMessage());
				}
			} catch (NumberFormatException e1) {
				errorsArea.setText(SavedErrors.getInstance().getErrors().get("NUMBER_EXCEPTION"));
			}
		});
		btnNewButton_2.setBounds(12, 202, 97, 25);
		getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.addActionListener(e -> {
            dispose();
            WarehouseMenu.main(null);
        });
		btnNewButton_3.setBounds(121, 202, 97, 25);
		getContentPane().add(btnNewButton_3);

	}
}