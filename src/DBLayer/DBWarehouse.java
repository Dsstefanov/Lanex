package DBLayer;
import ModelLayer.Warehouse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by USER on 26.4.2017 г..
 */
public class DBWarehouse implements IDBWarehouse{
    public static void main(String[] args) {
        try {
            new DBWarehouse().create(120, 20, 20);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * @return Warehouse object
     * @throws SQLException: if something goes wrong
     */
    public synchronized Warehouse create(float length, float width, float height) throws SQLException{
        Warehouse warehouse = new Warehouse(length,width,height);
        try{
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Warehouse " +
                    "(length, width, height) VALUES " +
                    "(?,?,?)");
            preparedStatement.setFloat(1,length);
            preparedStatement.setFloat(2,width);
            preparedStatement.setFloat(3,height);
            preparedStatement.executeUpdate();
            String sql2 = "SELECT TOP 1 id FROM warehouse ORDER BY id DESC";
            ResultSet rs = conn.createStatement().executeQuery(sql2);
            if (rs.next()){
                warehouse.setId(rs.getInt(1));
            }else{
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw e;
        }catch (Exception e){
            return null;
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
    private Warehouse buildObject(ResultSet rs) throws SQLException{
        Warehouse warehouse = new Warehouse(rs.getFloat("length"), rs.getFloat("height"), rs.getFloat("width"));
        try {
            warehouse.setId(rs.getInt("id"));
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return warehouse;
    }

    public boolean update(Warehouse warehouse) throws SQLException{
        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            String sql = String.format("UPDATE customer SET something=something WHERE id = '%d'",warehouse.getId());//TODO modify the script when table is updated
            conn.createStatement().executeUpdate(sql);
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return true;
    }
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
