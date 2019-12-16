package handlers;

import DB.HashMapDB;
import DB.Person;
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
        Scanner response = new Scanner(t.getRequestBody()).useDelimiter("\\A");
        String userName = response.hasNext() ? response.next() : "";
        if (!userName.isEmpty()) {
            Person newUser = new Person(userName.toLowerCase());
            DB.addUser(newUser);
            t.sendResponseHeaders(200, "User Added Successfully.".length());
            OutputStream os = t.getResponseBody();
            os.write("User Added Successfully.".getBytes());
            os.close();
        }
        else {
            t.sendResponseHeaders(200, "Something went wrong adding User".length());
            OutputStream os = t.getResponseBody();
            os.write("Something went wrong adding User".getBytes());
            os.close();
        }
    }
}
