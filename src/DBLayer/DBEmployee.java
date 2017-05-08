package DBLayer;

import ModelLayer.Contractor;
import ModelLayer.Employee;
import ModelLayer.Warehouse;
import com.sun.xml.internal.bind.v2.TODO;

import java.sql.*;

/**
 * Created by USER on 12.4.2017 г..
 */
public class DBEmployee implements IDBEmployee {
    /**
     *
    // * @param name: employee name
   //  * @param address: employee address
    // * @param email: employee email
    // * @param phone: employee phone
    // * @param city: employee city
     * @return employee object
     * @throws SQLException thrown if insertion fails
     */

    @Override
    public Employee create(String name, String address, String email, String phone, String city)throws SQLException{
        Employee employee = new Employee(name, address, email, phone, city);
        String sql = String.format("INSERT INTO person (name, address, email, phone, city, category) VALUES ('%s', '%s', '%s', '%s', '%s', 1)", name, address, email, phone, city);
        try{
            Connection conn = DBConnection.getInstance().getDBcon();
            conn.createStatement().executeUpdate(sql);

            String sql2 = "SELECT TOP 1 id FROM Person ORDER BY id DESC";
            ResultSet rs = conn.createStatement().executeQuery(sql2);

            if(rs.next()) {
                String sql3 = "INSERT INTO employee (person_id,work_id) VALUES ("+rs.getInt("id")+",1111)";
                conn.createStatement().executeUpdate(sql3);
            }else{
                throw new SQLException();
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection();
        }
        return employee;
    }

    /*TODO method*/
    @Override
    public Employee read(int id) throws SQLException{
        Employee employee = new Employee();
        try{
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            String sql = String.format("SELECT * FROM person where id='%d'",id);
            String sql2 = String.format("SELECT * FROM employee where person_id='%d'",id);
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()){
                employee = buildObject(rs);
            }
        }catch (SQLException e) {
            throw e;
        }finally{
            DBConnection.closeConnection();
        }
        return employee;
    }
    private Employee buildObject(ResultSet rs) throws SQLException{
        Employee employee = new Employee();
        try {
            employee.setWorkId(rs.getInt("id"));
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return employee;
    }


    /*TODO method*/
    @Override
    public Employee update(int id) throws SQLException{
        Employee employee = new Employee();
        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
            String sql = String.format("UPDATE employee SET phone='%s' WHERE id = '%d'",id);
            conn.createStatement().executeUpdate(sql);
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return employee;
    }

    @Override
    public boolean delete(int id)throws SQLException{
        try {
            java.sql.Connection conn = DBConnection.getInstance().getDBcon();
           // String sql = String.format("Delete from employee where person_id=%d", id);
           // conn.createStatement().executeUpdate(sql);
            String sql2 = String.format("Delete from person where id= '%d'",id);
            conn.createStatement().executeUpdate(sql2);
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            DBConnection.closeConnection();
        }
        return true;
    }

}
