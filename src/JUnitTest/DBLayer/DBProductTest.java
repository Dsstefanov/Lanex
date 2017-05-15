package DBLayer;

import ModelLayer.Product;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DBProductTest {
    DBProduct dbProduct;
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
            dbProduct = new DBProduct();
            dbProduct.create("654321",300,50,500,556655);
        } catch (Exception e){
            System.out.println("Couldn't insert the contractor in the DB");
            fail();
        }
    }

    @After
    public void tearDown() throws Exception {
        if (!isDeleted) {
            try {
                dbProduct.delete("654321");
            } catch (Exception e) {
                System.out.println("Couldn't remove the test contractor from the DB");
                fail();
            }
        }
    }


    @org.junit.Test
    public void testACreate() throws Exception {
        try {
            Product contractor = new Product("654321",300,50,500,556655);
            assertNotNull(contractor);
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void testBRead() throws Exception {
        try {
            dbProduct.read("654321");
            assertNotNull(dbProduct.read("654321"));
            System.out.println(dbProduct.read("654321"));
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void testCUpdate() throws Exception {
        try {
            Product myNewProduct = dbProduct.read("654321");
            assertNotNull(dbProduct.read("654321"));
            myNewProduct.setMaxQuantity(myNewProduct.getMaxQuantity()*2);
            dbProduct.update(myNewProduct);
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void testDDelete() throws Exception {
        try {
            isDeleted = dbProduct.delete("654321");
            assertTrue(isDeleted);
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    /*@Test
    public void readAll() throws Exception {
        try {
            dbProduct.readAll().forEach(x -> {System.out.print(x.toString());});
            assertNotNull(dbProduct.readAll());
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }*/

    /*public String getProductId() {
        try {
            String currentID;
            Connection conn = DBConnection.getInstance().getDBcon();
            String sql = "SELECT TOP 1 id FROM Product ORDER BY id DESC";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()) {
                currentID = rs.getString("barcode");
                return currentID;
            }
        } catch (Exception e) {
            System.out.println("Couldn't return the current id");
            fail();
        }
        return 0;
    }*/

}