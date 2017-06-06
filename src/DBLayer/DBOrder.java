package DBLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private int getNumberRows(){
        int counter = 0;
        String sql = String.format("SELECT * FROM Orders WHERE deliveryStatus = 0");
        try{
            Connection conn = DBConnection.getInstance().getDBcon();
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()){
                counter++;
            }
        } catch (Exception e){
            e.getMessage();
        } finally {
            DBConnection.closeConnection();
        }
        return counter;
    }

    public ArrayList<Integer> getIDByStatus(){
        java.sql.Connection conn = DBConnection.getInstance().getDBcon();
        try {
            PreparedStatement ps =conn.prepareStatement("SELECT id FROM Orders WHERE deliveryStatus = 0");
            ResultSet resultSet = ps.executeQuery();
            ArrayList<Integer> aux = new ArrayList<>();
            while(resultSet.next())
            {
                aux.add(resultSet.getInt("id"));
            }
            return aux;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> aux2 = new ArrayList<>();
        aux2.add(0);
        return aux2;
    }

    public ArrayList<String> getDatesByStatus(ArrayList<Integer> orderIDs){
        java.sql.Connection conn = DBConnection.getInstance().getDBcon();
        try {
            ArrayList<String> aux = new ArrayList<>();
            for(int orderID: orderIDs) {
                PreparedStatement ps = conn.prepareStatement("SELECT orderDate FROM Orders WHERE id = ?");
                ps.setInt(1,orderID);
                ResultSet resultSet = ps.executeQuery();

                if (resultSet.next()) {
                    aux.add(resultSet.getString("orderDate"));
                }
            }
            return aux;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<String> aux2 = new ArrayList<>();
        aux2.add("0");
        return aux2;
    }

    public boolean deliverOrder(int orderID){
        java.sql.Connection conn = DBConnection.getInstance().getDBcon();
        try{
            PreparedStatement ps = conn.prepareStatement("UPDATE Orders SET deliveryStatus = 1 WHERE id = ?");
            ps.setInt(1,orderID);
            DBOrderLine dbOrderLine = new DBOrderLine();
            dbOrderLine.updateProductQuantities(orderID);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
