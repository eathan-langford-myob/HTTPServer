package db;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class UserDBTest {

    private UserDB database = new UserDB();

    @Before
    public void setUp() {
        User adminUser = new User("Eathan");
        database.addUser(adminUser);
    }

    @After
    public void tearDown() {
        database = null;
    }


    @Test
    public void shouldAddToDB_WhenAddingUser() {
        User actual = new User("Barry");
        database.addUser(actual);
        Assert.assertEquals(actual, database.getUserByID(2));
    }

    @Test
    public void shouldReturnUser_WhenRetrievingByID() {
       User actual = database.getUserByID(1);
       User expected = new User("Eathan");
        Assert.assertEquals(actual.getName(), expected.getName());
    }

    @Test
    public void shouldReturnAllUsersNames() {
        ArrayList<String> actual = database.getAllUsersNames();
        ArrayList<String> expected = new ArrayList<>(
                Arrays.asList("Eathan"));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAllEntriesInDB_WhenMoreThanOneUserExists() {
        database.addUser(new User("Barry"));

        ArrayList<String> actual = database.getAllDbEntries();
        ArrayList<String> expected = new ArrayList<>(
                Arrays.asList("1,Eathan", "2,Barry"));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldDeleteUser_WhenGivenID() {
        database.addUser(new User("Oliver"));
        database.deleteUserByID(2);

        Assert.assertFalse(database.getAllDbEntries().contains("Oliver"));
    }
}