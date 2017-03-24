package ModelLayer;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;

/**
 * Created by Red John on 24-Mar-17.
 */
public class Container {
    /**3D Dimensions of the container in meters, digits after comma represent
     * subdivisions of meters(aka: centimeters, millimeters etc)*/
    private double height;
    private double width;
    private double length;
    /**Storage Capacity in kilograms*/
    private double netLoad;
    /**List of the products that the container will contain*/
    //TODO: Change the implementation so that the container contains crates that store the products.
    private ArrayList<Product> products;

    public Container(double height, double width, double length, double maxCapacity, ArrayList<Product> products)
    {
        this.height = height;
        this.width = width;
        this.length = length;
        this.netLoad = maxCapacity;
        this.products = products;
    }

    public Container()
    {
        /** 20' container*/
        height = 2.591;
        width = 2.438;
        length = 6.058;
        netLoad = 28200;
        products = new ArrayList<>();
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
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
}
