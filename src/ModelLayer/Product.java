package ModelLayer;

/**
 * Created by Luke on 21/03/2017.
 */
public class Product extends Box{
    private String productID;
    private int currentQuantity;
    private int dailyConsumption;
    private int minQuantity;
    private int maxQuantity;
    private int cvr;
    private String name;

    public Product(double height, double length, double width, String productID, int minQuantity,int maxQuantity, int currentQuantity,int dailyConsumption,String name ,int cvr) {
        super(length, width, height);
        this.productID = productID;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.currentQuantity = currentQuantity;
        this.dailyConsumption = dailyConsumption;
        this.name = name;
        this.cvr = cvr;
    }

    public String getProductID() {

        return productID;
    }

    public int getDailyConsumption(){
        return dailyConsumption;
    }

    public void setDailyConsumption(int dailyConsumption){
        this.dailyConsumption = dailyConsumption;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
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

    public void setCvr(int cvr) {
        this.cvr = cvr;
    }
}
