package GUI;

import ControlLayer.NotificationController;
import ControlLayer.ProductController;
import ModelLayer.Product;

import java.util.ArrayList;

/**
 * Created by USER on 2.6.2017 г..
 */
class Notification {
    private NotificationController notificationController = NotificationController.getInstance();
    ProductController productController = new ProductController();
    private ArrayList<Product> products;
    private ArrayList<Product> orderedProducts = new ArrayList<>();

    public Notification() {
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
