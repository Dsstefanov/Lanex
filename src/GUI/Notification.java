package GUI;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ControlLayer.NotificationController;
import ControlLayer.ProductController;
import ModelLayer.Product;

import java.util.ArrayList;
import java.awt.BorderLayout;

/**
 * Created by USER on 2.6.2017 Đł..
 */
class Notification extends JFrame {
    private NotificationController notificationController = NotificationController.getInstance();
    ProductController productController = new ProductController();
    private ArrayList<Product> products;
    private ArrayList<Product> orderedProducts = new ArrayList<>();
    private JTable table;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Notification frame = new Notification();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }




    public Notification() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Code");
        model.addColumn("Name");
        model.addColumn("Quantity");
        model.addColumn("Unit Price");
        model.addColumn("Price");
        JTable table = new JTable(model);


        products = notificationController.getProductsToOrder();



        //Lukas you can move the foreach loop inside other method but dont move the row above it has to be in the constructor
        for (Product product :products) {


            //TODO show the product info and put a checkbox for the user to be able to click it or a button order with an option order all(button preffered)
            //TODO create the button in the foreach loop so it is unique despite on click it should do the same put the product into ordered products
            //code for btn should contain this code this.orderedProducts.add(product); and remove the button when it is already clicked so the user knows it worked
            //orderAll btn should contain this code this.orderedProducts = products;
            //TODO create btn submit that submits the results
            //TODO btnsubmit should do this productController.update(products);
        }

    }
}
