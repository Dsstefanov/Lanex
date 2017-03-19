package ModelLayer;

/**
 * Created by Admin on 3/19/2017.
 */
public abstract class Person {
    private String name, address, email, phone, city;

    public Person(String name, String address, String email, String phone, String city) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.city = city;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
