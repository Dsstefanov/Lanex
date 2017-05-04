package DBLayer;

import ModelLayer.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by RedJohn on 4/26/2017.
 */
public class DBProduct {
    public static void main(String[] args) {

        try {
            Product product = new Product("123456",152,20,200,2666);
            new DBProduct().delete("123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("success");
    }
    public Product create(String productID, int currentQuantity, int minQuantity, int maxQuantity, int cvr)
    {
        Product product = new Product(productID,currentQuantity,minQuantity,maxQuantity,cvr);
        String sql = String.format("INSERT INTO Product VALUES ('%s', '%d', '%d', '%d', '%d') ",productID,currentQuantity,minQuantity,maxQuantity,cvr);


        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            conn.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBConnection.closeConnection();
        }
        return product;
    }
    public Product read(String productId) throws SQLException{
        Product product = null;
        try{
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            String sql = String.format("SELECT * FROM product where barcode=%s",productId);
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()){
                product = buildObject(rs);
            }
        }catch (SQLException e) {
            throw e;
        }finally{
            DBConnection.closeConnection();
        }
        return product;
    }
    private Product buildObject(ResultSet rs) throws SQLException{
        Product product;
        try {
            String productId = rs.getString(1);
            int currentQuantity = rs.getInt(2);
            int minQuantity = rs.getInt(3);
            int maxQuantity = rs.getInt(4);
            int cvr = rs.getInt(5);
            product = new Product(productId,currentQuantity,minQuantity,maxQuantity,cvr);
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return product;
    }
    public boolean update(Product product) throws SQLException{
        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            String productId = product.getProductID();
            int curentQuantity = product.getCurrentQuantity();
            int minQuantity = product.getMinQuantity();
            int maxQuantity = product.getMaxQuantity();
            int cvr = product.getCvr();
            /*String sql = String.format("UPDATE Product SET barcode = '%s', current_quantity = '%d', min_quantity = '%d', max_quantity = '%d', cvr = '%d' WHERE barcode = '%s' ;",product.getProductID(),product.getCurrentQuantity(),product.getMinQuantity(),product.getMaxQuantity(),product.getCvr(),product.getProductID());
            */
            PreparedStatement psttm = conn.prepareStatement("UPDATE Product SET current_quantity = ?, min_quantity = ?, max_quantity = ?, cvr = ? WHERE barcode = ? ");
           // psttm.setNString(1,productId);
            psttm.setInt(1,curentQuantity);
            psttm.setInt(2,minQuantity);
            psttm.setInt(3,maxQuantity);
            psttm.setInt(4,cvr);
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
            String sql = String.format("Delete from Product where barcode='%s'", productId);
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
