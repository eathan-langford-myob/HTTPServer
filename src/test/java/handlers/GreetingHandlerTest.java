package handlers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.Server;
import utilities.Constants;

import java.util.Date;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

public class GreetingHandlerTest {
    private Server server;
    @Before
    public void setUp() throws Exception {
        server = new Server();
        server.createServerConnection();
    }

    @After
    public void tearDown() {
        server.closeServerConnection();
    }


    @Test
    public void shouldDisplayGreeting_WhenHittingRootEndpoint() {
        when().
                get(Constants.local_address+Constants.port).
                then().
                statusCode(200).
                body(containsString("the time on the server is - " + new Date()));
    }

    @Test
    public void shouldDisplayAdminInGreeting_WhenHittingRootEndpoint() {
        when().
                get(Constants.local_address+Constants.port).
                then().
                statusCode(200).
                body(containsString(Constants.admin_name));
    }

    @Test
    public void shouldDisplayAllUsersInGreeting_WhenDBHasMoreUsers() {
        RequestSpecification request = RestAssured.given();
        request.body("Larry");
        request.post(Constants.local_address+Constants.port+Constants.users_endpoint);

        when().
                get(Constants.local_address+Constants.port).
                then().
                statusCode(200).
                body(containsString(Constants.admin_name+" and Larry,"));
    }
}