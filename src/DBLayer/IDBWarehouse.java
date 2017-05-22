package DBLayer;

import ModelLayer.Warehouse;
import java.sql.SQLException;

/**
 * Created by USER on 8.5.2017 г..
 */
public interface IDBWarehouse {
        public Warehouse create(int length, int width, int height)throws SQLException;
        public Warehouse read(int id) throws SQLException;
        public boolean update(Warehouse warehouse) throws SQLException;
        public boolean delete(int id)throws SQLException;

}
