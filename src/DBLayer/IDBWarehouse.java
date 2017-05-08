package DBLayer;

import ModelLayer.Warehouse;
import java.sql.SQLException;

/**
 * Created by USER on 8.5.2017 г..
 */
public interface IDBWarehouse {
    public interface IDBEmployee {
        public Warehouse create(String name, String address, String email, String phone, String city)throws SQLException;
        public Warehouse read(int id) throws SQLException;
        public boolean update(int id) throws SQLException;
        public boolean delete(int id)throws SQLException;
    }

}
