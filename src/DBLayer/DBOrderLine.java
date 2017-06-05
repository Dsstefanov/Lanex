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
}
