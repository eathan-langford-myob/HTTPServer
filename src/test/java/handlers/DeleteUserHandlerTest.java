package handlers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteUserHandlerTest {
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
    public void shouldDisplaySuccessMessage_WhenDeletingWithID() {
        RequestSpecification request = RestAssured.given();
        request.body("Barry");
        request.post(testing.usersPath);
        request.delete(testing.usersPath + "/2")
                .then()
                .body(equalTo(testing.outputMessages.getString("success_delete_user")));
    }

    @Test
    public void shouldDeleteFromDB_WhenGivenID() {
        String usersRequestPath = "http://localhost:4000/users/2";
        RequestSpecification request = RestAssured.given();
        request.body("Barry");
        request.post(testing.usersPath);
        request.delete(usersRequestPath);

        request.get(usersRequestPath)
                .then()
                .body(equalTo(testing.outputMessages.getString("error_getting_user")));
    }

    @Test
    public void shouldDisplayError_WhenGivenWrongID() {
        RequestSpecification request = RestAssured.given();
        request.delete(testing.usersPath + "/2")
                .then()
                .body(equalTo(testing.outputMessages.getString("error_delete_user")));
    }
}


