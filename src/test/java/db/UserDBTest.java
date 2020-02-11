package db;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDBTest {

    UserDB database = new UserDB();
    User adminUser;

    @Before
    public void setUp() {
        adminUser = new User(System.getenv("ADMIN_NAME"));
    }

    @After
    public void tearDown() {
        database = null;
    }


    @Test
    public void shouldAddToDB_WhenAddingUser() {
        database.addUser(adminUser);
        Assert.assertEquals(adminUser, database.getUserByID(1));
    }
}