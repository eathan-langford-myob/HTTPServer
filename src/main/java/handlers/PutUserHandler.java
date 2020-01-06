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

public class PutUserHandler implements HttpHandler {
    private UserDB DB;

    public PutUserHandler(UserDB database) {
        this.DB = database;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("GetUser handler method");
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/html");
        String path = exchange.getRequestURI().getPath();
        calculatePutResponse(exchange, path);
    }

    private void calculatePutResponse(HttpExchange exchange, String path) throws IOException {
        if (HttpRequestValidator.isValidUserRequest(path, DB)) {
            Integer query = HttpUtils.getIdFromPath(path);
            User queryUser = DB.getUserByID(query);
            String newUserName = HttpUtils.getRequestFromBody(exchange.getRequestBody()).replace(" ","").split(",")[1];
            queryUser.setName(newUserName.toLowerCase());

            HttpUtils.writeResponse(exchange, Constants.success_put_user);
        } else {
            HttpUtils.writeResponse(exchange, Constants.error_put_user);
        }
    }
}
