package ControlLayer;

import DBLayer.DBCrate;
import DBLayer.DBOrder;
import DBLayer.DBOrderLine;
import ModelLayer.Crate;
import ModelLayer.Product;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by RedJohn on 6/2/2017.
 */
public class AlgorithmController {
    private ArrayList <Crate> usedCrates;
    private Crate protoCrate;
    private ArrayList<Product> products;
    private int orderID;
    private DBOrder dbOrder;
    private DBOrderLine dbOrderLine;
    private DBCrate dbCrate;

    public AlgorithmController( ArrayList<Product> products)
    {
        usedCrates = new ArrayList<>();
        this.products = products;
        dbOrder = new DBOrder();
        dbOrderLine = new DBOrderLine();
        dbCrate = new DBCrate();
        myMethod();
    }


    public void myMethod(){
        orderID = dbOrder.createOrder();
        dbOrderLine.createOrderLine(orderID,products);
        ArrayList<Double> reqDimensions = getRequiredDimensions();
        try {
            protoCrate = dbCrate.getRequiredCrate(reqDimensions);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /** The method createNewCrates() creates additional crates in the system in order to supply the need of product space for shipping.
     *  The header contains the product than need to be ordered and inserted in crate/crates and crate defines the model of crate that is going to be used.
     *  Crate cratos is the real instance of the crate that is going to be shipped.
     *  Product iffyProduct is the instance of the required products that is going to be inserted in the new created Crate cratos.
     * **/
    private void createNewCrates(Product product, Crate crate) {


        int bestQuantityPerCrate = getBestQuantityPerCrate(product, crate).get(1);
        int position = getBestQuantityPerCrate(product, crate).get(0);
        //System.out.println(bestQuantityPerCrate + " and product's quantity is:" + product.getCurrentQuantity()());

        if (product.getCurrentQuantity() >= bestQuantityPerCrate)
            while (product.getCurrentQuantity() >= bestQuantityPerCrate) {
                Product iffyProduct = new Product(product.getName(), product.getLength(), product.getHeight(), product.getWidth(),bestQuantityPerCrate);
                //iffyProduct.printDetails();
                product.setCurrentQuantity(product.getCurrentQuantity() - bestQuantityPerCrate);
                Crate cratos = new Crate(crate.getCrateId(),crate.getLength(), crate.getHeight(), crate.getWidth());
                subtractDimensionsOne(position,iffyProduct,cratos);
                cratos.addProduct(iffyProduct);
                usedCrates.add(cratos);
            }
        if (product.getCurrentQuantity() > 0) {
            System.out.println(product.getName());
            Product iffyProduct = new Product(product.getName(), product.getLength(), product.getHeight(), product.getWidth(), product.getCurrentQuantity());
            //iffyProduct.printDetails();
            product.setCurrentQuantity(0);
            Crate cratos = new Crate(crate.getCrateId(),crate.getLength(), crate.getHeight(), crate.getWidth());
            subtractDimensionsTwo(position,iffyProduct,cratos);
            cratos.addProduct(iffyProduct);
            usedCrates.add(cratos);
        }
    }


    public ArrayList<Double> getRequiredDimensions(){
        double length = 0, width = 0, height = 0;
        for(Product product : products){
            if(product.getLength() > length) length = product.getLength();
            if(product.getHeight() > height) height = product.getHeight();
            if(product.getWidth() > width) width = product.getWidth();
        }
        ArrayList<Double> reqDimensions = new  ArrayList<>();
        reqDimensions.add(length);
        reqDimensions.add(height);
        reqDimensions.add(width);

        return  reqDimensions;
    }

    /** The method maximizeLayers() goes through all the pre-existent created crates and tries to fit newer products from the orderLine.
     *  The header contains the product than need to be ordered and inserted in any preexisting crate/crates.
     *  Crate auxCrate is the real instance of the crate that is going to be shipped.
     *  Product iffyProduct is the instance of the required products that is going to be inserted in the new created Crate cratos.
     * **/
    public int maximizeLayers(Product product)
    {
        for(Crate auxCrate:usedCrates)
        {
            if(auxCrate.usableCrate())
            {
                int bestQuantityPerCrate = getBestQuantityPerCrate(product, auxCrate).get(1);
                int position = getBestQuantityPerCrate(product, auxCrate).get(0);
                if(bestQuantityPerCrate > 0 && product.getCurrentQuantity() >= bestQuantityPerCrate)
                {
                    Product iffyProduct = new Product(product.getName(), product.getLength(), product.getHeight(), product.getWidth(),bestQuantityPerCrate);
                    product.setCurrentQuantity(product.getCurrentQuantity() - bestQuantityPerCrate);
                    //iffyProduct.printDetails();
                    subtractDimensionsOne(position,iffyProduct,auxCrate);
                    auxCrate.addProduct(iffyProduct);
                }
                else if (product.getCurrentQuantity() < bestQuantityPerCrate &&product.getCurrentQuantity() > 0)
                {
                    Product iffyProduct = new Product(product.getName(), product.getLength(), product.getHeight(), product.getWidth(), product.getCurrentQuantity());
                    //iffyProduct.printDetails();
                    product.setCurrentQuantity(0);
                    subtractDimensionsTwo(position,iffyProduct,auxCrate);
                    auxCrate.addProduct(iffyProduct);
                }
            }
        }
        return product.getCurrentQuantity();
    }

    private ArrayList<Integer> getBestQuantityPerCrate(Product product, Crate crate)
    {
        int sliceWidthWidth = (int)(crate.getWidth()/ product.getWidth());
        int sliceLengthLength = (int)(crate.getLength()/ product.getLength());
        int sliceHeight = (int)(crate.getHeight()/ product.getHeight());
        int quantityPerCrateZero = sliceLengthLength*sliceHeight*sliceWidthWidth;

        int sliceWidthLength = (int)(crate.getWidth()/ product.getLength());
        int sliceLengthHeight = (int)(crate.getLength()/ product.getHeight());
        int sliceHeightWidth = (int)(crate.getHeight()/ product.getWidth());
        int quantityPerCrateNinety = sliceLengthHeight*sliceHeightWidth*sliceWidthLength;

        int sliceWidthHeight = (int)(crate.getWidth()/ product.getHeight());
        int sliceLengthWidth = (int)(crate.getLength()/ product.getWidth());
        int sliceHeightLength = (int)(crate.getHeight()/ product.getLength());
        int quantityPerCrateOneEighty = sliceLengthWidth*sliceHeightLength*sliceWidthHeight;

        ArrayList<Integer> aux = new ArrayList<>();

        if((quantityPerCrateZero >quantityPerCrateNinety)&&(quantityPerCrateZero > quantityPerCrateOneEighty)){
            //position 0
            aux.add(1);
            //quantity for that position
            aux.add(quantityPerCrateZero);
            return aux;
        }

        if ((quantityPerCrateZero <= quantityPerCrateNinety)&&(quantityPerCrateNinety > quantityPerCrateOneEighty))
        {
            //position 90
            aux.add(2);
            //quantity for that position
            aux.add(quantityPerCrateNinety);
            return aux;
        }
        //position 180
        aux.add(3);
        //quantity for that position
        aux.add(quantityPerCrateOneEighty);
        return aux;

    }



    private void subtractDimensionsOne(int position, Product product, Crate crate)
    {
        switch(position){
            case 1: crate.subtractDimensions(product.getLength()*(int)(crate.getLength()/ product.getLength()),product.getHeight()*(int)(crate.getHeight()/ product.getHeight()),product.getWidth()*(int)(crate.getWidth()/ product.getWidth()));
                break;
            case 2: crate.subtractDimensions(product.getHeight()*(int)(crate.getLength()/ product.getHeight()),product.getWidth()*(int)(crate.getHeight()/ product.getWidth()),product.getLength()*(int)(crate.getWidth()/ product.getLength()));
                break;
            case 3: crate.subtractDimensions(product.getWidth()*(int)(crate.getLength()/ product.getWidth()),product.getLength()*(int)(crate.getHeight()/ product.getLength()),product.getHeight()*(int)(crate.getWidth()/ product.getHeight()));
                break;
            default: break;
        }
    }

    private void subtractDimensionsTwo(int position, Product product, Crate crate)
    {

        int n, axisLength = 0, axisHeight = 0, axisWidth = 0;

        switch(position){
            case 1:
                /** TO BE MODIFIED FOR BETTER OPTIMISATION, EACH CASE HAS 3 SUB CASES REVOLVED AROUND
                 * THE LEAST AMOUNT OF SPACE USED ON THE LAST ROW, WHICH IS INCOMPLETE IN SOME CASES **/
                axisWidth = (int)(crate.getWidth()/ product.getWidth());
                axisLength = (int)(crate.getLength()/ product.getLength());
                axisHeight = (int)(crate.getHeight()/ product.getHeight());

                if(product.getCurrentQuantity()%(axisLength*axisHeight) == 0) n = product.getCurrentQuantity()/(axisLength*axisHeight);
                else  n = (product.getCurrentQuantity()/(axisLength*axisHeight)) + 1;
                crate.subtractDimensions(0,0,n*product.getWidth());
                break;
            case 2:
                /** TO BE MODIFIED FOR BETTER OPTIMISATION, EACH CASE HAS 3 SUB CASES REVOLVED AROUND
                 * THE LEAST AMOUNT OF SPACE USED ON THE LAST ROW, WHICH IS INCOMPLETE IN SOME CASES **/
                axisWidth = (int)(crate.getWidth()/ product.getLength());
                axisLength = (int)(crate.getLength()/ product.getHeight());
                axisHeight = (int)(crate.getHeight()/ product.getWidth());

                if(product.getCurrentQuantity()%(axisLength*axisHeight) == 0) n = product.getCurrentQuantity()/(axisLength*axisHeight);
                else  n = (product.getCurrentQuantity()/(axisLength*axisHeight)) + 1;
                crate.subtractDimensions(0,n*product.getWidth(),0);
                break;
            case 3:
                /** TO BE MODIFIED FOR BETTER OPTIMISATION, EACH CASE HAS 3 SUB CASES REVOLVED AROUND
                 * THE LEAST AMOUNT OF SPACE USED ON THE LAST ROW, WHICH IS INCOMPLETE IN SOME CASES **/
                axisWidth = (int)(crate.getWidth()/ product.getHeight());
                axisLength = (int)(crate.getLength()/ product.getWidth());
                axisHeight = (int)(crate.getHeight()/ product.getLength());

                if(product.getCurrentQuantity()%(axisLength*axisHeight) == 0) n = product.getCurrentQuantity()/(axisLength*axisHeight);
                else  n = (product.getCurrentQuantity()/(axisLength*axisHeight)) + 1;
                crate.subtractDimensions(n*product.getWidth(),0,0);
                break;
            default: break;
        }

    }
}
