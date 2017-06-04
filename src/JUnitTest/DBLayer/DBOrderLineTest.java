package DBLayer;

import ModelLayer.Product;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by RedJohn on 6/3/2017.
 */
public class DBOrderLineTest {


    @Test
    public void create() throws Exception {
        try {
            DBProduct dbProduct = new DBProduct();
            DBOrderLine dbOrderLine = new DBOrderLine();
            Product productOne = dbProduct.read("222333");
            Product productTwo = dbProduct.read("147148");
            ArrayList <Product> testProducts = new ArrayList<>();
            testProducts.add(productOne);
            testProducts.add(productTwo);
            assertTrue(dbOrderLine.createOrderLine(2,testProducts));
        } catch (Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void delete() throws Exception
    {
        try{
            DBOrderLine dbOrderLine = new DBOrderLine();
            assertTrue(dbOrderLine.deleteOrderLine(2));
        }
        catch (Exception e)
        {
            e.getMessage();
            fail();
        }
    }
}
