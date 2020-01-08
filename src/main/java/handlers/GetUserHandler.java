package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.User;
import db.UserDB;
import utilities.Constants;
import utilities.DBValidator;
import utilities.HttpRequestValidator;
import utilities.HttpUtils;

import java.io.IOException;

public class GetUserHandler implements HttpHandler {
    private UserDB DB;
    public static final String error_getting_user = "Something went wrong getting User";

    public GetUserHandler(UserDB database) {
        this.DB = database;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/html");
        String path = exchange.getRequestURI().getPath();
        calculateGetResponse(exchange, path);
    }

    private void calculateGetResponse(HttpExchange exchange, String path) throws IOException {
        if (HttpRequestValidator.isAllUsersEndpoint(path)) {
            HttpUtils.writeResponse(exchange, DB.getAllDbEntries().toString());

        } else if (HttpRequestValidator.isIdEndpoint(path, Constants.users_endpoint) && DBValidator.isUserInDatabase(DB, HttpUtils.getIdFromPath(path))) {
            Integer query = HttpUtils.getIdFromPath(path);
            User queryUser = DB.getUserByID(query);
            HttpUtils.writeResponse(exchange, queryUser.getName());
        } else {
            HttpUtils.writeResponse(exchange, error_getting_user);
        }
    }
}
