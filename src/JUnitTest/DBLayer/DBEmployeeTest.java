package DBLayer;

import ModelLayer.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Yeah on 5/8/2017.
 */
public class DBEmployeeTest {

    DBEmployee dbCon;
    @Before
    public void setUp() throws Exception {

        dbCon = new DBEmployee();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void create() throws Exception {
        try {
            Employee employee = dbCon.create("NaskoTest", "here", "donno", "1234567", "Sliven");
            assertNotNull(employee);
            employee.setName("NaskoTest");
            employee.setAddress("here");
            employee.setEmail("donno");
            employee.setPhone("1234567");
            employee.setCity("Sliven");
            assertEquals("NaskoTest", employee.getName());
            assertEquals("here", employee.getAddress());
            assertEquals("donno", employee.getEmail());
            assertEquals("1234567", employee.getPhone());
            assertEquals("Sliven", employee.getCity());
        } catch (Exception e) {
            fail();
            e.getMessage();
        }
    }



    @Test
    public void read() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

}