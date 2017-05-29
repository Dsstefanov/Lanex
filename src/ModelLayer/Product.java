package ModelLayer;

import java.util.ArrayList;

/**
 * Created by Luke on 21/03/2017.
 */
public class Product extends Box{
    private String barcode;
    private int currentQuantity;
    private int dailyConsumption;
    private int minQuantity;
    private int maxQuantity;
    private int cvr;
    private String name;

    public Product(double height, double length, double width, String barcode, int minQuantity,int maxQuantity, int currentQuantity,int dailyConsumption,String name ,int cvr) {
        super(length, width, height);
        this.barcode = barcode;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.currentQuantity = currentQuantity;
        this.dailyConsumption = dailyConsumption;
        this.name = name;
        this.cvr = cvr;
    }

    public String getBarcode() {

        return barcode;
    }

    public int getDailyConsumption(){
        return dailyConsumption;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }


    public int getMinQuantity() {
        return minQuantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getCvr() {
        return cvr;
    }



    public ArrayList<String> allDetails(){
        ArrayList<String>productDetails = new ArrayList<>();
        productDetails.add(barcode);
        productDetails.add(Double.toString(getHeight()));
        productDetails.add(Double.toString(getLength()));
        productDetails.add(Double.toString(getWidth()));
        productDetails.add(Integer.toString(dailyConsumption));
        productDetails.add(Integer.toString(currentQuantity));
        productDetails.add(Integer.toString(cvr));
        productDetails.add(name);

        return productDetails;

    }
}
