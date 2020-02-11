
package handlers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.TestServer;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

public class PostUserTest {
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
    public void postEndpointAddsToData() {
        RequestSpecification request = RestAssured.given();
        request.body("Barry");
        request.post(testing.usersPath);
        when().
                get(testing.usersPath).
                then().
                statusCode(200).
                body(containsString("Barry"));

    }
}
