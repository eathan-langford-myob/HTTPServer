package db;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User adminUser;
    private User notAdminUser;

    @Before
    public void setUp() {
        adminUser = new User("adminUser", true);
        notAdminUser = new User("testUser");
    }


    @Test
    public void shouldReturnUserName() {
        String actual = adminUser.getName();
        String expected = "adminUser";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnWhenUserIsNotAdmin() {
        Assert.assertFalse(notAdminUser.isAdmin());
    }

    @Test
    public void shouldSetName_WhenCallingSetNameToUpdateName() {
        User newUser = new User("Barry");
        newUser.setName("Steve");

        String actual = newUser.getName();
        String expected = "Steve";

        Assert.assertEquals(expected, actual);
    }
}
