package DBLayer;

import ModelLayer.Contractor;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Admin on 5/5/2017.
 */
public class DBContractorTest {
    DBContractor dbCon;

    @org.junit.Before
    public void setUp() throws Exception {
        /*try {
            DBCleanup.cleanDB();//TODO fix the cleanDB class
        }
        catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not clean up the db");
        }*/
        dbCon = new DBContractor();
    }

    @org.junit.Test
    public void create() throws Exception {
        try {
            Contractor contractor = dbCon.create("testName","Skensevaj","testMail@gmail.bg","44113","aalborg",556655);
            assertNotNull(contractor);
            contractor.setName("testName");
            contractor.setAddress("Skensevaj");
            contractor.setEmail("testMail@gmail.bg");
            contractor.setPhone("44113");
            contractor.setCity("aalborg");
            contractor.setCvr(556655);
            assertEquals("testName", contractor.getName());
            assertEquals("Skensevaj", contractor.getAddress());
            assertEquals("testMail@gmail.bg", contractor.getEmail());
            assertEquals("44113", contractor.getPhone());
            assertEquals("aalborg", contractor.getCity());
            assertEquals(556655, contractor.getCvr());
        } catch(Exception e) {
            fail();
            e.getMessage();
        }
    }

    @Test
    public void read() throws Exception {
        try {
            dbCon.read(556655);
            assertNotNull(dbCon.read(556655));
            System.out.println(dbCon.read(556655).getContractor());
        } catch(Exception e) {
            fail();
            e.getMessage();
        }
    }

    @Test
    public void update() throws Exception {
        try {
            dbCon.update(1,"exampleName");
        } catch(Exception e) {
            fail();
            e.getMessage();
        }
    }

    @Test
    public void delete() throws Exception {
        try {
            dbCon.delete(1);
            assertTrue(dbCon.delete((1)));
        } catch(Exception e) {
            fail();
            e.getMessage();
        }
    }

    @Test
    public void readAll() throws Exception {
        try {
            dbCon.readAll().forEach(x -> {System.out.print(x.toString());});
            assertNotNull(dbCon.readAll());
        } catch(Exception e) {
            fail();
            e.getMessage();
        }
    }

}