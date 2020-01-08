

package utilities;

import org.junit.Assert;
import org.junit.Test;

public class HttpRequestValidatorTest {

    @Test
    public void shouldReturnTrue_WhenAddressEndsWithValidIDRequest() {
        String urlRequest = "/users/1";
        Assert.assertTrue(HttpRequestValidator.isValidIdRequest(urlRequest));
    }

    @Test
    public void shouldReturnFalse_WhenAddressEndsWithInvalidIDRequest() {
        String urlRequest = "/users/Q";
        Assert.assertFalse(HttpRequestValidator.isValidIdRequest(urlRequest));
    }

    @Test
    public void shouldReturnTrue_WhenRequestAddressIsAllUsers() {
        String urlRequest = "/users";
        Assert.assertTrue(HttpRequestValidator.isAllUsersEndpoint(urlRequest));
    }
}
