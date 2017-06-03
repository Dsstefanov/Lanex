package DBLayer;

import ModelLayer.Crate;
import ModelLayer.Product;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by RedJohn on 6/3/2017.
 */
public class DBProductCrateMapTest {



    @Test
    public void create() throws Exception {
        try {
            DBProduct dbProduct = new DBProduct();
            DBCrate dbCrate = new DBCrate();
            DBProductCrateMap dbProductCrateMap = new DBProductCrateMap();
            Product productOne = dbProduct.read("123488");
            Product productTwo = dbProduct.read("147148");
            Crate crate = dbCrate.read(1);
            crate.addProduct(productOne);
            crate.addProduct(productTwo);
            Crate crate1 = dbCrate.read(1);
            crate1.addProduct(productOne);
            crate1.addProduct(productTwo);
            ArrayList<Crate> crates = new ArrayList<>();
            crates.add(crate);
            crates.add(crate1);
            assertTrue(dbProductCrateMap.create(2,crates));
        } catch (Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void delete() throws Exception {
        try{
            DBProductCrateMap dbProductCrateMap = new DBProductCrateMap();
            assertTrue(dbProductCrateMap.delete(2));
        } catch (Exception e) {
            e.getMessage();
            fail();

        }
    }
}
