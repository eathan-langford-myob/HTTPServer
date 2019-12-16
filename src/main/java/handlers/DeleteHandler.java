package handlers;

import DB.HashMapDB;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import utilities.RequestValidator;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class DeleteHandler implements HttpHandler {
    HashMapDB DB;

    public DeleteHandler(HashMapDB database) {
        this.DB = database;
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        Headers responseHeaders = t.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/html");
        Scanner response = new Scanner(t.getRequestBody()).useDelimiter("\\A");
        String queryName = response.hasNext() ? response.next().toLowerCase() : "";

        if (RequestValidator.isValidStringResponse(queryName, DB)) {
            long ID = DB.getUserID(queryName);
            DB.deleteUserByID(ID);
            t.sendResponseHeaders(200, "User Deleted Successfully.".length());
            OutputStream os = t.getResponseBody();
            os.write(("User Deleted Successfully.").getBytes());
            os.close();
        } else {
            t.sendResponseHeaders(200, "Something went wrong deleting User".length());
            OutputStream os = t.getResponseBody();
            os.write(("Something went wrong deleting User").getBytes());
            os.close();
        }
    }
}
