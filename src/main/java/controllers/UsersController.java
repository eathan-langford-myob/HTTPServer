package controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import domain.UserService;
import handlers.CreateNewUserHandler;
import handlers.GetAllUsersHandler;
import server.SimpleHttpRequest;
import server.SimpleHttpResponse;
import utilities.StatusCodes;

import java.io.IOException;
import java.util.ResourceBundle;

public class UsersController implements HttpHandler {
    private final ResourceBundle outputMessages;
    private final UserService userService;


    public UsersController(UserService userService, ResourceBundle outputMessages) {
        this.userService = userService;
        this.outputMessages = outputMessages;
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        SimpleHttpRequest request = new SimpleHttpRequest(exchange);

        if (!request.getPath().equals("/users")) {
            new SimpleHttpResponse(request).write(StatusCodes.BAD_REQUEST.getCode(), "path_error");
        }
//        Headers responseHeaders = exchange.getResponseHeaders();
//        responseHeaders.set("Content-Type", "text/plain");

        switch (request.getMethod()) {
            case "GET":
               new GetAllUsersHandler(request, userService).execute();
                break;
            case "POST":
                new CreateNewUserHandler(request, userService, outputMessages).execute();
                break;
            default:
                new SimpleHttpResponse(request).write(StatusCodes.NOT_ACCEPTED.getCode(), outputMessages.getString("request_error"));
                break;
        }
    }
}