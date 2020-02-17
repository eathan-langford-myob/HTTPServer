package controllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import domain.UserService;
import utilities.HttpUtils;
import utilities.StatusCodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import static utilities.UserStringFormatter.formatUsersToString;

public class GreetingController implements HttpHandler {
    private UserService userService;
    private ResourceBundle outputMessages;

    public GreetingController(UserService userService, ResourceBundle outputMessages) {
        this.userService = userService;
        this.outputMessages = outputMessages;
    }

    private String collectNames() {
        ArrayList<String> collectionOfNamesFromDB = userService.collectUserNames();
        return formatUsersToString(collectionOfNamesFromDB);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestURI().getPath().equals("/")) {
            HttpUtils.writeResponse(StatusCodes.NOT_FOUND.getCode(), exchange, outputMessages.getString("path_error"));
            return;
        }

        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/plain");
        String users = collectNames();
        String response = outputMessages.getString("hello") + users + outputMessages.getString("time") + new Date().toString();
        HttpUtils.writeResponse(StatusCodes.OK.getCode(), exchange, response);
    }
}
