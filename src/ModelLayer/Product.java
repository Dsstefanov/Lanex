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
    private int isOrdered;
    private String lastUpdatedDate;


    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Product(String barcode, double height, double length, double width, int minQuantity, int maxQuantity, int currentQuantity, int dailyConsumption, String name , int cvr) {
        super(length, width, height);
        this.barcode = barcode;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.currentQuantity = currentQuantity;
        this.dailyConsumption = dailyConsumption;
        this.name = name;
        this.cvr = cvr;
        isOrdered= 0;

    }

    public Product (String barcode, double length, double height, double width, int currentQuantity){
        super(length, width, height);
        this.barcode = barcode;
        this.currentQuantity = currentQuantity;
    }
    public Product(String barcode, double height, double length, double width,int minQuantity,int maxQuantity, int currentQuantity,int dailyConsumption,String name ,int cvr, String date) {
        super(length, width, height);
        this.barcode = barcode;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.currentQuantity = currentQuantity;
        this.dailyConsumption = dailyConsumption;
        this.name = name;
        this.cvr = cvr;
        isOrdered= 0;
        lastUpdatedDate = date;
    }

    public String getBarcode() {

        return barcode;
    }

    public int setAsOrdered(){
        isOrdered = 1;
        return  isOrdered;

    }

    public int setAsNotOrdered(){
        isOrdered = 0;
        return isOrdered;
    }

    public int getIsOrdered(){
        return isOrdered;
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

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
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
