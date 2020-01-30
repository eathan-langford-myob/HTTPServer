package controllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import domain.UserService;
import utilities.HttpUtils;
import utilities.StatusCodes;

import java.io.IOException;
import java.util.ResourceBundle;

public class UsersController implements HttpHandler {
    private ResourceBundle outputMessages;
    private UserService userService;


    public UsersController(UserService userService, ResourceBundle outputMessages) {
        this.userService = userService;
        this.outputMessages = outputMessages;
    }


@Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/plain");
        String requestMethod = exchange.getRequestMethod();
        switch (requestMethod) {
            case "GET":
                GetAllUsersHandler(exchange);
                break;
            case "POST":
                PostUserHandler(exchange);
                break;
            default:
                HttpUtils.writeResponse( StatusCodes.NOT_ACCEPTED.getCode(), exchange, outputMessages.getString("request_error"));
                break;
        }
    }

    private void GetAllUsersHandler(HttpExchange exchange) throws IOException {
        String allUsers = userService.readAll();
        HttpUtils.writeResponse(StatusCodes.OK.getCode(), exchange, allUsers);
    }

    private void PostUserHandler(HttpExchange exchange) throws IOException {
        String nameFromRequest = HttpUtils.getRequestFromBody(exchange.getRequestBody());
        if (!nameFromRequest.isEmpty()) {
            userService.createUser(nameFromRequest);
            HttpUtils.writeResponse(StatusCodes.OK.getCode(), exchange, outputMessages.getString("success_post_user"));
        } else {
            HttpUtils.writeResponse(StatusCodes.BAD_REQUEST.getCode(), exchange, outputMessages.getString("error_post_user"));

        }

    }
}