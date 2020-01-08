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

public class GetUserHandlerTest {
    private Server server;
    private User admin = new User("Eathan");
    private UserDB DB;
    private String usersPath = System.getenv("LOCAL_ADDRESS") + System.getenv("PORT") + System.getenv("ROOT_ADDRESS") + System.getenv("USERS_ENDPOINT");

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
    public void shouldGetUser_WhenGivenID() {
        RequestSpecification request = RestAssured.given();
        request.body("Barry");
        request.post(usersPath);

        request.get(usersPath+"/2")
                .then()
                .body(equalTo("barry"));
    }

    @Test
    public void shouldReturnErrorBody_WhenGivenWrongID() {
        RequestSpecification request = RestAssured.given();
        request.get(usersPath+"/2")
                .then()
                .body(equalTo("Something went wrong getting User"));
    }

    @Test
    public void shouldReturnAllUsers_WhenHittingUsersEndpoint() {
        RequestSpecification request = RestAssured.given();
        request.body("Harry");
        request.post(usersPath);

        request.get(usersPath)
                .then()
                .body(equalTo("[" +"1," + admin.getName() +", "+ "2,harry" + "]"));
    }
}
