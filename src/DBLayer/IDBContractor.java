package DBLayer;

import ModelLayer.Contractor;
import ModelLayer.Person;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Admin on 5/8/2017.
 */
public interface IDBContractor {
    Contractor create(String name, String address, String email, String phone, String city, int cvr) throws SQLException;
    Contractor read(int cvr) throws SQLException;
    boolean update(Contractor contractor, int cvr) throws SQLException;
    boolean delete(int id) throws SQLException;
    ArrayList<Person> readAll() throws SQLException;
}
