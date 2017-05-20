package DBLayer;

import ModelLayer.Crate;

import java.sql.*;

/**
 * Created by Luke on 10.05.2017.
 */
public class DBCrate {


    public static void main(String[] args) {

       /*try {
           Crate crate = new Crate( "11234",120,130,150);
           new DBCrate().create("11234",120, 130, 150 );
        } catch (SQLException e) {
           e.printStackTrace();
       }
       System.out.println("success");*/
    }
        public Crate create( String crateId, double height, double length, double width) throws SQLException {
            Crate crate = new Crate(crateId, height, length, width);
            String sql = String.format("INSERT INTO Crate (crateId, height, length, width) VALUES (?,?,?,?)");

            try
                    (java.sql.Connection conn = DBConnection.getInstance().getDBcon();
                PreparedStatement ps =conn.prepareStatement(sql)){
                ps.setString(1,crateId);
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


        public Crate read(String crateId) throws SQLException{
            Crate crate = null;
            String sql = String.format("SELECT * FROM crate where crateId= ?");
            try(
                java.sql.Connection conn = DBConnection.getInstance().getDBcon();

//                ResultSet rs = conn.createStatement().executeQuery(sql);
//                if (rs.next()){
//                    crate = buildObject(rs);
//                }
                PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1,crateId);
                //crate = buildObject(ps)

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
                String crateId = rs.getString(1);
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
                String crateId = crate.getCrateId();


                PreparedStatement psttm = conn.prepareStatement("UPDATE Crate SET height = ?,length = ?, width = ? WHERE crateId = ? ");
                psttm.setString(4,crateId);
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

        public boolean delete(String crateId)throws SQLException{
            try {
                java.sql.Connection conn = DBConnection.getInstance().getDBcon();
                String sql = String.format("Delete from Crate where crateId='%s'", crateId);
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