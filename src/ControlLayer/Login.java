package ControlLayer;
import DBLayer.DBConnection;
import ModelLayer.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created by Luke on 23.05.2017.
 */
public class Login {
    ArrayList<String> logins = new ArrayList<>();

    public void setup() {

        ArrayList<String> logins = new ArrayList<>();
        PreparedStatement ps = null;
        String sql = String.format("SELECT WORK_ID FROM EMPLOYEE");
        try {
            Connection conn = DBConnection.getInstance().getDBcon();

            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                logins.add(rs.getString(1));

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public boolean checkLogin(String log) {
        boolean loginSuccessful = false;
        try {


            if (logins.contains(log)) {
                loginSuccessful = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginSuccessful;
    }
}








