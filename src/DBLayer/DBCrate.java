package DBLayer;

import ModelLayer.Crate;

import java.sql.*;

/**
 * Created by Luke on 10.05.2017.
 */
public class DBCrate implements IDBCrate {


    public static void main(String[] args) {

       try {
//           Crate crate = new Crate( "11234567",120.1,130,150);
          new DBCrate().create(1,120.1, 130, 150 );

        } catch (SQLException e) {
           e.printStackTrace();
       }
       System.out.println("success");
    }
        public Crate create( int id, double height, double length, double width) throws SQLException {
            Crate crate = new Crate(id, height, length, width);
            String sql = String.format("INSERT INTO Crate (id, height, length, width) VALUES (?,?,?,?)");

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
            return crate;
        }


        public Crate read(int id) throws SQLException{
            Crate crate = null;
            String sql = String.format("SELECT * FROM crate where id= %d",id);
            try{
                java.sql.Connection conn = DBConnection.getInstance().getDBcon();

                ResultSet rs = conn.createStatement().executeQuery(sql);
                if (rs.next()){
                    crate = buildObject(rs);
                }


            }catch (SQLException e) {
                throw e;
            }finally{
                DBConnection.closeConnection();
            }
            return crate;
        }
        private Crate buildObject(ResultSet rs) throws SQLException{
            Crate crate;
            try {
                int crateId = rs.getInt(1);
                double height = rs.getDouble(2);
                double length = rs.getDouble(3);
                double width = rs.getDouble(4);


                crate = new Crate(crateId, height, width, length);
            } catch(SQLException e) {
                e.printStackTrace();
                throw e;
            }

            return crate;
        }
        public boolean update(Crate crate) throws SQLException{
            try {
                Connection conn = DBConnection.getInstance().getDBcon();
                double height = crate.getHeight();
                double width = crate.getWidth();
                double length = crate.getLength();
                int crateId = crate.getCrateId();


                PreparedStatement psttm = conn.prepareStatement("UPDATE Crate SET height = ?,length = ?, width = ? WHERE id = ? ");
                psttm.setInt(4,crateId);
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
                String sql = String.format("Delete from Crate where id = %d", id);
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