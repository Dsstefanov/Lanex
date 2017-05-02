package DBLayer;

import ModelLayer.Container;
import ModelLayer.Employee;
import ModelLayer.Product;
import ModelLayer.Warehouse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by Luke on 29.04.2017.
 */
public class DBContainer {


    public boolean create(double height, double width, double length, double maxCapacity, ArrayList<Product> products) throws SQLException {
        Container container = new Container(height, width, length, maxCapacity, products);
        String sql = String.format("INSERT INTO container (height, width, lenght, maxCapacity) VALUES ('%d', '%d', '%d', '%d')", height, width, length, maxCapacity);

        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            conn.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBConnection.closeConnection();

        return true;
    }

    public Container read(String containerId) throws SQLException{
        Container container = null;
        try{
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            String sql = String.format("SELECT * FROM continer where barcode=%s",containerId);
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()){
                container = buildObject(rs);
            }
        }catch (SQLException e) {
            throw e;
        }finally{
            DBConnection.closeConnection();
        }
        return container;
}
    private Container buildObject(ResultSet rs) throws SQLException{
        Container container;

        try {
            String containerId = rs.getString(1);
            double height = rs.getDouble(2);
            double width = rs.getDouble(3) ;
            double length = rs.getDouble(4);
            double maxCapacity = rs.getDouble(5);

           //TODO: get array list ArrayList<Product> products = container.getArrayList();

            container = new Container(containerId, height, width, length, maxCapacity, products);
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return container;
    }

    public boolean update(Container Container) throws SQLException{
        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            String sql = String.format();
            conn.createStatement().executeUpdate(sql);
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;

        }
        return true;
    }

    public boolean delete(String containerId)throws SQLException{
        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            String sql = String.format("Delete from warehouse where containerId='%s'", containerId);
            conn.createStatement().executeUpdate(sql);
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            DBConnection.closeConnection();
        }
        return true;
    }
}
    }

