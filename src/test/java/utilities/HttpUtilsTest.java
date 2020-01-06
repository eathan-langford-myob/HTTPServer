package utilities;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.Server;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

public class HttpUtilsTest {
    private Server server;

    @Before
    public void setUp() throws Exception {
        server = new Server();
        server.createServerConnection();
    }

    @After
    public void tearDown() {
        server.closeServerConnection();
    }


    @Test
    public void shouldExtractIDFromPath_WhenGivenStringOfPathh() {
        String path = "localhost:4000/users/3";
        int ID = HttpUtils.getIdFromPath(path);
        Assert.assertEquals(ID, 3);
    }

    @Test
    public void shouldGetRequestFromBody_WhenGivenHTTPExchange() {
        InputStream request = new ByteArrayInputStream("Barry".getBytes(Charset.forName("UTF-8")));
        String requestBodyActual = HttpUtils.getRequestFromBody(request);
        Assert.assertEquals(requestBodyActual, "Barry");
    }
}