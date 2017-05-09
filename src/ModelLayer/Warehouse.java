package ModelLayer;

/**
 * Created by USER on 26.4.2017 г..
 */
public class Warehouse {
    int id;
    private float length;
    private float width;
    private float height;

    public Warehouse(float length, float width, float height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public Warehouse(int id, float length, float width, float height) {
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
