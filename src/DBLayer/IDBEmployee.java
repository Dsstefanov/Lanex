package DBLayer;

import ModelLayer.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yeah on 5/8/2017.
 */
public interface IDBEmployee {
    public Employee create(String name, String address, String email, String phone, String city)throws SQLException;
    public Employee read(int id) throws SQLException;
    public Employee update(int id) throws SQLException;
    public boolean delete(int id)throws SQLException;
}
