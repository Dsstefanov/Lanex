package DBLayer;

import ModelLayer.Container;

import java.sql.*;

/**
 * Created by Luke on 10.05.2017.
 */
public class DBContainer {


    public static void main(String[] args) {

        try {
//           Container container = new Container( "11234567",120.1,130,150);
            new DBContainer().create(1,120.1, 130, 150 );

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("success");
    }
    public Container create( int id, double height, double length, double width) throws SQLException {
        Container container = new Container(id, height, length, width);
        String sql = String.format("INSERT INTO Container (id, height, length, width) VALUES (?,?,?,?)");

        try
                (java.sql.Connection conn = DBConnection.getInstance().getDBcon();
                 PreparedStatement ps =conn.prepareStatement(sql)){
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
        String sql = String.format("SELECT * FROM container where id= %d",id);
        try{
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();

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
    public boolean update(Container container) throws SQLException{
        try {
            Connection conn = DBConnection.getInstance().getDBcon();
            double height = container.getHeight();
            double width = container.getWidth();
            double length = container.getLength();
            int containerId = container.getContainerId();


            PreparedStatement psttm = conn.prepareStatement("UPDATE Container SET height = ?,length = ?, width = ? WHERE id = ? ");
            psttm.setInt(4,containerId);
            psttm.setDouble(1,height);
            psttm.setDouble(2,length);
            psttm.setDouble(3,width);


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