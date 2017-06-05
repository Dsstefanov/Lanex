package DBLayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by RedJohn on 5/31/2017.
 */
public class DBOrder {

     String today;
     DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
     public String getCurrentDate() {
        today = dateFormat.format(new Date());
        return today;

    }


    public int createOrder(){

        String currentDate = getCurrentDate();

        String sql2 = "SELECT TOP 1 id FROM Orders ORDER BY id DESC";
        java.sql.Connection conn = DBConnection.getInstance().getDBcon();
        try {
            PreparedStatement ps1 =conn.prepareStatement("INSERT INTO Orders (orderDate,deliveryStatus) VALUES (?,0)");
            ps1.setString(1,currentDate);
            //ps1.setInt(2,0);
            ps1.executeUpdate();
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ResultSet rs = ps2.executeQuery();
            if(rs.next())
            return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
return -1;
    }
}
