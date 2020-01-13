package controllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.UserDB;
import utilities.StatusCodes;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import static utilities.UserStringFormatter.formatUsersToString;

public class GreetingController implements HttpHandler {
    private UserDB DB;
    private ResourceBundle outputMessages;

    public GreetingController(UserDB database, ResourceBundle outputMessages) {
        this.DB = database;
        this.outputMessages = outputMessages;
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
        String response = outputMessages.getString("hello") + users + outputMessages.getString("time") + new Date().toString();        exchange.sendResponseHeaders(StatusCodes.OK.getCode(), response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
