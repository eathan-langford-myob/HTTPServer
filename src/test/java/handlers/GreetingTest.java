

package handlers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.ServerHelper;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

public class GreetingTest {
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
    public void shouldDisplayGreeting_WhenHittingRootEndpoint() {
        when().
                get(testing.localPath).
                then().
                statusCode(200).
                body(containsString(testing.outputMessages.getString("hello")));
    }

    @Test
    public void shouldDisplayAdminInGreeting_WhenHittingRootEndpoint() {
        when().
                get(testing.localPath).
                then().
                statusCode(200).
                body(containsString(testing.admin.getName()));
    }

    @Test
    public void shouldDisplayAllUsersInGreeting_WhenDBHasMoreUsers() {
        RequestSpecification request = RestAssured.given();
        request.body("Larry");
        request.post(testing.usersPath);

        when().
                get(testing.localPath).
                then().
                statusCode(200).
                body(containsString(testing.admin.getName() + " and Larry,"));
    }

    @Test
    public void shouldThrowInvalidRequest_WhenGreetingEndpointIsIncorrect() {
        when().
                get(testing.localPath + "GreetMePlease").
                then().
                statusCode(404).
                body(containsString(testing.outputMessages.getString("path_error")));
    }
}
