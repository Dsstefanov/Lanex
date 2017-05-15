package DBLayer;

import ModelLayer.Contractor;
import ModelLayer.Crate;
import ModelLayer.Person;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Luke on 10.05.2017.
 */
public class DBCrate {


    public static void main(String[] args) {

        try {
           Crate crate = new Crate(150,120,130, "11234", "1234");
           // new DBCrate().create(150,120,130,"11234", "1234");
            new DBCrate().delete("1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("success");
    }
        public Crate create(int height, int width, int lenght, String crateId, String productId) throws SQLException {
            Crate crate = new Crate(height, width, lenght, crateId, productId);
            String sql = String.format("INSERT INTO Crate (height, width, lenght, crateId, productId ) VALUES ('%d', '%d', '%d', '%s','%s')", height, width, lenght, crateId,productId);

            try {
                java.sql.Connection conn = DBConnection.getInstance().getDBcon();
                conn.createStatement().executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBConnection.closeConnection();
            }
            return crate;
        }


        public Crate read(String crateId) throws SQLException{
            Crate crate = null;
            try{
                java.sql.Connection conn = DBConnection.getInstance().getDBcon();
                String sql = String.format("SELECT * FROM crate where crateId=%s", crateId);
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
                int height = rs.getInt(1);
                int width = rs.getInt(2);
                int lenght = rs.getInt(3);
                String crateId = rs.getString(4);
                String productId= rs.getString(5);
                crate = new Crate(height, width, lenght, crateId, productId);
            } catch(SQLException e) {
                e.printStackTrace();
                throw e;
            }

            return crate;
        }
        public boolean update(Crate crate) throws SQLException{
            try {
                Connection conn = DBConnection.getInstance().getDBcon();
                int height = crate.getHeight();
                int width = crate.getWidth();
                int lenght = crate.getLenght();
                String crateId = crate.getCrateId();
                String productId = crate.getProductId();

            /*String sql = String.format("UPDATE Product SET barcode = '%s', current_quantity = '%d', min_quantity = '%d', max_quantity = '%d', cvr = '%d' WHERE barcode = '%s' ;",product.getProductID(),product.getCurrentQuantity(),product.getMinQuantity(),product.getMaxQuantity(),product.getCvr(),product.getProductID());
            */
                PreparedStatement psttm = conn.prepareStatement("UPDATE Product SET height = ?, width = ?, lenght = ?, productId = ? WHERE crateId = ? ");
                // psttm.setNString(1,productId);
                psttm.setInt(1,height);
                psttm.setInt(2,width);
                psttm.setInt(3,lenght);
                psttm.setNString(4,crateId);
                psttm.setNString(5,productId);
                psttm.executeUpdate();
            } catch(SQLException e) {
                e.printStackTrace();
                throw e;

            }
            return true;
        }

        public boolean delete(String productId)throws SQLException{
            try {
                java.sql.Connection conn = DBConnection.getInstance().getDBcon();
                String sql = String.format("Delete from Crate where productId='%s'", productId);
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