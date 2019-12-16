package handlers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import server.Server;

import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteHandlerTest {
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
    public void deleteRemovesUserFromDatabase() {
        RequestSpecification request = RestAssured.given();
        request.body("Barry");
        request.post("http://localhost:4000/post");
        request.delete("http://localhost:4000/delete").then().body(equalTo("User Deleted Successfully."));
    }
}
