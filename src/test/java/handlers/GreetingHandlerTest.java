package handlers;

import db.User;
import db.UserDB;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.Server;

import java.util.Date;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

public class GreetingHandlerTest {
    private Server server;
    private User admin = new User("Eathan");
    private UserDB DB;
    private String localPath = System.getenv("LOCAL_ADDRESS") + System.getenv("PORT");
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
    public void shouldDisplayGreeting_WhenHittingRootEndpoint() {
        when().
                get(localPath).
                then().
                statusCode(200).
                body(containsString("the time on the server is - " + new Date()));
    }

    @Test
    public void shouldDisplayAdminInGreeting_WhenHittingRootEndpoint() {
        when().
                get(localPath).
                then().
                statusCode(200).
                body(containsString(admin.getName()));
    }

    @Test
    public void shouldDisplayAllUsersInGreeting_WhenDBHasMoreUsers() {
        RequestSpecification request = RestAssured.given();
        request.body("Larry");
        request.post(usersPath);

        when().
                get(localPath).
                then().
                statusCode(200).
                body(containsString(admin.getName()+" and Larry,"));
    }
}