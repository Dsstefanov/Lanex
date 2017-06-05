package DBLayer;

import ModelLayer.Crate;
import ModelLayer.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by RedJohn on 5/31/2017.
 */
public class DBProductCrateMap {

    public ArrayList<Integer> create(int orderID,ArrayList<Crate> crates){
        java.sql.Connection conn = DBConnection.getInstance().getDBcon();
        ArrayList<Integer> crateNumbers = new ArrayList<>();
        for(Crate crate : crates){
            try {
                PreparedStatement ps1 =conn.prepareStatement("INSERT INTO CrateIdentificationTable (crateID, orderID) " +
                        "VALUES ( ?, ?)");
                ps1.setInt(1,crate.getCrateId());
                ps1.setInt(2,orderID);
                ps1.executeUpdate();
                PreparedStatement ps2 = conn.prepareStatement("SELECT TOP 1 crateNumber FROM CrateIdentificationTable ORDER BY crateNumber DESC");
                ResultSet rs = ps2.executeQuery();
                int crateNumber;

                if(rs.next())
                {
                    crateNumber = rs.getInt("crateNumber");
                    System.out.println("Crate number is: " + crateNumber);
                    crateNumbers.add(crateNumber);
                    for(Product product : crate.getProducts()){
                        PreparedStatement ps3 = conn.prepareStatement("INSERT INTO ProductCrateMap (productBarcode, crateNumber, productQuantity) VALUES ( ?, ?, ?)");

                        System.out.println("Product Barcode: " + product.getBarcode());
                        ps3.setString(1,product.getBarcode());
                        ps3.setInt(2,crateNumber);
                        ps3.setInt(3,product.getCurrentQuantity());
                        ps3.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return crateNumbers;
    }
    /** To be modified to DeleteOnCascade**/
    public boolean delete(int orderID){
        java.sql.Connection conn = DBConnection.getInstance().getDBcon();
        try {
            PreparedStatement ps1 = conn.prepareStatement("SELECT crateNumber FROM CrateIdentificationTable WHERE orderID = ? ");
            ps1.setInt(1,orderID);
            ResultSet rs = ps1.executeQuery();
            while(rs.next()){
                int crateNumber = rs.getInt("crateNumber");
                PreparedStatement ps2 = conn.prepareStatement("DELETE FROM ProductCrateMap WHERE crateNumber = ?");
                ps2.setInt(1,crateNumber);
                ps2.executeUpdate();
            }
            PreparedStatement ps3 = conn.prepareStatement("DELETE FROM CrateIdentificationTable WHERE orderID = ?");
            ps3.setInt(1,orderID);
            ps3.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
