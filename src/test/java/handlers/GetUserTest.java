
package handlers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.ServerHelper;

import static org.hamcrest.CoreMatchers.equalTo;

public class GetUserTest {
    private final ServerHelper testing = new ServerHelper();


    @Before
    public void setUp() throws Exception {
        testing.setup();
    }

    @After
    public void tearDown() {
        testing.tearDown();
    }



    @Test
    public void shouldGetUser_WhenGivenID() {
        RequestSpecification request = RestAssured.given();
        request.body("Barry");
        request.post(testing.usersPath);

        request.get(testing.usersPath+"/2")
                .then()
                .body(equalTo("Barry"));
    }

    @Test
    public void shouldGet405Error_WhenDoingUnsupportedRequest() {
        RequestSpecification request = RestAssured.given();
        request.body("Steve");
        request.post(testing.usersPath+"/4")
                .then()
                .statusCode(405)
                .body(equalTo(testing.outputMessages.getString("request_error")));
    }

    @Test
    public void shouldReturnErrorBody_WhenGivenWrongID() {
        RequestSpecification request = RestAssured.given();
        request.get(testing.usersPath+"/2")
                .then()
                .body(equalTo(testing.outputMessages.getString("error_getting_user")));
    }

    @Test
    public void shouldReturnAllUsers_WhenHittingUsersEndpoint() {
        RequestSpecification request = RestAssured.given();
        request.body("Harry");
        request.post(testing.usersPath);

        request.get(testing.usersPath)
                .then()
                .body(equalTo("[" +"1," + testing.admin.getName() +", "+ "2,Harry" + "]"));
    }
}

