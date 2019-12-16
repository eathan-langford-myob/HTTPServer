package handlers;

import DB.HashMapDB;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class PutHandler implements HttpHandler {
    private HashMapDB DB;

    public PutHandler(HashMapDB database) {
        this.DB = database;
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        Headers responseHeaders = t.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/html");
        Scanner response = new Scanner(t.getRequestBody()).useDelimiter("\\A");
        String userName = response.hasNext() ? response.next() : "";
        if (!userName.isEmpty()) {
            String[] queryNames = userName.replace(" ", "").split(",");
            DB.updateUserName(queryNames[0],queryNames[1]);
            t.sendResponseHeaders(200, "User Updated Successfully.".length());
            OutputStream os = t.getResponseBody();
            os.write("User Updated Successfully.".getBytes());
            os.close();
        }
        else {
            t.sendResponseHeaders(200, "Something went wrong updating User".length());
            OutputStream os = t.getResponseBody();
            os.write("Something went wrong updating User".getBytes());
            os.close();
        }
    }
}
