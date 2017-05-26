package DBLayer;

import ModelLayer.Contractor;
import ModelLayer.Person;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Admin on 4/28/2017.
 */
public class DBContractor implements IDBContractor {
    //check the numbers of tuples in the DB - needed fot transaction part!
    public int getNumberRows(){
        int counter = 0;
        String sql = String.format("SELECT * FROM person WHERE category = 2");
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

    @Override
    public Contractor create(String name, String address, String email, String phone, String city, int cvr) throws SQLException{
        int currentRows = getNumberRows();
        Contractor contractor = new Contractor(name, address, email, phone, city, cvr);
        //Transaction between person and contractor tables. It's valid in all cases that's why i check every time before and after with getNumberOfRows method!!
        String sql = "BEGIN TRANSACTION " +
                "INSERT INTO person (name, address, email, phone, city, category) " +
                "VALUES ('"+name+"', '"+address+"', '"+email+"', '"+phone+"', '"+city+"', 2) " +
                "declare @sql as int " +
                "SELECT @sql = id " +
                "FROM Person ORDER BY Person.id ASC " +
                "INSERT INTO contractor (cvr, person_id) VALUES ('"+cvr+"', @sql) " +
                "IF @@ROWCOUNT <> 1 " +
                "BEGIN " +
                "ROLLBACK " +
                "RAISERROR('CVR already exists!', 16, 1) " +
                "END " +
                "ELSE " +
                "BEGIN " +
                "COMMIT " +
                "END";
        try{
            Connection conn = DBConnection.getInstance().getDBcon();
            conn = DBConnection.getInstance().getDBcon();
            conn.createStatement().executeUpdate(sql);

        } catch (SQLException e){
            e.getMessage();
        } finally {
            DBConnection.closeConnection();
        }

        int lastChangesOfRows = getNumberRows();
        if(currentRows == lastChangesOfRows) {
            throw new IllegalArgumentException("The CVR already exists!!!");
        }

        return contractor;
    }

    @Override
    public Contractor read(int cvr) throws SQLException{
        Contractor contractor = null;
        try {
            Connection conn = DBConnection.getInstance().getDBcon();
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
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE Person \n" +
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
            //preparedStatement.setInt(6,cvr); work in eclipse
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
