package ModelLayer;

/**
 * Created by Yeah on 3/20/2017.
 */
public class Contractor extends Person {

    private int cvr;

    public Contractor(String name, String address, String email, String phone, String city, int cvr) {
        super(name, address, email, phone, city);
        this.cvr = cvr;
    }
}
