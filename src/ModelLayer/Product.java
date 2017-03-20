package ModelLayer;

/**
 * Created by Luke on 21/03/2017.
 */
public class Product {
    private double productID;
    private int currentQuantity;
    private int minQuantity;
    private int maxQuantity;

    public Product(double productID, int currentQuantity, int minQuantity, int maxQuantity) {
        this.productID = productID;
        this.currentQuantity = currentQuantity;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
    }

    public double getProductID() {

        return productID;
    }

    public void setProductID(double productID) {
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



}
