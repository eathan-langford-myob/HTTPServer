package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.UserDB;
import utilities.DBValidator;
import utilities.HttpUtils;
import utilities.StatusCodes;

import java.io.IOException;
import java.util.ResourceBundle;

public class DeleteUserHandler implements HttpHandler {
    private UserDB DB;
    private ResourceBundle outputMessages;

    public DeleteUserHandler(UserDB database, ResourceBundle outputMessages) {
        this.DB = database;
        this.outputMessages = outputMessages;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/html");
        String path = exchange.getRequestURI().getPath();
        calculatePostResponse(exchange, path);
    }

    public void calculatePostResponse(HttpExchange exchange, String path) throws IOException {
        if (DBValidator.isValidUserRequest(path, DB)) {
            long ID = HttpUtils.getIdFromPath(path);
            DB.deleteUserByID(ID);
            HttpUtils.writeResponse(exchange, outputMessages.getString("success_delete_user"), StatusCodes.OK.getCode());
        } else {
            HttpUtils.writeResponse(exchange, outputMessages.getString("error_delete_user"), StatusCodes.BAD_REQUEST.getCode());
        }
    }
}
