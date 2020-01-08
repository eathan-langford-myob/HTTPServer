
package handlers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

public class PutUserHandlerTest {
    private TestServer testing = new TestServer();


    @Before
    public void setUp() throws Exception {
        testing.setup();
    }

    @After
    public void tearDown() {
        testing.tearDown();
    }




    @Test
    public void putEndpointUpdatesUserName() {
        RequestSpecification request = RestAssured.given();
        request.body("Barry");
        request.post(testing.usersPath);

        request.body("Barry, Larry");
        request.put(testing.usersPath +"/2");

        when().
                get(testing.usersPath).
                then().
                statusCode(200).
                body(containsString("larry"));
    }

    @Test
    public void putEndpointShouldFailWithWrongID() {
        RequestSpecification request = RestAssured.given();
        request.body("Barry, Larry");

        when().
                put(testing.usersPath +"/2").
                then().
                statusCode(400).
                body(containsString(testing.outputMessages.getString("error_put_user")));
    }
}
