package ControlLayer;

import DBLayer.DBConnection;
import DBLayer.DBNotification;
import ModelLayer.Product;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

/**
 * Created by USER on 29.5.2017 г..
 */
public class NotificationController extends Timer {
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
    DBNotification dbNotification = new DBNotification();


    public void Compare(){
        try{
            Date todayDate = dateFormat.parse(dbNotification.getLastUpdated());
            Date lastupdateDate = dateFormat.parse(dbNotification.getCurrentDate());
            if(todayDate.getTime() - lastupdateDate.getTime() > 1) {
               substractAvarageConsumption();
            }


        }
        catch (ParseException p){
            p.printStackTrace();
        }

        catch (SQLException e){
            e.printStackTrace();
        }

    }
    public boolean substractAvarageConsumption(){
        for (Product product:productController.readAll()) {
            if(product.getCurrentQuantity()<product.getDailyConsumption()){
                //TODO create an exception and throw it
            }else{
                product.setCurrentQuantity(product.getCurrentQuantity()-product.getDailyConsumption());
                boolean success = productController.update(
                        product.getHeight(), product.getLength(), product.getWidth(), product.getBarcode(),
                        product.getMinQuantity(), product.getMaxQuantity(), product.getCurrentQuantity(),
                        product.getDailyConsumption(), product.getName(), product.getCvr()

                );
                dbNotification.insertLastUpdatedDate();
                if(success) {
                    if (product.getCurrentQuantity() <= product.getMinQuantity()) {
                        productsToOrder.add(product);
                    } else if (product.getCurrentQuantity() <= product.getMinQuantity() + product.getMinQuantity() * 0.1) {//*0.1: 10% above the minimum quantity
                        runningOutOfStockSoon.add(product);
                    }
                }else{
                    //TODO throw exception for not updated
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
