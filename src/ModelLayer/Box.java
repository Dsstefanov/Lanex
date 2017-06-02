package ModelLayer;

/**
 * Created by Luke on 20.05.2017.
 */
public abstract class Box {
    private double height;
    private double width;
    private double length;

    public Box(double height, double width, double length) {
        this.height = height;
        this.length = length;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void subtractDimensions(double a, double b, double c){
        length = length - a;
        height = height - b;
        width = width - c;
    }
}
