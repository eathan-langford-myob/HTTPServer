package db;

import org.junit.Assert;
import org.junit.Test;
import utilities.Constants;

public class UserTest {

    UserDB database = new UserDB();

    @Test
    public void shouldInstantiateAsAdmin_WhenMatchingAdminName() {
        User adminUser = new User(Constants.admin_name);
        Assert.assertTrue(adminUser.isAdmin());
    }
}
