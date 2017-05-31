
package GUI;
import ControlLayer.WarehouseController;
import Exceptions.ValidationException;
import ValidatorLayer.SavedErrors;

import javax.swing.*;

import java.awt.*;

public class DeleteWarehouse extends JFrame {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteWarehouse frame = new DeleteWarehouse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DeleteWarehouse() {
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


		JButton btnSubmit = new JButton("Delete");
		btnSubmit.addActionListener(e -> {
			try{
				int id = Integer.parseInt(textField.getText());
				try{
					WarehouseController warehouseController = new WarehouseController();
					if (warehouseController.read(id)!=null) {
						warehouseController.delete(id);
					}
					errorsArea.setText("Successfully deleted");
				}catch (ValidationException e1){
					errorsArea.setText(e1.getMessage());
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
