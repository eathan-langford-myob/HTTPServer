package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.User;
import db.UserDB;
import utilities.*;

import java.io.IOException;
import java.util.ResourceBundle;

public class GetUserHandler implements HttpHandler {
    private UserDB DB;
    private ResourceBundle outputMessages;

    public GetUserHandler(UserDB database, ResourceBundle outputMessages) {
        this.DB = database;
        this.outputMessages = outputMessages;
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
            HttpUtils.writeResponse(exchange, DB.getAllDbEntries().toString(), StatusCodes.OK.getCode());

        } else if (HttpRequestValidator.isIdEndpoint(path, Constants.users_endpoint) && DBValidator.isUserInDatabase(DB, HttpUtils.getIdFromPath(path))) {
            Integer query = HttpUtils.getIdFromPath(path);
            User queryUser = DB.getUserByID(query);
            HttpUtils.writeResponse(exchange, queryUser.getName(), StatusCodes.OK.getCode());
        } else {
            HttpUtils.writeResponse(exchange, outputMessages.getString("error_getting_user"), StatusCodes.BAD_REQUEST.getCode());
        }
    }
}
