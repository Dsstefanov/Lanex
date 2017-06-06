package DBLayer;

import ModelLayer.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by RedJohn on 6/2/2017.
 */
public class DBOrderLine {
    public boolean createOrderLine(int orderID, ArrayList<Product> products)
    {
        java.sql.Connection conn = DBConnection.getInstance().getDBcon();
        for(Product product : products)
        try {
            PreparedStatement ps1 =conn.prepareStatement("INSERT INTO OrderLine (orderID, productBarcode,productQuantity) VALUES (?,?,?)");
            ps1.setInt(1,orderID);
            ps1.setString(2,product.getBarcode());
            ps1.setInt(3,product.getCurrentQuantity());
            //ps1.setInt(2,0);
            ps1.executeUpdate();
            PreparedStatement ps2 = conn.prepareStatement("UPDATE Product SET isOrdered = 1 WHERE barcode = ?");
            ps2.setString(1,product.getBarcode());
            ps2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteOrderLine(int orderID){
        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            PreparedStatement ps1 =conn.prepareStatement("DELETE  FROM OrderLine WHERE orderID = ?");
            ps1.setInt(1,orderID);
            ps1.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean updateProductQuantities(int orderID){
        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            PreparedStatement ps =conn.prepareStatement("SELECT * FROM OrderLine WHERE orderID = ?");
            ps.setInt(1,orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String barcode = rs.getString("productBarcode");
                int productQuantity = rs.getInt("productQuantity");
                PreparedStatement ps2 = conn.prepareStatement("UPDATE Product SET isOrdered = 0,currentQuantity = (currentQuantity + ?) WHERE barcode = ?");
                ps2.setInt(1,productQuantity);
                ps2.setString(2,barcode);
                ps2.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;


    }
}
