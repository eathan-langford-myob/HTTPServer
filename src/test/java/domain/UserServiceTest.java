package domain;

import db.User;
import db.UserDB;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utilities.InvalidRequestException;

import java.util.ResourceBundle;

public class UserServiceTest {
    private UserService userService;

    @Before
    public void setUp() {
        UserDB database = new UserDB();
        User adminUser = new User("Eathan", true);
        database.addUser(adminUser);
        ResourceBundle outputMessages = ResourceBundle.getBundle("OutputMessages");
        userService = new UserService(database, outputMessages);
    }

    @Test
    public void shouldDisplayAllUsers_WhenCallingUserServiceRead() {
        String actual = userService.readAll();
        String expected = "[" + "1,Eathan" + "]";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateUser_WhenCallingUserServiceCreate() throws InvalidRequestException {
        String userName = "Barry";
            userService.createUser(userName);
            Assert.assertTrue(userService.readAll().contains(userName));
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowException_WhenRequestIsEmpty() throws InvalidRequestException {
        String userName = "";
        userService.createUser(userName);
    }

    @Test
    public void shouldGetUserByID_WhenCallingUserServiceReadUser() throws InvalidRequestException {
        String expected = "Steven";
            userService.createUser(expected);
            String actual = userService.ReadByID(2).getName();
            Assert.assertEquals(expected, actual);
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowException_WhenReadingUserWithWrongID() throws InvalidRequestException {
        userService.ReadByID(4);
    }

    @Test
    public void shouldReturnTrue_WhenUserExistsInDB() throws InvalidRequestException {
        String expected = "Batman";
        userService.createUser(expected);
        Assert.assertTrue(userService.isUserInDB(2));
    }

    @Test
    public void shouldRemoveUser_WhenUsingIDWithUserService() throws InvalidRequestException {
        String userName = "Steve Rogers";
        userService.createUser(userName);
        userService.removeUserByID(2);
        Assert.assertFalse(userService.readAll().contains(userName));
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowException_WhenRemovingWithWrongID() throws InvalidRequestException {
        userService.removeUserByID(2);
    }

    @Test
    public void shouldUpdateUserName_WhenCallingUserServiceUpdate() throws InvalidRequestException {
        String originalName = "Tony Stark";
        String expected = "IronMan";

        userService.createUser(originalName);
        userService.updateUserNameByID(2, expected);
        String actual = userService.ReadByID(2).getName();
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = InvalidRequestException.class)
    public void shouldThrowException_WhenCallingUpdateByIDWithWrongID() throws InvalidRequestException {
        userService.updateUserNameByID(2, "Steve");
    }

}


