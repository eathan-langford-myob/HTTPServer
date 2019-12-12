package handlers;

import DB.HashMapDB;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

public class RootHandler implements HttpHandler {
    private HashMapDB DB;

    public RootHandler(HashMapDB database) {
        this.DB = database;
    }

    private String collectNames() {
        ArrayList<String> collectionOfNamesFromDB = DB.getAllUsersNames();
        StringBuilder collectNamesForGreeting = new StringBuilder();

        for (String userName : collectionOfNamesFromDB) {
            collectNamesForGreeting.append(userName);
            if (collectionOfNamesFromDB.size() >1 && collectionOfNamesFromDB.get(collectionOfNamesFromDB.size() - 1).equals(userName)) {
                collectNamesForGreeting.append(" and ");
            } else {
                collectNamesForGreeting.append(", ");
            }
        }
        return collectNamesForGreeting.toString();
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        Headers responseHeaders = t.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/plain");
        String users = collectNames();
        Date dateOfToday = new Date();
        String response = "Hello " + users + "the time on the server is - " + dateOfToday.toString();
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
