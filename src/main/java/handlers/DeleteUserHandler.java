package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.UserDB;
import utilities.DBValidator;
import utilities.HttpUtils;

import java.io.IOException;

public class DeleteUserHandler implements HttpHandler {
    private UserDB DB;
    private String success_delete_user = "User deleted successfully.";
    private String error_delete_user = "Error deleting User.";

    public DeleteUserHandler(UserDB database) {
        this.DB = database;
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
                HttpUtils.writeResponse(exchange, success_delete_user);
        } else {
            HttpUtils.writeResponse(exchange, error_delete_user);
        }
    }
}
