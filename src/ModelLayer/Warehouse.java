package ModelLayer;

/**
 * Created by USER on 26.4.2017 г..
 */
public class Warehouse {
    int id;
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
                '}';
    }
}
