package DBLayer;

import ModelLayer.Crate;

import java.sql.SQLException;

/**
 * Created by Luke on 21.05.2017.
 */
public interface IDBCrate {

        Crate create(int id, double height, double length, double width) throws SQLException;
//        Crate read(String crateId) throws SQLException;

        boolean delete(int id) throws SQLException;
//        ArrayList<Person> readAll() throws SQLException;
}
