package ControlLayer;
import DBLayer.DBConnection;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Luke on 23.05.2017.
 */
public class LoginController {
    private ArrayList<Integer> logins = new ArrayList<>();


    public LoginController() {
        setup();
    }

    public void setup() {


        PreparedStatement ps = null;
        String sql = String.format("SELECT WORK_ID FROM EMPLOYEE");
        try {
            Connection conn = DBConnection.getInstance().getDBcon();

            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                logins.add(rs.getInt(1));

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }



    public boolean checkLogin(int x) {
        boolean loginSuccessful = false;
        try {


            if (logins.contains(x)) {
                loginSuccessful = true;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginSuccessful;
    }
}








