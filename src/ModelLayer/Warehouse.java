package ModelLayer;

/**
 * Created by USER on 26.4.2017 г..
 */
public class Warehouse {
    int id;
    private double length;
    private double width;
    private double height;

    public Warehouse(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public Warehouse(int id, double length, double width, double height) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
