package handlers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.Server;
import utilities.Constants;

import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteUserHandlerTest {
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
    public void shouldDisplaySuccessMessage_WhenDeletingWithID() {
        RequestSpecification request = RestAssured.given();
        request.body("Barry");
        request.post(Constants.local_address+Constants.port+Constants.users_endpoint);
        request.delete(Constants.local_address+Constants.port+Constants.users_endpoint+"/2")
                .then()
                .body(equalTo(Constants.success_delete_user));
    }

    @Test
    public void shouldDeleteFromDB_WhenGivenID() {
        RequestSpecification request = RestAssured.given();
        request.body("Barry");
        request.post(Constants.local_address+Constants.port+Constants.users_endpoint);
        request.delete(Constants.local_address+Constants.port+Constants.users_endpoint+"/2");

        request.get(Constants.local_address+Constants.port+Constants.users_endpoint+"/2")
                .then()
                .body(equalTo(.error_getting_user));
    }

    @Test
    public void shouldDisplayError_WhenGivenWrongID() {
        RequestSpecification request = RestAssured.given();
        request.delete(Constants.local_address+Constants.port+Constants.users_endpoint+"/2")
                .then()
                .body(equalTo(Constants.error_delete_user));
    }
}
