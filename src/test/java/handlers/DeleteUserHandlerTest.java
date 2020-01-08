package handlers;

import db.User;
import db.UserDB;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.Server;

import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteUserHandlerTest {
    private Server server;
    private User admin = new User("Eathan");
    private UserDB DB;
//    private String usersPath = System.getenv("LOCAL_ADDRESS") + System.getenv("PORT") + System.getenv("ROOT_ADDRESS") + System.getenv("USERS_ENDPOINT");
private String usersPath = "http://localhost:4000/Users";

    @Before
    public void setUp() throws Exception {
        DB = new UserDB();
        DB.addUser(admin);
        server = new Server(DB);
        server.createServerConnection();
    }

    @After
    public void tearDown() {
        server.closeServerConnection();
    }



    @Test
    public void shouldDisplaySuccessMessage_WhenDeletingWithID() {
        RequestSpecification request = RestAssured.given();
        request.body("Barry");
        request.post(usersPath);
        request.delete(usersPath+"/2")
                .then()
                .body(equalTo("User deleted successfully."));
    }

    @Test
    public void shouldDeleteFromDB_WhenGivenID() {
        RequestSpecification request = RestAssured.given();
        request.body("Barry");
        request.post(usersPath);
        request.delete(usersPath+"/2");

        request.get(usersPath+"/2")
                .then()
                .body(equalTo("Something went wrong getting User"));
    }

    @Test
    public void shouldDisplayError_WhenGivenWrongID() {
        RequestSpecification request = RestAssured.given();
        request.delete(usersPath+"/2")
                .then()
                .body(equalTo("Error deleting User."));
    }
}
