package db;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDBTest {

    UserDB database = new UserDB();
    User adminUser;

    @Before
    public void setUp() throws Exception {
        adminUser = new User("Eathan");
    }

    @After
    public void tearDown() {
        database = null;
    }

//    @Test
//    public void shouldAddAdmin_WhenDBInstantiates() {
//        Assert.assertTrue(database.getUserByID(1).getName().equalsIgnoreCase(Constants.admin_name));
//    }

    @Test
    public void shouldAddToDB_WhenAddingUser() {
        database.addUser(adminUser);
        Assert.assertEquals(adminUser, database.getUserByID(1));
    }
}