package JUnitTest.DBLayer;


import DBLayer.DBCrate;
import ModelLayer.Crate;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Luke on 15.05.2017.
 */
public class DBCrateTest {


        DBCrate dbCrate;
        boolean isDeleted = false;

        @org.junit.Before
        public void setUp() throws Exception {

            try{
                dbCrate = new DBCrate();
                dbCrate.create(12,51,8,10);
            } catch (Exception e){
                System.out.println("Couldn't insert the crate in the DB");
                e.getMessage();
                fail();
            }
        }

        @After
        public void tearDown() throws Exception {
            if (!isDeleted) {
                try {
                    dbCrate.delete(12);
                } catch (Exception e) {
                    System.out.println("Couldn't remove the test contractor from the DB");
                    fail();
                }
            }
        }


        @org.junit.Test
        public void testCreate() throws Exception {
            try {
                assertNotNull(dbCrate.create(10,5,8,10));
            } catch(Exception e) {
                e.getMessage();
                fail();
            }
        }
//
        @Test
        public void testRead() throws Exception {
            try {
                dbCrate.read(12);
                assertNotNull(dbCrate.read(12));

            } catch(Exception e) {
                e.getMessage();
                fail();
            }
        }

    @Test
    public void testFindAvailableID() throws Exception {
        try {
            dbCrate.findAvailableID();
            assertNotNull(dbCrate.findAvailableID());

        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

        @Test
        public void testUpdate() throws Exception {
            try {
                Crate crate = dbCrate.read(12);
                crate.setCrateId(15);
                assertNotNull(crate.getCrateId());
            } catch(Exception e) {
                e.getMessage();
                fail();
            }
        }

        @Test
        public  void testGetRequiredCrate() throws Exception{
            try {
                ArrayList <Double> dimensions = new ArrayList<>();
                dimensions.add( 1.01);
                dimensions.add(1.02);
                dimensions.add(1.03);
                assertNotNull(dbCrate.getRequiredCrate(dimensions));
            } catch(Exception e) {
                e.getMessage();
                fail();
            }
        }

        @Test
        public void testDelete() throws Exception {
            try {
                isDeleted = dbCrate.delete(12);
                assertTrue(isDeleted);
            } catch(Exception e) {
                e.getMessage();
                fail();
            }
        }

}
