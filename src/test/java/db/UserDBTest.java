package db;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDBTest {

    UserDB database = new UserDB();
    User userTest;

    @Before
    public void setUp() throws Exception {
        userTest = new User("Barry");
    }

    @After
    public void tearDown() {
        database = null;
    }

    @Test
    public void shouldAddAdmin_WhenDBInstantiates() {
        Assert.assertTrue(database.getUserByID(1).getName().equalsIgnoreCase("Eathan"));
    }

    @Test
    public void shouldAddToDB_WhenAddingUser() {
        database.addUser(userTest);
        Assert.assertEquals(userTest, database.getUserByID(2));
    }
}