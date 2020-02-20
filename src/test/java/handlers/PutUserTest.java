
package handlers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.ServerHelper;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

public class PutUserTest {
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
    public void putEndpointUpdatesUserName() {
        RequestSpecification request = RestAssured.given();
        request.body("Barry");
        request.post(testing.usersPath);

        request.body("Barry, Larry");
        request.put(testing.usersPath + "/2");

        when().
                get(testing.usersPath).
                then().
                statusCode(200).
                body(containsString("Larry"));
    }

    @Test
    public void shouldDisplayErrorMessage_WhenTryingToUpdateAdminUser() {
        RequestSpecification request = RestAssured.given();

        request.body("Eathan, Larry");
        request.put(testing.usersPath + "/1")
                .then()
                .statusCode(400)
                .body(containsString(testing.outputMessages.getString("error_put_user")));
    }

    @Test
    public void putEndpointShouldFailWithWrongID() {
        RequestSpecification request = RestAssured.given();
        request.post("Barry");

        request.body("Barry, Larry");
        request.
                put(testing.usersPath + "/2").
                then().
                statusCode(400).
                body(containsString(testing.outputMessages.getString("error_put_user")));
    }
}
