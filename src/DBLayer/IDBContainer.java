package DBLayer;

import ModelLayer.Container;

import java.sql.SQLException;

/**
 * Created by Admin on 6/3/2017.
 */
public interface IDBContainer {
    Container create(int id, double height, double length, double width) throws SQLException;
    Container read(int id) throws SQLException;
    boolean update(Container container) throws SQLException;
    boolean delete(int id)throws SQLException;
}
