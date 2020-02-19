package domain;

import db.User;
import db.UserDB;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utilities.InvalidRequestException;

import java.util.ResourceBundle;

public class UserServiceTest {
    private UserService userService;

//TODO remove server logic from tests, test method behaviour

    @Before
    public void setUp() {
        UserDB database = new UserDB();
        User adminUser = new User("Eathan", true);
        database.addUser(adminUser);
        ResourceBundle outputMessages = ResourceBundle.getBundle("OutputMessages");
        userService = new UserService(database, outputMessages);
    }

    @After
    public void tearDown() {
    }


    @Test
    public void shouldDisplayAllUsers_WhenCallingUserServiceRead() {
        String actual = userService.readAll();
        String expected = "[" + "1,Eathan" + "]";

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
    public void shouldReturnTrue_WhenUserExistsInDB() throws InvalidRequestException {
        String expected = "Batman";
            userService.createUser(expected);
            Assert.assertTrue(userService.isUserInDB(2));
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


