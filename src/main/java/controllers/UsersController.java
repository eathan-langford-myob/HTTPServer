package controllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import domain.UserService;
import utilities.HttpUtils;
import utilities.InvalidRequestException;
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

    if (!exchange.getRequestURI().getPath().equals("/users")) {
        HttpUtils.writeResponse(StatusCodes.BAD_REQUEST.getCode(), exchange, outputMessages.getString("path_error"));
    }
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/plain");;

        switch (exchange.getRequestMethod()) {
            case "GET":
                getAllUsersHandler(exchange);
                break;
            case "POST":
                postUserHandler(exchange);
                break;
            default:
                HttpUtils.writeResponse( StatusCodes.NOT_ACCEPTED.getCode(), exchange, outputMessages.getString("request_error"));
                break;
        }
    }

    private void getAllUsersHandler(HttpExchange exchange) throws IOException {
        String allUsers = userService.readAll();
        HttpUtils.writeResponse(StatusCodes.OK.getCode(), exchange, allUsers);
    }

    private void postUserHandler(HttpExchange exchange) throws IOException {
        String nameFromRequest = HttpUtils.getRequestFromBody(exchange.getRequestBody());
        try {
            userService.createUser(nameFromRequest);
            HttpUtils.writeResponse(StatusCodes.CREATED.getCode(), exchange, outputMessages.getString("success_post_user"));
        } catch(InvalidRequestException e) {
            HttpUtils.writeResponse(StatusCodes.BAD_REQUEST.getCode(), exchange, e.getMessage());

        }

    }
}