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

    public boolean create(String productId, int currentQuantity,int minQuantity,int maxQuantity,int cvr){

        boolean aux = dbProduct.create(productId,currentQuantity,minQuantity,maxQuantity,cvr);

        return aux;
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

    public boolean update(Product product){

        try {
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
