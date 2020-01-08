
package utilities;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class UserStringFormatterTest {

    @Test
    public void shouldFormatNamesWithAppropriateSeparators_WhenGivenNamesFromDB() {
        ArrayList<String> collectionOfNamesFromDB = new ArrayList<String>() {{
            add("eathan");
            add("barry");
            add("oliver");
        }};

        String expected = "Eathan, Barry and Oliver, ";
        String actual = UserStringFormatter.formatUsersToString(collectionOfNamesFromDB);

        Assert.assertEquals(expected, actual);
    }
}
