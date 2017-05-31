package GUI;
import java.awt.EventQueue;

import ControlLayer.WarehouseController;
import Exceptions.ValidationException;
import ModelLayer.Warehouse;
import ValidatorLayer.SavedErrors;

import javax.swing.*;

public class ReadWarehouse extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReadWarehouse frame = new ReadWarehouse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ReadWarehouse() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 530);

		JTextField textField = new JTextField();
		textField.setBounds(128, 45, 116, 22);
		getContentPane().add(textField);
		textField.setColumns(10);

		JTextArea errorsArea = new JTextArea();
		errorsArea.setColumns(10);
		JScrollPane scrollErrors = new JScrollPane(errorsArea);
		scrollErrors.setBounds(12,280, 300, 150);
		getContentPane().add(scrollErrors);

		JTextField length = new JTextField();
		length.setBounds(340, 43, 116, 22);
		getContentPane().add(length);
		length.setColumns(10);
		length.setEditable(false);

		JTextField width = new JTextField();
		width.setColumns(10);
		width.setBounds(340, 78, 116, 22);
		getContentPane().add(width);
		width.setEditable(false);

		JTextField height = new JTextField();
		height.setColumns(10);
		height.setBounds(340, 113, 116, 22);
		getContentPane().add(height);
		height.setEditable(false);

		JTextField id = new JTextField();
		id.setColumns(10);
		id.setBounds(340, 148, 116, 22);
		getContentPane().add(id);
		id.setEditable(false);

		JLabel lengthLabel = new JLabel("Length");
		lengthLabel.setBounds(272, 46, 56, 16);
		getContentPane().add(lengthLabel);

		JLabel widthLabel = new JLabel("Width");
		widthLabel.setBounds(272, 81, 56, 16);
		getContentPane().add(widthLabel);

		JLabel heightLabel = new JLabel("Height");
		heightLabel.setBounds(272, 116, 56, 16);
		getContentPane().add(heightLabel);

		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(272, 151, 56, 16);
		getContentPane().add(idLabel);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(e -> {
			try {
				int inputId = Integer.parseInt(textField.getText());

				try{
					Warehouse warehouse = new WarehouseController().read(inputId);
					id.setText(String.valueOf(warehouse.getId()));
					length.setText(String.valueOf(warehouse.getLength()));
					width.setText(String.valueOf(warehouse.getWidth()));
					height.setText(String.valueOf(warehouse.getHeight()));
					errorsArea.setText("");
				} catch (ValidationException er) {
					errorsArea.setText(er.getMessage());
				}
			}catch (NumberFormatException e1){
				errorsArea.setText(SavedErrors.getInstance().getErrors().get("NUMBER_EXCEPTION"));
			}
        });
		btnSubmit.setBounds(12, 202, 97, 25);
		getContentPane().add(btnSubmit);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> {
            dispose();
            WarehouseMenu.main(null);
        });
		btnBack.setBounds(121, 202, 97, 25);
		getContentPane().add(btnBack);

		JLabel lblWarehouseId = new JLabel("Warehouse ID");
		lblWarehouseId.setBounds(12, 47, 97, 19);
		getContentPane().add(lblWarehouseId);
	}
}

