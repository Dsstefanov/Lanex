package ControlLayer;


import DBLayer.DBProduct;
import ModelLayer.Product;
import ValidatorLayer.Validator;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by RedJohn on 4/26/2017.
 */
// TODO MODIFY ALL THE CLASS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class ProductController {
    ArrayList<String> errors = new ArrayList<>();
    DBProduct dbProduct;
    double height, length, width;
    String productID, name;
    int minQuantity, maxQuantity, currentQuantity, dailyConsumption, cvr;

    public ArrayList<String> getErrors(){
        return errors;
    }

    public void removeErrorMessages() {
        this.errors.clear();
    }

    public ProductController(){
        dbProduct = new DBProduct();
    }

    public boolean create(double height, double length, double width, String productID,
                          int minQuantity,int maxQuantity, int currentQuantity,int dailyConsumption,String name ,int cvr){
        checkMultipleErrors(height, length, width, productID, minQuantity, maxQuantity, currentQuantity, dailyConsumption,
                name, cvr);
        Product product = new Product(this.height,this.length,this.width,this.productID,
                this.minQuantity,this.maxQuantity,this.currentQuantity,this.dailyConsumption,this.name,this.cvr);
        if (errors.size() == 0) {
            try {
                dbProduct.create(product);
                return true;
            } catch (SQLException ex) {
                return false;
            }
        } else {
            throw new IllegalArgumentException(String.join("\n", errors));
        }

    }

    public Product read(String productId){
        Product product = null;
        try {
          product = dbProduct.read(productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public boolean update(double height, double length, double width, String productID, int minQuantity,int maxQuantity, int currentQuantity,int dailyConsumption,String name ,int cvr){
        checkMultipleErrors(height, length, width, productID, minQuantity, maxQuantity, currentQuantity, dailyConsumption,
                name, cvr);
        if (errors.size() == 0) {
            try {
                Product product = new Product(height, length, width, productID, minQuantity, maxQuantity, currentQuantity, dailyConsumption, name, cvr);
                dbProduct.update(product);
                return  true;
            } catch (SQLException e) {
                return false;
            }
        } else {
            throw new IllegalArgumentException(String.join("\n", errors));
        }
    }
    private void checkMultipleErrors(double height, double length, double width, String productID, int minQuantity,
                                     int maxQuantity, int currentQuantity, int dailyConsumption, String name, int cvr) {
        try {
            this.height = Validator.validateObjectHeight(height);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.length = Validator.validateObjectLength(length);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.width = Validator.validateObjectWidth(width);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.productID = Validator.validateBarcode(productID);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.minQuantity = Validator.validateMinQ(minQuantity);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.maxQuantity = Validator.validateMaxQ(maxQuantity);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.currentQuantity = Validator.validateQuantities(currentQuantity);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.dailyConsumption = Validator.validateDailyConsumption(dailyConsumption);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.name = Validator.validateProductName(name);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
        try {
            this.cvr = Validator.validateCVR(cvr);
        } catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    public boolean delete(String productId){
        boolean aux = false;
        try {
            aux = dbProduct.delete(productId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aux;
    }

    public ArrayList<Product> readAll() {
        try {
            return dbProduct.readAll();
        } catch (SQLException e) {
            return null;
        }
    }
}
