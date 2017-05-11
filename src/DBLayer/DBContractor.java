package DBLayer;

import ModelLayer.Contractor;
import ModelLayer.Person;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Admin on 4/28/2017.
 */
public class DBContractor implements IDBContractor {
    @Override
    public Contractor create(String name, String address, String email, String phone, String city, int cvr) throws SQLException{
        Contractor contractor = new Contractor(name, address, email, phone, city, cvr);
        String sql = String.format("INSERT INTO person (name, address, email, phone, city, category) VALUES ('%s', '%s', '%s', '%s', '%s', 2)", name, address, email, phone, city);
        try{
            Connection conn = DBConnection.getInstance().getDBcon();
            conn.createStatement().executeUpdate(sql);

            String sql2 = "SELECT TOP 1 id FROM Person ORDER BY id DESC";
            ResultSet rs = conn.createStatement().executeQuery(sql2);

            if(rs.next()) {
                String sql3 = "INSERT INTO contractor (cvr, person_id) VALUES ("+cvr+","+rs.getInt("id")+")";
                conn.createStatement().executeUpdate(sql3);
            }else{
                throw new SQLException("Something went wrong with inserting the data into the contractor");
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection();
        }
        return contractor;
    }

    @Override
    public Contractor read(int cvr) throws SQLException{
        Contractor contractor = null;
        try {
            Connection conn = DBConnection.getInstance().getDBcon();
            //String sql = String.format("SELECT * FROM contractor WHERE cvr = %d", cvr);
            String sql = String.format("SELECT p.id, p.name , p.address, p.email, p.city, p.phone, " +
                    "c.cvr FROM person p \n" +
                    "INNER JOIN contractor c \n" +
                    "ON p.id = c.person_id \n" +
                    "WHERE c.cvr =" + cvr + " \n" +
                    "ORDER BY p.id ASC");
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while(rs.next()) {
                contractor = buildObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnection.closeConnection();
        }
        return contractor;
    }

    @Override
    public boolean update(Contractor contractor, int cvr) throws SQLException {
        try {
            Connection conn = DBConnection.getInstance().getDBcon();
            String name = contractor.getName();
            String address = contractor.getAddress();
            String email = contractor.getEmail();
            String phone = contractor.getPhone();
            String city = contractor.getCity();
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE Person \n" +
                    "SET name = ?, address = ?, email = ?, city = ?, phone = ?\n" +
                    "FROM Person p\n" +
                    "INNER JOIN Contractor c\n" +
                    "ON p.id = c.person_id\n" +
                    "WHERE c.cvr = cvr");
            preparedStatement.setNString(1,name);
            preparedStatement.setNString(2,address);
            preparedStatement.setNString(3,email);
            preparedStatement.setNString(4,city);
            preparedStatement.setNString(5,phone);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnection.closeConnection();
        }
        return true;
    }

    @Override
    public boolean delete(int id) throws SQLException{
        try {
            Connection conn = DBConnection.getInstance().getDBcon();
            String sql = String.format("DELETE FROM person WHERE id = '%d'", id);
            conn.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnection.closeConnection();
        }
        return true;
    }

    @Override
    public ArrayList<Person> readAll() throws SQLException {
        ArrayList<Person> person = new ArrayList<>();
        String sql = "SELECT * FROM person WHERE category = 2";
        try(Statement st = DBConnection.getInstance().getDBcon().createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                person = buildObjects(rs);// create method buildObject
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBConnection.closeConnection();
        }
        return  person;
    }

    private Contractor buildObject(ResultSet rs) throws SQLException{
        Contractor contractor = new Contractor();
        try {
            contractor.setName(rs.getString("name"));
            contractor.setAddress(rs.getString("address"));
            contractor.setEmail(rs.getString("email"));
            contractor.setPhone(rs.getString("phone"));
            contractor.setCity(rs.getString("city"));
            contractor.setCvr(rs.getInt(1));
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return contractor;
    }

    private ArrayList<Person> buildObjects(ResultSet rs) throws SQLException{
        ArrayList<Person> person = new ArrayList<>();
        while(rs.next()) {
            person.add(buildObject(rs));
        }
        return person;
    }
}
