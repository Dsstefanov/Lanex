package DBLayer;

import ModelLayer.Container;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Luke on 10.05.2017.
 */
public class DBContainer implements IDBContainer{

    public Container create( int id, double height, double length, double width) throws SQLException {

        Container container = new Container(id, height, length, width);
        try {
            
            String sql = "INSERT INTO Container (id, height, length, width) VALUES (?,?,?,?)";
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            PreparedStatement ps =conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setDouble(2, height);
            ps.setDouble(3,length);
            ps.setDouble(4, width);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnection.closeConnection();
        }
        return container;
    }


    public Container read(int id) throws SQLException{
        Container container = null;
        try{
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM container where id=?");
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                container = buildObject(rs);
            }
            
        } finally{
            DBConnection.closeConnection();
        }
        return container;
    }
    private Container buildObject(ResultSet rs) throws SQLException{
        Container container;
        try {
            int containerId = rs.getInt(1);
            double height = rs.getDouble(2);
            double length = rs.getDouble(3);
            double width = rs.getDouble(4);


            container = new Container(containerId, height, width, length);
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return container;
    }

    public Container getRequiredContainer(ArrayList<Double> reqDimensions) throws SQLException {
        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Container " +
                    "WHERE height >= ? AND length >= ? AND width >= ? ;");

            ps.setDouble(1, reqDimensions.get(1));
            ps.setDouble(2, reqDimensions.get(0));
            ps.setDouble(3, reqDimensions.get(2));
            ResultSet rs =  ps.executeQuery();
            if(rs.next()) {
                return buildObject(rs);
            }
            else
            {
                int id = findAvailableID();
                create(id, reqDimensions.get(0), reqDimensions.get(1), reqDimensions.get(2));
                return getRequiredContainer(reqDimensions);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnection.closeConnection();
        }
    }

    private int findAvailableID() throws SQLException {

        try{
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT id FROM Container ORDER BY id DESC ");
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                return rs.getInt("id") + 1;
            }

        } finally{
            DBConnection.closeConnection();
        }
        return 1;
    }


    public boolean update(Container container) throws SQLException{
        try {
            Connection conn = DBConnection.getInstance().getDBcon();
            double height = container.getHeight();
            double width = container.getWidth();
            double length = container.getLength();
            int containerId = container.getContainerId();


            PreparedStatement psttm = conn.prepareStatement("UPDATE Container SET height = ?,length = ?, width = ? WHERE id = ? ");
            psttm.setInt(4,containerId);
            psttm.setDouble(1, height);
            psttm.setDouble(2, length);
            psttm.setDouble(3, width);
            
            psttm.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;

        }
        return true;
    }

    public boolean delete(int id)throws SQLException{
        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            String sql = String.format("Delete from Container where id = %d", id);
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