package DB;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HashMapDBTest {
    HashMapDB database = new HashMapDB();
    Person userTest;

    @Before
    public void setUp() throws Exception {
        userTest = new Person("Barry");
    }

    @After
    public void tearDown() {
        database = null;
    }

    @Test
    public void whenDatabaseIsConstructedMyNameIsAdded() {
        Person myself = new Person("Eathan");
        Assert.assertEquals(myself, database.getUserByID(1));
    }

    @Test
    public void addUserPutsPersonToDB() {
        database.addUser(userTest);
        Assert.assertEquals(userTest, database.getUserByID(2));
    }
}