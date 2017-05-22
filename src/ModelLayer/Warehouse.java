package ModelLayer;

/**
 * Created by USER on 26.4.2017 г..
 */
public class Warehouse {
    int id;
    private int length;
    private int width;
    private int height;

    public Warehouse(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public Warehouse(int id, int length, int width, int height) {
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


    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
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
