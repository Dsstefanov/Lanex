package ControlLayer;

import ModelLayer.Product;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import GUI.Notification;

/**
 * Created by USER on 29.5.2017 г..
 */
public class NotificationController {
    private ArrayList<Product> productsToOrder = new ArrayList<>();
    private ProductController productController = new ProductController();
    private String today;
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
    private static NotificationController instance = null;

    public static NotificationController getInstance() {
        if (instance == null) {
            instance = new NotificationController();
        }
        return instance;
    }

    private NotificationController() {
    }

    public ArrayList<Product> getProductsToOrder() {
        return productsToOrder;
    }

    /**
     * productsToOrder: arraylist of all products that needs to be ordered
     */

    public String getCurrentDate() {
        today = dateFormat.format(new Date());
        return today;

    }

    public boolean substractAvarageConsumption() {
        for (Product product : productController.readAll()) {

            int days = 0;
            try {
                DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yy");
                Date productLastUpdatedDate = dateFormat1.parse(product.getLastUpdatedDate());
                days = (int) ((dateFormat.parse(getCurrentDate()).getTime() - productLastUpdatedDate.getTime()) / 86400000);
            } catch (ParseException e) {

            }
            if (product.getCurrentQuantity() < product.getDailyConsumption() && days > 0) {
                product.setCurrentQuantity(0);
                productController.update(product.getBarcode(), product.getCurrentQuantity(), getCurrentDate());
                if (product.getIsOrdered() == 0)
                    productsToOrder.add(product);

            } else {
                if (days > 0) {
                    product.setCurrentQuantity(product.getCurrentQuantity() - product.getDailyConsumption() * days);
                    boolean success = productController.update(product.getBarcode(), product.getCurrentQuantity(), getCurrentDate());
                    if (success) {
                        if ((product.getCurrentQuantity() <= product.getMinQuantity() + 7 * product.getDailyConsumption()) && product.getIsOrdered() == 0) {
                            productsToOrder.add(product);
                        }
                    }
                }
            }
        }
        if (productsToOrder.size() != 0) {
            new AlgorithmController(productsToOrder);
            notifyEmployee();
        }
        return true;
    }

    public boolean notifyEmployee() {
        try {
            Notification.main(null);
            return true;
        } catch (Exception e) {
            //TODO specify what kind of exceptions might be thrown during the process
            return false;//TODO will be changed to throw exception showing what went wrong
        }
    }
}