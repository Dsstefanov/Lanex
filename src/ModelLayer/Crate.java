package ModelLayer;


/**
 * Created by Luke on 09.05.2017.
 */
public class Crate {
    private int height;
    private int width;
    private int lenght;
    private String crateId;
    private String productId;



    public Crate(int height, int width, int lenght, String crateId, String productId) {
        this.height = height;
        this.width = width;
        this.lenght = lenght;
        this.crateId = crateId;
        this.productId = productId;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public String getCrateId() {
        return crateId;
    }

    public void setCrateId(String crateId) {
        this.crateId = crateId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
