package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.UserDB;
import utilities.Constants;
import utilities.HttpRequestValidator;
import utilities.HttpUtils;

import java.io.IOException;

public class DeleteUserHandler implements HttpHandler {
    UserDB DB;

    public DeleteUserHandler(UserDB database) {
        this.DB = database;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Put handler method");
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/html");
        String path = exchange.getRequestURI().getPath();
        calculatePostResponse(exchange, path);
    }

    public void calculatePostResponse(HttpExchange exchange, String path) throws IOException {
        if (HttpRequestValidator.isValidUserRequest(path, DB)) {
                long ID = HttpUtils.getIdFromPath(path);
                DB.deleteUserByID(ID);
                HttpUtils.writeResponse(exchange, Constants.success_delete_user);
        } else {
            HttpUtils.writeResponse(exchange, Constants.error_delete_user);
        }
    }
}
