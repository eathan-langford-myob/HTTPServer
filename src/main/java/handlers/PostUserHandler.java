package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.User;
import db.UserDB;
import utilities.HttpRequestValidator;
import utilities.HttpUtils;
import utilities.StatusCodes;

import java.io.IOException;
import java.util.ResourceBundle;

public class PostUserHandler implements HttpHandler {
    private UserDB DB;
    private ResourceBundle outputMessages;

    public PostUserHandler(UserDB database, ResourceBundle outputMessages) {
        this.DB = database;
        this.outputMessages = outputMessages;
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
            HttpUtils.writeResponse(exchange, outputMessages.getString("success_post_user"), StatusCodes.OK.getCode());
        }
        else {
            HttpUtils.writeResponse(exchange, outputMessages.getString("error_post_user"), StatusCodes.BAD_REQUEST.getCode());
        }
    }
}