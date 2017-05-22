package JUnitTest.DBLayer;

import DBLayer.DBWarehouse;
import ModelLayer.Warehouse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by USER on 8.5.2017 г..
 */
public class DBWarehouseTest {
    private DBWarehouse dbWarehouse=null;
    private Warehouse warehouse = null;

    @Before
    public void setUp() throws Exception {
        dbWarehouse = new DBWarehouse();
        warehouse = dbWarehouse.create(120, 20, 20);
    }

    @Test
    public void create() throws Exception {
        try {
            assertNotNull(warehouse);
        }catch (Exception e){
            fail();
            e.getMessage();
        }
    }

    @Test
    public void read() throws Exception {
        try {
            dbWarehouse.read(warehouse.getId());
            assertNotNull(dbWarehouse.read(warehouse.getId()));
            System.out.println(dbWarehouse.read(warehouse.getId()).toString());
        }catch (Exception e){
            fail();
            e.getMessage();
        }
    }

    @Test
    public void update() throws Exception {
        warehouse.setHeight(5);
        warehouse.setLength(5);
        warehouse.setWidth(5);
        dbWarehouse.update(warehouse);
        assertEquals(warehouse.getHeight(),dbWarehouse.read(warehouse.getId()).getHeight());
        assertEquals(warehouse.getLength(),dbWarehouse.read(warehouse.getId()).getLength());
        assertEquals(warehouse.getWidth(),dbWarehouse.read(warehouse.getId()).getWidth());
    }

    @Test
    public void delete() throws Exception {
        try {
            boolean success = dbWarehouse.delete(warehouse.getId());
            assertTrue(success);
            warehouse=null;
        } catch(Exception e) {
            fail();
            e.getMessage();
        }
    }

    @After
    public void tearDown() throws Exception {
        if (warehouse!=null){
            dbWarehouse.delete(warehouse.getId());
            warehouse=null;
        }
    }
}