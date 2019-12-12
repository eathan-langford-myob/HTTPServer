package server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

public class RootHandlerTest {
    private Server server;
    @Before
    public void setUp() throws Exception {
        server = new Server();
        server.createServerConnection();
    }

    @After
    public void tearDown() {
        server = null;
    }

    @Test
    public void rootEndpointDisplaysWelcomeMessageWithTime() {
        when().
                get("http://localhost:4000/").
                then().
                statusCode(200).
                body(containsString("the time on the server is - " + new Date()));
    }

    @Test
    public void rootEndpointDisplaysFirstPersonInDatabaseInGreeting() {
        when().
                get("http://localhost:4000/").
                then().
                statusCode(200).
                body(containsString("Hello Eathan"));
    }
}