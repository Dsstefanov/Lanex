package DBLayer;

import ModelLayer.Contractor;
import org.junit.After;
import org.junit.Test;
import java.sql.*;
import static org.junit.Assert.*;

/**
 * Created by Admin on 5/5/2017.
 */
public class DBContractorTest {
    DBContractor dbCon;
    boolean isDeleted = false;

    @org.junit.Before
    public void setUp() throws Exception {
        /*try {
            DBCleanup.cleanDB();//TODO fix the cleanDB class
        }
        catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not clean up the db");
        }*/
        try{
            dbCon = new DBContractor();
            dbCon.create("testName","Skensevaj","testMail@gmail.bg","44113","Aarhus",556655);
        } catch (Exception e){
            System.out.println("Couldn't insert the contractor in the DB");
            fail();
        }
    }

    @After
    public void tearDown() throws Exception {
        if (!isDeleted) {
            try {
                dbCon.delete(getContractorId());
            } catch (Exception e) {
                System.out.println("Couldn't remove the test contractor from the DB");
                fail();
            }
        }
    }


    @org.junit.Test
    public void create() throws Exception {
        try {
            Contractor contractor = new Contractor("testName","Skensevaj","testMail@gmail.bg","44113","Aarhus",556655);
            assertNotNull(contractor);
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void read() throws Exception { //raboti
        try {
            dbCon.read(556655);
            assertNotNull(dbCon.read(556655));
            System.out.println(dbCon.read(556655).getContractor());
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void update() throws Exception { //TODO finish the update fields
        try {
            Contractor contractor = dbCon.read(556655);
            assertNotNull(dbCon.read(556655));
            dbCon.update(contractor, 556655);
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void delete() throws Exception {
        try {
            isDeleted = dbCon.delete(getContractorId());
            assertTrue(isDeleted);
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void readAll() throws Exception {
        try {
            dbCon.readAll().forEach(x -> {System.out.print(x.toString());});
            assertNotNull(dbCon.readAll());
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    public int getContractorId() {
        try {
            int currentID;
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