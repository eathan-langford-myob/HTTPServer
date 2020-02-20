package controllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import domain.UserService;
import handlers.DeleteSingleUserHandler;
import handlers.GetSingleUserHandler;
import handlers.UpdateSingleUserHandler;
import server.SimpleHttpRequest;
import server.SimpleHttpResponse;
import utilities.StatusCodes;
import utilities.UserHttpRequestValidator;

import java.io.IOException;
import java.util.ResourceBundle;

public class UsersIDController implements HttpHandler {
    private final UserService userService;
    private final ResourceBundle outputMessages;

    public UsersIDController(UserService userService, ResourceBundle outputMessages) {
        this.userService = userService;
        this.outputMessages = outputMessages;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/plain");
        SimpleHttpRequest request = new SimpleHttpRequest(exchange);

        if (UserHttpRequestValidator.isValidIdRequest(request.getPath())) {
            switch (request.getMethod()) {
                case "GET":
                    new GetSingleUserHandler(request, userService, outputMessages).execute();
                    break;
                case "DELETE":
                    new DeleteSingleUserHandler(request, userService, outputMessages).execute();
                    break;
                case "PUT":
                    new UpdateSingleUserHandler(request, userService, outputMessages).execute();
                    break;
                default:
                    new SimpleHttpResponse(request).write(StatusCodes.NOT_ACCEPTED.getCode(),outputMessages.getString("request_error"));
                    break;
            }
        } else {
            new SimpleHttpResponse(request).write(StatusCodes.BAD_REQUEST.getCode(),"path_error");
        }
    }
}
