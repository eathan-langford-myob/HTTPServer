package handlers;

import DB.HashMapDB;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class PostHandler implements HttpHandler {
HashMapDB DB;

    public PostHandler(HashMapDB database) {
    this.DB = database;
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        Headers responseHeaders = t.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/html");
        Scanner s = new Scanner(t.getRequestBody()).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
