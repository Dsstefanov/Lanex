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
    public void createOrderLine(int orderID, ArrayList<Product> products)
    {
        for(Product product : products)
        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            PreparedStatement ps1 =conn.prepareStatement("INSERT INTO OrderLine (orderID, productBarcode,productQuantity) VALUES (?,?,?)");
            ps1.setInt(1,orderID);
            ps1.setString(2,product.getBarcode());
            ps1.setInt(3,product.getCurrentQuantity());
            //ps1.setInt(2,0);
            ps1.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
