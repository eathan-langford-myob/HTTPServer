package handlers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.Server;
import utilities.Constants;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

public class PutUserHandlerTest {
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
    public void putEndpointUpdatesUserName() {
        RequestSpecification request = RestAssured.given();
        request.body("Barry");
        request.post(Constants.local_address+Constants.port+Constants.users_endpoint);

        request.body("Barry, Larry");
        request.put(Constants.local_address+Constants.port+Constants.users_endpoint+"/2");

        when().
                get(Constants.local_address+Constants.port+Constants.users_endpoint).
                then().
                statusCode(200).
                body(containsString("larry"));
    }

    @Test
    public void putEndpointShouldFailWithWrongID() {
        RequestSpecification request = RestAssured.given();
        request.body("Barry, Larry");

        when().
                put(Constants.local_address+Constants.port+Constants.users_endpoint+"/2").
                then().
                statusCode(200).
                body(containsString(Constants.error_put_user));
    }
}
