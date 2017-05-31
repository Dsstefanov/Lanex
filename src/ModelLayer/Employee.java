package ModelLayer;

/**
 * Created by Yeah on 3/20/2017.
 */
public class Employee extends Person {
    private int workId;
    public Employee(String name, String address, String email, String phone, String city) {
        super(name, address, email, phone, city);
    }
    public Employee(int workId, String name, String address, String email, String phone, String city) {
        super(name, address, email, phone, city);
        this.workId = workId;
    }

    public Employee() {
        super(null,null,null,null,null);
    }


    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    @Override
    public String toString() {
        return
        " name" + getName() +
                ", address" + getAddress() +
                ", email" + getEmail() +
                ", phone" + getPhone() +
                ", city" + getCity() +
                ", id" + getWorkId() +
                '}';
    }
}
