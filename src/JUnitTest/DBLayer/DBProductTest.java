package DBLayer;

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
            product = new Product("123456",1.15,0.44,0.23,37*dailyConsumption,74*dailyConsumption,44*dailyConsumption,dailyConsumption,"Lime Rope",11111111);
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
    public void testCreate() throws Exception {
        try {
            int dailyConsumption = 30;
            product = new Product("123488",1.15,0.44,0.23,37*dailyConsumption,74*dailyConsumption,44*dailyConsumption,dailyConsumption,"Lime Rope",11111111);
            assertNotNull(dbProduct.create(product));
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void testRead() throws Exception {
        try {
            dbProduct.read("123456");
            assertNotNull(dbProduct.read("123456"));
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void testUpdate() throws Exception {
        try {
            Product myNewProduct = dbProduct.read("123456");
            myNewProduct.setMaxQuantity(myNewProduct.getMaxQuantity()*2);
            assertNotNull(dbProduct.update(myNewProduct));
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void testDelete() throws Exception {
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