package domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.ServerHelper;
import utilities.InvalidRequestException;

public class UserServiceTest {
    private ServerHelper testing = new ServerHelper();
    private UserService userService;

//TODO remove server logic from tests, test method behaviour

    @Before
    public void setUp() throws Exception {
        testing.setup();
        userService = new UserService(testing.DB, testing.outputMessages);
    }

    @After
    public void tearDown() {
        testing.tearDown();
    }


    @Test
    public void shouldDisplayAllUsers_WhenCallingUserServiceRead() {
        String actual = userService.readAll();
        String expected = "[" + "1," + testing.admin.getName() + "]";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateUser_WhenCallingUserServiceCreate() {
        String userName = "Barry";
        try {
            userService.createUser(userName);
            Assert.assertTrue(userService.readAll().contains(userName));
        } catch (InvalidRequestException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowException_WhenRequestIsEmpty() throws InvalidRequestException {
        String userName = "";
        userService.createUser(userName);
    }

    @Test
    public void shouldGetUserByID_WhenCallingUserServiceReadUser() {
        String expected = "Steven";
        try {
            userService.createUser(expected);
            String actual = userService.ReadByID(2).getName();
            Assert.assertEquals(expected, actual);
        } catch (InvalidRequestException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowException_WhenReadingUserWithWrongID() throws InvalidRequestException {
        userService.ReadByID(4);
    }

    @Test
    public void shouldReturnTrue_WhenUserExistsInDB() {
        String expected = "Batman";
        try {
            userService.createUser(expected);
            Assert.assertTrue(userService.isUserInDB(2));
        } catch (InvalidRequestException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void shouldRemoveUser_WhenUsingIDWithUserService() {
        String userName = "Steve Rogers";

        try {
            userService.createUser(userName);
            userService.removeUserByID(2);
            Assert.assertFalse(userService.readAll().contains(userName));
        } catch (InvalidRequestException e) {
            System.out.println(e.getMessage());
            ;
        }
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowException_WhenRemovingWithWrongID() throws InvalidRequestException {
        userService.removeUserByID(2);
    }

    @Test
    public void shouldUpdateUserName_WhenCallingUserServiceUpdate() {
        String originalName = "Tony Stark";
        String expected = "IronMan";

        try {
            userService.createUser(originalName);
            userService.updateUserNameByID(2, expected);
            String actual = userService.ReadByID(2).getName();
            Assert.assertEquals(expected, actual);
        } catch (InvalidRequestException e) {
            System.out.println(e.getMessage());
            ;
        }
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowException_WhenCallingUpdateByIDWithWrongID() throws InvalidRequestException {
        userService.updateUserNameByID(2, "Steve");
    }

}


