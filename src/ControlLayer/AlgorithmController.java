package ControlLayer;

import DBLayer.*;
import ModelLayer.Container;
import ModelLayer.Crate;
import ModelLayer.Product;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by RedJohn on 6/2/2017.
 */
public class AlgorithmController {
    private ArrayList <Crate> usedCrates;
    private ArrayList <Container> usedContainers;
    private Crate protoCrate;
    private Container protoContainer;
    private ArrayList<Product> products;
    private int orderID;
    private DBOrder dbOrder;
    private DBOrderLine dbOrderLine;
    private DBCrate dbCrate;
    private DBContainer dbContainer;
    private DBProductCrateMap dbProductCrateMap;
    private DBCrateContainerMap dbCrateContainerMap;
    
    
    public AlgorithmController( ArrayList<Product> products)
    {
        usedCrates = new ArrayList<>();
        usedContainers = new ArrayList<>();
        this.products = products;
        dbOrder = new DBOrder();
        dbOrderLine = new DBOrderLine();
        dbCrate = new DBCrate();
        dbContainer = new DBContainer();
        dbCrateContainerMap = new DBCrateContainerMap();
        dbProductCrateMap = new DBProductCrateMap();
        System.out.println("Main started");
        mainMethod();
    }


    public void mainMethod(){

        setProductsQuantitiesForOrder();

        orderID = dbOrder.createOrder();
        dbOrderLine.createOrderLine(orderID,products);

        ArrayList<Double> reqDimensions1 = getRequiredDimensionsPC();

        try {
            protoCrate = dbCrate.getRequiredCrate(reqDimensions1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Crate dimensions found");
        try {
            ArrayList<Double> reqDimensions2 = new ArrayList<>();
            reqDimensions2.add(protoCrate.getLength());
            reqDimensions2.add(protoCrate.getHeight());
            reqDimensions2.add(protoCrate.getWidth());
            System.out.println("Container dimensions: " + reqDimensions2.get(0)+"  " + reqDimensions2.get(1)+ " " + reqDimensions2.get(2));
            protoContainer = dbContainer.getRequiredContainer(reqDimensions2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Container dimensions found");

        for (Product product: products){
            System.out.println("Product's barcode: " + product.getBarcode());
            product.setCurrentQuantity(maximizeLayers(product));
            createNewCrates(product,protoCrate);
            product.setCurrentQuantity(0);
        }
        System.out.println("Products added into crates");


        ArrayList<Integer> crateNumbers = dbProductCrateMap.create(orderID,usedCrates);
        int numberOfCrates = usedCrates.size();
        createNewContainers(protoCrate, protoContainer,numberOfCrates,crateNumbers);

        dbCrateContainerMap.create(orderID,protoContainer.getContainerId(),usedContainers);
        System.out.println("Crates added into containers");
    }
    /** The method createNewCrates() creates additional crates in the system in order to supply the need of product space for shipping.
     *  The header contains the product than need to be ordered and inserted in crate/crates and crate defines the model of crate that is going to be used.
     *  Crate cratos is the real instance of the crate that is going to be shipped.
     *  Product iffyProduct is the instance of the required products that is going to be inserted in the new created Crate cratos.
     * **/
    private void createNewCrates(Product product, Crate crate) {


        int bestQuantityPerCrate = getBestQuantity(product, crate).get(1);
        int position = getBestQuantity(product, crate).get(0);

        if (product.getCurrentQuantity() >= bestQuantityPerCrate)
            while (product.getCurrentQuantity() >= bestQuantityPerCrate) {
                Product iffyProduct = new Product(product.getBarcode(), product.getLength(), product.getHeight(), product.getWidth(),bestQuantityPerCrate);
                product.setCurrentQuantity(product.getCurrentQuantity() - bestQuantityPerCrate);
                Crate cratos = new Crate(crate.getCrateId(),crate.getLength(), crate.getHeight(), crate.getWidth());
                subtractDimensionsOne(position,iffyProduct,cratos);
                cratos.addProduct(iffyProduct);
                usedCrates.add(cratos);
            }
        if (product.getCurrentQuantity() > 0) {
            Product iffyProduct = new Product(product.getBarcode(), product.getLength(), product.getHeight(), product.getWidth(), product.getCurrentQuantity());
            //iffyProduct.printDetails();
            product.setCurrentQuantity(0);
            Crate cratos = new Crate(crate.getCrateId(),crate.getLength(), crate.getHeight(), crate.getWidth());
            subtractDimensionsTwo(position,iffyProduct,cratos);
            cratos.addProduct(iffyProduct);
            usedCrates.add(cratos);
        }
    }

    private void createNewContainers(Crate crate, Container container, int numberOfCrates, ArrayList<Integer> crateNumbers) {


        int bestQuantityPerContainer = getBestQuantityTwo(crate, container).get(0);
        int aux = 0;
        if (numberOfCrates > bestQuantityPerContainer)
            while (numberOfCrates > bestQuantityPerContainer)
            {
                Container auxContainer = new Container(container.getHeight(),container.getWidth(),container.getLength());
                for(int i = 0; i < bestQuantityPerContainer ; i++)
                {
                    auxContainer.addCrateNumber(crateNumbers.get(aux));
                    aux ++;
                }
                numberOfCrates -= bestQuantityPerContainer;
                usedContainers.add(auxContainer);

            }
        if (numberOfCrates > 0) {
            Container auxContainer = new Container(container.getHeight(),container.getWidth(),container.getLength());
            while (aux < crateNumbers.size()){
            auxContainer.addCrateNumber(crateNumbers.get(aux));
            aux ++;
            }
            usedContainers.add(auxContainer);
        }
    }


    private void setProductsQuantitiesForOrder(){
        for(Product product : products){
            if(product.getCurrentQuantity() >= product.getMinQuantity())
            product.setCurrentQuantity(product.getMaxQuantity() - product.getCurrentQuantity() + product.getMinQuantity());
            else product.setCurrentQuantity(product.getMaxQuantity() - product.getCurrentQuantity());
        }
    }

    public ArrayList<Double> getRequiredDimensionsPC(){
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
                int bestQuantityPerCrate = getBestQuantity(product, auxCrate).get(1);
                int position = getBestQuantity(product, auxCrate).get(0);
                if(bestQuantityPerCrate > 0 && product.getCurrentQuantity() >= bestQuantityPerCrate)
                {
                    Product iffyProduct = new Product(product.getBarcode(), product.getLength(), product.getHeight(), product.getWidth(),bestQuantityPerCrate);
                    product.setCurrentQuantity(product.getCurrentQuantity() - bestQuantityPerCrate);
                    //iffyProduct.printDetails();
                    subtractDimensionsOne(position,iffyProduct,auxCrate);
                    auxCrate.addProduct(iffyProduct);
                }
                else if (product.getCurrentQuantity() < bestQuantityPerCrate && product.getCurrentQuantity() > 0)
                {
                    Product iffyProduct = new Product(product.getBarcode(), product.getLength(), product.getHeight(), product.getWidth(), product.getCurrentQuantity());
                    //iffyProduct.printDetails();
                    product.setCurrentQuantity(0);
                    subtractDimensionsTwo(position,iffyProduct,auxCrate);
                    auxCrate.addProduct(iffyProduct);
                }
            }
        }
        return product.getCurrentQuantity();
    }



    private ArrayList<Integer> getBestQuantity(Product product, Crate crate)
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


    private ArrayList<Integer> getBestQuantityTwo( Crate crate, Container container)
    {
        int sliceWidthWidth = (int)(container.getWidth()/ crate.getWidth());
        int sliceLengthLength = (int)(container.getLength()/ crate.getLength());
        int sliceHeight = (int)(container.getHeight()/ crate.getHeight());
        int quantityPerContainerZero = sliceLengthLength*sliceHeight*sliceWidthWidth;

        int sliceWidthLength = (int)(container.getWidth()/ crate.getLength());
        int sliceLengthHeight = (int)(container.getLength()/ crate.getHeight());
        int sliceHeightWidth = (int)(container.getHeight()/ crate.getWidth());
        int quantityPerContainerNinety = sliceLengthHeight*sliceHeightWidth*sliceWidthLength;

        int sliceWidthHeight = (int)(container.getWidth()/ crate.getHeight());
        int sliceLengthWidth = (int)(container.getLength()/ crate.getWidth());
        int sliceHeightLength = (int)(container.getHeight()/ crate.getLength());
        int quantityPerContainerOneEighty = sliceLengthWidth*sliceHeightLength*sliceWidthHeight;

        ArrayList<Integer> aux = new ArrayList<>();

        if((quantityPerContainerZero >quantityPerContainerNinety)&&(quantityPerContainerZero > quantityPerContainerOneEighty)){
            //quantity for that position
            aux.add(quantityPerContainerZero);
            return aux;
        }

        if ((quantityPerContainerZero <= quantityPerContainerNinety)&&(quantityPerContainerNinety > quantityPerContainerOneEighty))
        {
            //quantity for that position
            aux.add(quantityPerContainerNinety);
            return aux;
        }
        //quantity for that position
        aux.add(quantityPerContainerOneEighty);
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
