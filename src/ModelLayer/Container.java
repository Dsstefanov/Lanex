package ModelLayer;

import java.util.ArrayList;

/**
 * Created by Red John on 24-Mar-17.
 */
public class Container extends Box{
    /**3D Dimensions of the container in meters, digits after comma represent
     * subdivisions of meters(aka: centimeters, millimeters etc)*/
    private String containerId;
    /**Storage Capacity in kilograms*/
    private double netLoad;
    /**List of the products that the container will contain*/
    //TODO: Change the implementation so that the container contains crates that store the products.
    private ArrayList<Product> products;

    public Container(String containerId, double height, double width, double length, double maxCapacity, ArrayList<Product> products)
    {
        super(height, width, length);
        this.containerId = containerId;
        this.netLoad = maxCapacity;
        this.products = products;
    }

    public Container()
    {
        /**20' container*/
        super(2.591,2.438,6.058);
        netLoad = 28200;
        products = new ArrayList<>();
    }

    public double getNetLoad() {
        return netLoad;
    }

    public void setNetLoad(double netLoad) {
        this.netLoad = netLoad;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }
}
