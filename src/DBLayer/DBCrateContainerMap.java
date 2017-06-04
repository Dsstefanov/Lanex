package DBLayer;

import ModelLayer.Container;
import ModelLayer.Crate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by RedJohn on 5/31/2017.
 */
public class DBCrateContainerMap {

    public void create(int orderID, int containerID, ArrayList<Container> containers){
        java.sql.Connection conn = DBConnection.getInstance().getDBcon();
        for(Container container : containers){
            try {
                PreparedStatement ps1 =conn.prepareStatement("INSERT INTO ContainerIdentificationTable (containerID, orderID) " +
                        "VALUES ( ?, ?)");
                ps1.setInt(1,containerID);
                ps1.setInt(2,orderID);
                ps1.executeUpdate();
                PreparedStatement ps2 = conn.prepareStatement("SELECT TOP 1 containerNumber FROM ContainerIdentificationTable ORDER BY containerNumber DESC");
                ResultSet rs = ps2.executeQuery();

                if(rs.next())
                {
                    int containerNumber = rs.getInt("containerNumber");
                    for(Integer crateNumber : container.getNumberOfCrates()){
                        PreparedStatement ps3 =conn.prepareStatement("INSERT INTO CrateContainerMap (containerNumber, crateNumber) " +
                                "VALUES ( ?, ?)");
                        ps3.setInt(1,containerNumber);
                        ps3.setInt(2,crateNumber);
                        ps3.executeUpdate();
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /** To be modified to DeleteOnCascade**/
    public boolean delete(int orderID){
        java.sql.Connection conn = DBConnection.getInstance().getDBcon();
        try {
            PreparedStatement ps1 = conn.prepareStatement("SELECT containerNumber FROM ContainerIdentificationTable WHERE orderID = ? ");
            ps1.setInt(1,orderID);
            ResultSet rs = ps1.executeQuery();
            while(rs.next()){
                int crateNumber = rs.getInt("containerNumber");
                PreparedStatement ps2 = conn.prepareStatement("DELETE FROM CrateContainerMap WHERE containerNumber = ?");
                ps2.setInt(1,crateNumber);
                ps2.executeUpdate();
            }
            PreparedStatement ps3 = conn.prepareStatement("DELETE FROM ContainerIdentificationTable WHERE orderID = ?");
            ps3.setInt(1,orderID);
            ps3.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

}
