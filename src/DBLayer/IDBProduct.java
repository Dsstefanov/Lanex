package DBLayer;

import ModelLayer.Product;

import java.sql.SQLException;

/**
 * Created by RedJohn on 5/15/2017.
 */
public interface IDBProduct {
    Product create(Product product) throws SQLException;
    Product read(String productId) throws SQLException;
    boolean update(Product product) throws SQLException;
    boolean delete(String productId)throws SQLException;
}
