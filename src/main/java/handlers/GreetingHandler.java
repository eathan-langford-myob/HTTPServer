package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.UserDB;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import static utilities.UserStringFormatter.formatUsersToString;

public class GreetingHandler implements HttpHandler {
    private UserDB DB;
    private final String hello = "Hello ";
    private final String time = "the time on the server is - ";

    public GreetingHandler(UserDB database) {
        this.DB = database;
    }

    private String collectNames() {
        ArrayList<String> collectionOfNamesFromDB = DB.getAllUsersNames();
        return formatUsersToString(collectionOfNamesFromDB);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/plain");
        String users = collectNames();
        String response = hello + users + time + new Date().toString();
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
