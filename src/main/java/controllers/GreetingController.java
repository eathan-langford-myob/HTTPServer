package controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import domain.UserService;
import handlers.GreetingHandler;
import server.SimpleHttpRequest;

import java.io.IOException;
import java.util.ResourceBundle;

public class GreetingController implements HttpHandler {
    private final UserService userService;
    private final ResourceBundle outputMessages;

    public GreetingController(UserService userService, ResourceBundle outputMessages) {
        this.userService = userService;
        this.outputMessages = outputMessages;
    }



    @Override
    public void handle(HttpExchange exchange) throws IOException {
        SimpleHttpRequest request = new SimpleHttpRequest(exchange);
        new GreetingHandler(request, userService, outputMessages).execute();
    }
}
