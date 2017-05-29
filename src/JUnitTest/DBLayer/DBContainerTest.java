package DBLayer;

import DBLayer.DBContainer;
import ModelLayer.Container;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by RedJohn on 5/27/2017.
 */
public class DBContainerTest {
    DBContainer dbContainer;
    boolean isDeleted = false;

    @org.junit.Before
    public void setUp() throws Exception {

        try{
            dbContainer = new DBContainer();
            dbContainer.create(10,510,80,100);
        } catch (Exception e){
            System.out.println("Couldn't insert the container in the DB");
            e.getMessage();
            fail();
        }
    }

    @After
    public void tearDown() throws Exception {
        if (!isDeleted) {
            try {
                dbContainer.delete(10);
            } catch (Exception e) {
                System.out.println("Couldn't remove the test contractor from the DB");
                fail();
            }
        }
    }


    @org.junit.Test
    public void testACreate() throws Exception {
        try {
            Container container = new Container(10,5,8,10);
            assertNotNull(container);
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }
    //
    @Test
    public void testBRead() throws Exception {
        try {
            dbContainer.read(10);
            assertNotNull(dbContainer.read(10));

        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void testCUpdate() throws Exception {
        try {
            Container container = dbContainer.read(10);
            container.setContainerId(15);
            assertNotNull(container.getContainerId());
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }

    @Test
    public void testDDelete() throws Exception {
        try {
            isDeleted = dbContainer.delete(10);
            assertTrue(isDeleted);
        } catch(Exception e) {
            e.getMessage();
            fail();
        }
    }
}
