package ControlLayer;

import ModelLayer.Product;

import java.util.ArrayList;

/**
 * Created by USER on 29.5.2017 г..
 */
public class NotificationController {
    ArrayList<Product> productsToOrder = new ArrayList<>();
    ProductController productController = new ProductController();
    public boolean substractAvarageConsumption(){
        for (Product product:productController.readAll()) {
            if(product.getCurrentQuantity()-product.getDailyConsumption()<0){
                //TODO create an exception and throw it
            }else{
                product.setCurrentQuantity(product.getCurrentQuantity()-product.getDailyConsumption());
                if (product.getCurrentQuantity()<=product.getMinQuantity()){
                    productsToOrder.add(product);
                }
            }
        }
        if (productsToOrder.size()!=0){
            this.notifyEmployee();
        }
        return true;
    }
    public boolean notifyEmployee() {
        try {
            //TODO write code to notify the users
            return true;
        }catch (Exception e){
            //TODO specify what kind of exceptions might be thrown during the process
            return false;//TODO will be changed to throw exception showing what went wrong
        }
    }
}
