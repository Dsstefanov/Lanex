package ModelLayer;

/**
 * Created by Luke on 21/03/2017.
 */
public class Product {
    private String productID;
    private int currentQuantity;
    private int minQuantity;
    private int maxQuantity;
    private int cvr;

    public Product(String productID, int currentQuantity, int minQuantity, int maxQuantity, int cvr) {
        this.productID = productID;
        this.currentQuantity = currentQuantity;
        this.minQuantity = minQuantity;
        this.maxQuantity = maxQuantity;
        this.cvr = cvr;
    }

    public String getProductID() {

        return productID;
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
