package ControlLayer;

import DBLayer.DBOrder;
import DBLayer.DBProduct;
import ModelLayer.Product;

import java.util.ArrayList;

/**
 * Created by RedJohn on 5/31/2017.
 */
public class OrderController {
    private DBOrder dbOrder;

    private DBProduct dbProduct;

    public int initializeOrder(){
        int orderID = dbOrder.createOrder();
        //ArrayList <Product> productsToOrder = dbProduct.getProductsToOrder();

        // TODO: 5/31/2017 to be finished, db is already functional, only the connections need to be completed 
        return -1;
    }

}
