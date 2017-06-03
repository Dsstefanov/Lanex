package ControlLayer;

import ModelLayer.Product;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by USER on 29.5.2017 г..
 */
public class NotificationController  {
    /**
     *  singleton
     */
    private static NotificationController instance = null;
    public static NotificationController getInstance(){
        if (instance==null){
            instance=new NotificationController();
        }
        return instance;
    }
    private NotificationController() {
    }

    int differenceInDays;


    public ArrayList<Product> getProductsToOrder() {
        return productsToOrder;
    }

    /**
     * productsToOrder: arraylist of all products that needs to be ordered
     */
    ArrayList<Product> productsToOrder = new ArrayList<>();
    ArrayList<Product> runningOutOfStockSoon = new ArrayList<>();
    ProductController productController = new ProductController();
    String lastUpdated;
    Date lastUpdatedDate;
    String today;
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
    boolean update;


    public  String getCurrentDate() {
        today = dateFormat.format(new Date());
        return today;

    }
    public boolean substractAvarageConsumption(){
        for (Product product:productController.readAll()) {
            int days = 0;
            try{
                DateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yy");
                Date productLastUpdatedDate = dateFormat1.parse(product.getLastUpdatedDate());
                days = (int) ((dateFormat.parse(getCurrentDate()).getTime()-productLastUpdatedDate.getTime())/86400000);
            }catch (ParseException e){

            }
            if(product.getCurrentQuantity()<product.getDailyConsumption()){
                //TODO create an exception and throw it
            }else {
                if (product.getIsOrdered()==0 && days>0) {
                    product.setCurrentQuantity(product.getCurrentQuantity() - product.getDailyConsumption() *days);
                    boolean success = productController.update(product.getBarcode(), product.getCurrentQuantity(), getCurrentDate());
                    if (success) {
                        if (product.getCurrentQuantity() <= product.getMinQuantity()) {
                            productsToOrder.add(product);
                        } else if (product.getCurrentQuantity() <= product.getMinQuantity() + product.getMinQuantity() * 0.1) {//*0.1: 10% above the minimum quantity
                            runningOutOfStockSoon.add(product);
                        }
                    } else {
                        //TODO throw exception for not updated
                    }
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