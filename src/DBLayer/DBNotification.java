package DBLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimerTask;


/**
 * Created by Luke on 28.05.2017.
 */
public class DBNotification {
    String lastUpdated;
    Date lastUpdatedDate;
    String today;
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
    boolean update;


    public String getLastUpdated() throws SQLException{

        try {

            Connection conn = DBConnection.getInstance().getDBcon();
            String sql = String.format("SELECT lastupdated FROM ServiceTable ");
            ResultSet rs = conn.createStatement().executeQuery(sql);
           if(rs.next())
            {

                lastUpdated = rs.getString(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnection.closeConnection();

        }
        return lastUpdated;
    }
    public  String getCurrentDate() {
        today = dateFormat.format(new Date());
        return today;

    }

    public void insertLastUpdatedDate(){
        try {
            Connection conn = DBConnection.getInstance().getDBcon();
            String currentDate = getCurrentDate();
            PreparedStatement preparedStatement = conn.prepareStatement( "UPDATE serviceTable SET lastUpdated = ? ");
            preparedStatement.setString(1,currentDate);
            preparedStatement.executeUpdate();

        }
        catch(SQLException e){
            e.printStackTrace();
        }


    }


}
