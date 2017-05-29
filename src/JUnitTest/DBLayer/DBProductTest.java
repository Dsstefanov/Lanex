package JUnitTest.DBLayer;

import ModelLayer.Product;
//import org.junit.runners.MethodSorters;
//import org.junit.FixMethodOrder;
import org.junit.After;
import org.junit.Test;

import DBLayer.DBProduct;

import static org.junit.Assert.*;

/**
 *
 */
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DBProductTest {
    DBProduct dbProduct;
    boolean isDeleted = false;
    Product product = null;
    @org.junit.Before
    public void setUp() throws Exception {

        try{
            dbProduct = new DBProduct();
            int dailyConsumption = 30;
            product = new Product(1.15,0.44,0.23,"123456",37*dailyConsumption,74*dailyConsumption,44*dailyConsumption,dailyConsumption,"Lime Rope",55555555);
            dbProduct.create(product);
            isDeleted = false;
        } catch (Exception e){
            System.out.println("Couldn't insert the product in the DB");
            fail();
        }
    }

    @After
    public void tearDown() throws Exception {
        if (!isDeleted) {
            try {
                dbProduct.delete("123456");
            } catch (Exception e) {
                System.out.println("Couldn't remove the test contractor from the DB");
                fail();
            }
        }
    }


    @org.junit.Test
    public void testACreate() throws Exception {
        try {
            assertNotNull(product);
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void testBRead() throws Exception {
        try {
            dbProduct.read("123456");
            assertNotNull(dbProduct.read("123456"));
            System.out.println(dbProduct.read("123456"));
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void testCUpdate() throws Exception {
        try {
            Product myNewProduct = dbProduct.read("123456");
            assertNotNull(dbProduct.read("123456"));
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
            isDeleted = dbProduct.delete("123456");
            assertTrue(isDeleted);
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void readAll() throws Exception {
        try {
            dbProduct.readAll().forEach(x -> {System.out.print(x.toString());});
            assertNotNull(dbProduct.readAll());
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

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