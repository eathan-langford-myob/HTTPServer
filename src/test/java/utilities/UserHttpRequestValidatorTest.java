

package utilities;

import org.junit.Assert;
import org.junit.Test;

public class UserHttpRequestValidatorTest {

    @Test
    public void shouldReturnTrue_WhenAddressEndsWithValidIDRequest() {
        String urlRequest = "/users/1";
        Assert.assertTrue(UserHttpRequestValidator.isValidIdRequest(urlRequest));
    }

    @Test
    public void shouldReturnFalse_WhenAddressEndsWithInvalidIDRequest() {
        String urlRequest = "/users/Q";
        Assert.assertFalse(UserHttpRequestValidator.isValidIdRequest(urlRequest));
    }

    @Test
    public void shouldReturnTrue_WhenRequestAddressIsAllUsers() {
        String urlRequest = "/users";
        Assert.assertTrue(UserHttpRequestValidator.isAllUsersEndpoint(urlRequest));
    }
}
