package ControlLayer;


import DBLayer.DBProduct;
import ModelLayer.Product;

import java.sql.SQLException;

/**
 * Created by RedJohn on 4/26/2017.
 */
public class ProductController {
    DBProduct dbProduct;
    public ProductController(){
        dbProduct = new DBProduct();
    }

    public boolean create(double height, double length, double width, String productID, int minQuantity,int maxQuantity, int currentQuantity,int dailyConsumption,String name ,int cvr){
        Product product = new Product(height,length,width,productID,minQuantity,maxQuantity,currentQuantity,dailyConsumption,name,cvr);
        dbProduct.create(product);

        return true;
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

        try {
            Product product = new Product(height,length,width,productID,minQuantity,maxQuantity,currentQuantity,dailyConsumption,name,cvr);
            return dbProduct.update(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
}
