package DBLayer;

import ModelLayer.Employee;

import java.sql.*;

/**
 * Created by USER on 12.4.2017 г..
 */
public class DBEmployee {
    /**
     *
     * @param name: employee name
     * @param address: employee address
     * @param email: employee email
     * @param phone: employee phone
     * @param city: employee city
     * @return employee object
     * @throws SQLException thrown if insertion fails
     */
    public Employee create(String name, String address, String email, String phone, String city)throws SQLException{
        Employee employee = new Employee(name, address, email, phone, city);
        String sql = String.format("INSERT INTO person (name, address, email, phone, city, category) VALUES ('%s', '%s', '%s', '%s', '%s', 1)", name, address, email, phone, city);

        try{
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            conn.createStatement().executeUpdate(sql);
            String sql2 = "SELECT TOP 1 id FROM Person ORDER BY id DESC";
            ResultSet rs = conn.createStatement().executeQuery(sql2);
            if(rs.next()) {
                String sql3 = "INSERT INTO employee (person_id) VALUES ("+rs.getInt("id")+")";
                conn.createStatement().executeUpdate(sql3);
            }else{
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw e;
        }catch (Exception e){
            return null;
        }finally {
            DBConnection.closeConnection();
        }
        return employee;
    }

}
