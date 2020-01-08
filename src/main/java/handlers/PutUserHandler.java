package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.User;
import db.UserDB;
import utilities.DBValidator;
import utilities.HttpUtils;
import utilities.StatusCodes;

import java.io.IOException;
import java.util.ResourceBundle;

public class PutUserHandler implements HttpHandler {
    private UserDB DB;
    private ResourceBundle outputMessages;

    public PutUserHandler(UserDB database, ResourceBundle outputMessages) {
        this.DB = database;
        this.outputMessages = outputMessages;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/html");
        String path = exchange.getRequestURI().getPath();
        calculatePutResponse(exchange, path);
    }

    private void calculatePutResponse(HttpExchange exchange, String path) throws IOException {
        if (DBValidator.isValidUserRequest(path, DB)) {
            Integer query = HttpUtils.getIdFromPath(path);
            User queryUser = DB.getUserByID(query);
            String newUserName = HttpUtils.getRequestFromBody(exchange.getRequestBody()).replace(" ","").split(",")[1];
            queryUser.setName(newUserName.toLowerCase());

            HttpUtils.writeResponse(exchange, outputMessages.getString("success_put_user"), StatusCodes.OK.getCode());
        } else {
            HttpUtils.writeResponse(exchange, outputMessages.getString("error_put_user"), StatusCodes.BAD_REQUEST.getCode());
        }
    }
}
