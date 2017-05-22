package DBLayer;

import ModelLayer.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.*;

/**
 * Created by Yeah on 5/8/2017.
 */
public class DBEmployeeTest {

    DBEmployee dbCon;
    Employee employee = null;
    boolean isDeleted = false;

    @Before
    public void setUp() throws Exception {
        try {
            dbCon = new DBEmployee();
            employee = dbCon.create("Atanas","Hobrovej","123456@gmail.com","7654321","Vejle",123456789);
        }catch (Exception e) {
            System.out.println("Could not insert this employee in the Database!");
            fail();
        }
    }

    @After
    public void tearDown() throws Exception {
        if(!isDeleted) {
            try {
                dbCon.delete(getEmployeeId());
            }catch(Exception e) {
                System.out.println("Cannot delete the test Employee from Database!");
                fail();
            }
        }

    }

    @Test
    public void create() throws Exception {
        try {
            //Employee employee = dbCon.create(12345678,"NaskoTest", "here", "donno", "1234567", "Sliven");
            assertNotNull(employee);
        } catch (Exception e) {
            e.getMessage();
            fail();
        }
    }



    @Test
    public void read() throws Exception {
        try {
            dbCon.read(123456789);
            assertNotNull(dbCon.read(123456789));
            System.out.println(dbCon.read(123456789).toString());
        }
        catch (Exception e) {
            fail();
            e.getMessage();
        }
    }


    @Test
    public void update() throws Exception {//TODO Finish the update test
        try {
            Employee employee = dbCon.read(123456789);
            assertNotNull(dbCon.read(123456789));
            dbCon.update(employee,123456789);
        }catch(Exception e) {
            e.getMessage();
            fail();
        }

    }

    @Test
    public void delete() throws Exception {
        try {
            isDeleted = dbCon.delete(getEmployeeId());
            assertTrue(isDeleted);
        }catch (Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void readAll() throws Exception {
        try {
            dbCon.readAll().forEach(x -> {
                System.out.print(x.toString());
            });
            assertNotNull(dbCon.readAll());
        }catch (Exception e) {
            e.getMessage();
            fail();
        }
    }


    public int getEmployeeId() {
        try {
            int currentID = 0;
            Connection conn = DBConnection.getInstance().getDBcon();
            String sql = "SELECT TOP 1 id FROM Person ORDER BY id DESC";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()) {
                currentID = rs.getInt("id");
                return currentID;
            }
        } catch (Exception e) {
            System.out.println("Couldn't return the current id");
            fail();
        }
        return 0;
    }

}