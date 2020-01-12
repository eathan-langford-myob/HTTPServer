package domain;

import handlers.TestServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {
    private TestServer testing = new TestServer();
    private UserService userService;



    @Before
    public void setUp() throws Exception {
        testing.setup();
        userService = new UserService(testing.DB);
    }

    @After
    public void tearDown() {
        testing.tearDown();
    }


    @Test
    public void shouldDisplayAllUsers_WhenCallingUserServiceRead() {
        String actual = userService.readAll();
        String expected = "[" +"1," + testing.admin.getName() +"]";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateUser_WhenCallingUserServiceCreate() {
        String userName = "Barry";
        userService.createUser(userName);

        Assert.assertTrue(userService.readAll().contains(userName));
    }

    @Test
    public void shouldGetUserByID_WhenCallingUserServiceReadUser() {
        String expected = "Steven";
        userService.createUser(expected);
        String actual = userService.ReadByID(2).getName();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrue_WhenUserExistsInDB() {
        String expected = "Batman";
        userService.createUser(expected);

        Assert.assertTrue(userService.isUserInDB(2));
    }

    @Test
    public void shouldRemoveUser_WhenUsingIDWithUserService() {
        String userName = "Steve Rogers";
        userService.createUser(userName);
        userService.removeUserByID(2);

        Assert.assertFalse(userService.readAll().contains(userName));
    }

    @Test
    public void shouldUpdateUserName_WhenCallingUserServiceUpdate() {
        String originalName = "Tony Stark";
        String expected = "IronMan";
        userService.createUser(originalName);
        userService.updateUserNameByID(2, expected);
        String actual = userService.ReadByID(2).getName();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrueForUpdateRequestContainingOriginalName_WhenUpdatingUserNameWithUserService() {
        String userName = "Barry";
        userService.createUser(userName);
        String nameChangeRequest = "Barry, The Flash";

        Assert.assertTrue(userService.isSameName(2, nameChangeRequest));
    }


}


