package ModelLayer;


import java.util.ArrayList;

/**
 * Created by Luke on 09.05.2017.
 */
public class Crate extends Box {

    private int crateId;
    private ArrayList<Product> products = new ArrayList<>();

    public Crate(int crateId, double  height, double  length, double width){
        super( height, length, width);
        this.crateId = crateId;
        products = new ArrayList<>();
    }



    public int getCrateId() {
        return crateId;
    }

    public void setCrateId(int crateId) {
        this.crateId = crateId;
    }

    public String toString(){
        return "Crate id:" +getCrateId() + "Height : " + getHeight() + "Length : " +getLength() + "Width : " +getWidth();
    }

    public ArrayList<Product> getProducts(){
        return  products;}

    public void addProduct(Product product){
        products.add(product);
    }

    public boolean usableCrate(){
        if(getLength() > 0 && getHeight() > 0 && getWidth() > 0) return true;
        return false;
    }
}
