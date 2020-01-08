
package handlers;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class GetUserHandlerTest {
    private TestSetup testing = new TestSetup();


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
                .body(equalTo("barry"));
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
                .body(equalTo("[" +"1," + testing.admin.getName() +", "+ "2,harry" + "]"));
    }
}

