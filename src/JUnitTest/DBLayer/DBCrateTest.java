package JUnitTest.DBLayer;


import DBLayer.DBCrate;
import ModelLayer.Crate;
import org.junit.After;
import org.junit.Test;

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
                dbCrate.create(10,51,8,10);
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
                    dbCrate.delete(10);
                } catch (Exception e) {
                    System.out.println("Couldn't remove the test contractor from the DB");
                    fail();
                }
            }
        }


        @org.junit.Test
        public void testACreate() throws Exception {
            try {
                Crate crate = new Crate(10,5,8,10);
                assertNotNull(crate);
            } catch(Exception e) {
                e.getMessage();
                fail();
            }
        }
//
        @Test
        public void testBRead() throws Exception {
            try {
                dbCrate.read(10);
                assertNotNull(dbCrate.read(10));

            } catch(Exception e) {
                e.getMessage();
                fail();
            }
        }

        @Test
        public void testCUpdate() throws Exception {
            try {
                Crate crate = dbCrate.read(10);
                crate.setCrateId(15);
                assertNotNull(crate.getCrateId());
            } catch(Exception e) {
                e.getMessage();
                fail();
            }
        }

        @Test
        public void testDDelete() throws Exception {
            try {
                isDeleted = dbCrate.delete(10);
                assertTrue(isDeleted);
            } catch(Exception e) {
                e.getMessage();
                fail();
            }
        }
//
//    /*@Test
//    public void readAll() throws Exception {
//        try {
//            dbProduct.readAll().forEach(x -> {System.out.print(x.toString());});
//            assertNotNull(dbProduct.readAll());
//        } catch(Exception e) {
//            e.getMessage();
//            fail();
//        }
//    }*/
//
//    /*public String getProductId() {
//        try {
//            String currentID;
//            Connection conn = DBConnection.getInstance().getDBcon();
//            String sql = "SELECT TOP 1 id FROM Product ORDER BY id DESC";
//            ResultSet rs = conn.createStatement().executeQuery(sql);
//            if (rs.next()) {
//                currentID = rs.getString("barcode");
//                return currentID;
//            }
//        } catch (Exception e) {
//            System.out.println("Couldn't return the current id");
//            fail();
//        }
//        return 0;
//    }*/
//
//    }
}
