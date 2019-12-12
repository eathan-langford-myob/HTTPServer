package handlers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.Server;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;

public class PostHandlerTest {
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
        public void postEndpointAddsToData() {
            RequestSpecification request = RestAssured.given();
            request.body("Barry");
            request.post("http://localhost:4000/post");
            when().
                    get("http://localhost:4000/").
                    then().
                    statusCode(200).
                    body(containsString("Barry"));

        }
    }
