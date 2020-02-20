package server;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class SimpleHttpResponse {
    private HttpExchange exchange;

    public SimpleHttpResponse(SimpleHttpRequest request) {
        this.exchange = request.getExchange();
    }

    public void write ( int statusCode, String response) throws IOException {
        this.exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = this.exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
