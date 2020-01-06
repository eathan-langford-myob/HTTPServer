package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.User;
import db.UserDB;
import utilities.Constants;
import utilities.HttpRequestValidator;
import utilities.HttpUtils;

import java.io.IOException;

public class PostUserHandler implements HttpHandler {
    private UserDB DB;

    public PostUserHandler(UserDB database) {
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

    private void calculatePostResponse(HttpExchange exchange, String path) throws IOException {
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/html");
        String queryID = HttpUtils.getRequestFromBody(exchange.getRequestBody());

        if (!queryID.isEmpty() && HttpRequestValidator.isAllUsersEndpoint(path)) {
            User newUser = new User(queryID.toLowerCase());
            DB.addUser(newUser);
            HttpUtils.writeResponse(exchange, Constants.success_post_user);
        }
        else {
            HttpUtils.writeResponse(exchange, Constants.error_post_user);
        }
    }
}