package DBLayer;
import ModelLayer.Warehouse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by USER on 26.4.2017 г..
 */
public class DBWarehouse implements IDBWarehouse{
    /**
     * @return ModelLayer.Warehouse
     * @throws SQLException: if something goes wrong
     */
    public synchronized Warehouse create(double length, double width, double height) throws SQLException{
        Warehouse warehouse = new Warehouse(length,width,height);
        try{
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Warehouse " +
                    "(length, width, height) VALUES " +
                    "(?,?,?)");
            preparedStatement.setDouble(1,length);
            preparedStatement.setDouble(2,width);
            preparedStatement.setDouble(3,height);
            preparedStatement.executeUpdate();
            String sql2 = "SELECT TOP 1 id FROM warehouse ORDER BY id DESC";
            ResultSet rs = conn.createStatement().executeQuery(sql2);
            if (rs.next()){
                warehouse.setId(rs.getInt(1));
            }else{
                throw new SQLException();
            }
        }finally {
            DBConnection.closeConnection();
        }
        return warehouse;
    }

    /*public ArrayList<Warehouse> readAll() throws SQLException{
        ArrayList<Warehouse> warehouses= new ArrayList<>();
        try{
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            String sql2 = "SELECT * FROM warehouse";
            ResultSet rs = conn.createStatement().executeQuery(sql2);
            while (rs.next()){
                warehouses.add(buildObject(rs));
            }
        }catch (SQLException e) {
            throw e;
        }finally{
            DBConnection.closeConnection();
        }
        return warehouses;
    }*/

    /**
     * @param id warehouse id
     * @return ModelLayer.Warehouse
     * @throws SQLException
     */
    public Warehouse read(int id) throws SQLException{
        Warehouse warehouse = null;
        try{
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            String sql = String.format("SELECT * FROM warehouse where id=%d",id);
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                warehouse = buildObject(rs);
            }
        }catch (SQLException e) {
            throw e;
        }finally{
            DBConnection.closeConnection();
        }
        return warehouse;
    }

    /**
     * Object generator from ResultSet
     * @param rs ResultSet
     * @return ModelLayer.Warehouse
     * @throws SQLException
     */
    private Warehouse buildObject(ResultSet rs) throws SQLException{
        Warehouse warehouse = new Warehouse(rs.getDouble("length"), rs.getDouble("height"), rs.getDouble("width"));
        try {
            warehouse.setId(rs.getInt("id"));
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return warehouse;
    }


    /**
     * @param warehouse ModelLayer.Warehouse
     * @return success:true/failure:SQLException
     * @throws SQLException
     */
    public synchronized boolean update(Warehouse warehouse) throws SQLException{
        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE Warehouse SET length=?, width=?, height=? WHERE id=?");
            preparedStatement.setDouble(1,warehouse.getLength());
            preparedStatement.setDouble(2,warehouse.getWidth());
            preparedStatement.setDouble(3,warehouse.getHeight());
            preparedStatement.setDouble(4,warehouse.getId());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return true;
    }

    /**
     * @param id warehouse id
     * @return success:true/failure:SQLException
     * @throws SQLException
     */
    public boolean delete(int id)throws SQLException{
       try {
           java.sql.Connection conn = DBConnection.getInstance().getDBcon();
           String sql = String.format("Delete from warehouse_product_map where warehouse_id=%d", id);
           conn.createStatement().executeUpdate(sql);
           String sql2 = String.format("Delete from warehouse where id= '%d'",id);
           conn.createStatement().executeUpdate(sql2);
       } catch(SQLException e) {
           e.printStackTrace();
           throw e;
       }finally {
           DBConnection.closeConnection();
       }
        return true;
    }
}
