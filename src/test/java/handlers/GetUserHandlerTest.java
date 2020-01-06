package handlers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.Server;
import utilities.Constants;

import static org.hamcrest.CoreMatchers.equalTo;

public class GetUserHandlerTest {
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
    public void shouldGetUser_WhenGivenID() {
        RequestSpecification request = RestAssured.given();
        request.body("Barry");
        request.post(Constants.local_address+Constants.port+Constants.users_endpoint);

        request.get(Constants.local_address+Constants.port+Constants.users_endpoint+"/2")
                .then()
                .body(equalTo("barry"));
    }

    @Test
    public void shouldReturnErrorBody_WhenGivenWrongID() {
        RequestSpecification request = RestAssured.given();
        request.get(Constants.local_address+Constants.port+Constants.users_endpoint+"/2")
                .then()
                .body(equalTo(Constants.error_getting_user));
    }

    @Test
    public void shouldReturnAllUsers_WhenHittingUsersEndpoint() {
        RequestSpecification request = RestAssured.given();
        request.body("Harry");
        request.post(Constants.local_address+Constants.port+Constants.users_endpoint);

        request.get(Constants.local_address+Constants.port+Constants.users_endpoint)
                .then()
                .body(equalTo("[" +"1," + Constants.admin_name +", "+ "2,harry" + "]"));
    }
}
