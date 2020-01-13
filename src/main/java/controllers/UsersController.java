package controllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.User;
import db.UserDB;
import domain.UserService;
import utilities.HttpRequestValidator;
import utilities.HttpUtils;
import utilities.StatusCodes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ResourceBundle;

public class UsersController implements HttpHandler {
    private ResourceBundle outputMessages;
    private UserService userService;
    private String requestPath;
    private int IDFromPath;
    private String nameFromRequest;


    public UsersController(UserDB database, ResourceBundle outputMessages) {
        this.userService = new UserService(database);
        this.outputMessages = outputMessages;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        requestPath = exchange.getRequestURI().getPath();
        if (requestPath.equalsIgnoreCase("/users") || requestPath.equalsIgnoreCase("/users/")) {
            handleAllUsers(exchange);
        } else if (requestPath.matches("/users/" + "\\d+")) {
            handleByID(exchange);
        }
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/plain");
    }

    private void handleByID(HttpExchange exchange) throws IOException {
        extractIDFromPath();
        String requestMethod = exchange.getRequestMethod();
        if (HttpRequestValidator.isValidIdRequest(requestPath)) {
            switch (requestMethod) {
                case "GET":
                    requestUserByIdHandler(exchange);
                    break;
                case "DELETE":
                    deleteUserHandler(exchange);
                    break;
                case "PUT":
                    updateUserHandler(exchange);
                    break;
                default:
                    writeUnsuccessfulResponse(exchange, outputMessages.getString("request_error"), StatusCodes.NOT_ACCEPTED.getCode());
                    break;
            }
        } else {
            writeUnsuccessfulResponse(exchange, outputMessages.getString("path_error"), StatusCodes.BAD_REQUEST.getCode());
        }
    }

    private void updateUserHandler(HttpExchange exchange) throws IOException {
        extractNameFromBody(exchange.getRequestBody());
        extractIDFromPath();
        if (!nameFromRequest.isEmpty()
                && HttpRequestValidator.isValidUpdateRequest(nameFromRequest)
                && userService.isSameName(IDFromPath, nameFromRequest)
                && !userService.isUserAdmin(IDFromPath)) {
            String newName = nameFromRequest.split(",")[1].trim();
            userService.updateUserNameByID(IDFromPath, newName);
            writeSuccessfulResponse(exchange, outputMessages.getString("success_put_user"));
        } else {
            writeUnsuccessfulResponse(exchange, outputMessages.getString("error_put_user"), StatusCodes.BAD_REQUEST.getCode());

        }
    }

    private void deleteUserHandler(HttpExchange exchange) throws IOException {
        if (userService.isUserInDB(IDFromPath)) {
            userService.removeUserByID(IDFromPath);
            writeSuccessfulResponse(exchange, outputMessages.getString("success_delete_user"));
        } else {
            writeUnsuccessfulResponse(exchange, outputMessages.getString("error_delete_user"), StatusCodes.NOT_FOUND.getCode());
        }
    }

    private void extractIDFromPath() {
        IDFromPath = HttpUtils.getIdFromPath(requestPath);
    }

    private void extractNameFromBody(InputStream requestBody) {
        nameFromRequest = HttpUtils.getRequestFromBody(requestBody);
    }

    private User getValidSingleUserByID() {
        return userService.ReadByID(IDFromPath);
    }

    private void requestUserByIdHandler(HttpExchange exchange) throws IOException {
        if (userService.isUserInDB(IDFromPath)) {
            User singleUser = getValidSingleUserByID();
            writeSuccessfulResponse(exchange, singleUser.getName());
        } else {
            writeUnsuccessfulResponse(exchange, outputMessages.getString("error_getting_user"), StatusCodes.NOT_FOUND.getCode());
        }
    }

    private void handleAllUsers(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        switch (requestMethod) {
            case "GET":
                GetAllUsersHandler(exchange);
                break;
            case "POST":
                PostUserHandler(exchange);
                break;
            default:
                writeUnsuccessfulResponse(exchange, outputMessages.getString("request_error"), StatusCodes.NOT_ACCEPTED.getCode());
                break;
        }
    }

    private void GetAllUsersHandler(HttpExchange exchange) throws IOException {
        String allUsers = userService.readAll();
        writeSuccessfulResponse(exchange, allUsers);
    }

    private void PostUserHandler(HttpExchange exchange) throws IOException {
        extractNameFromBody(exchange.getRequestBody());
        if (!nameFromRequest.isEmpty()) {
            userService.createUser(nameFromRequest);
            writeSuccessfulResponse(exchange, outputMessages.getString("success_post_user"));
        } else {
            writeUnsuccessfulResponse(exchange, outputMessages.getString("error_post_user"), StatusCodes.BAD_REQUEST.getCode());

        }

    }

    private static void writeSuccessfulResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private static void writeUnsuccessfulResponse(HttpExchange exchange, String response, int statusCode) throws IOException {
        exchange.sendResponseHeaders(statusCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}