package handlers;

import domain.UserService;
import server.SimpleHttpRequest;
import server.SimpleHttpResponse;
import utilities.StatusCodes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import static utilities.UserStringFormatter.formatUsersToString;

public class GreetingHandler implements Handler {
    private UserService userService;
    private SimpleHttpRequest request;
    private ResourceBundle outputMessages;

    public GreetingHandler(SimpleHttpRequest request, UserService userService, ResourceBundle outputMessages) {
        this.request = request;
        this.userService = userService;
        this.outputMessages = outputMessages;
    }

    private String collectNames() {
        ArrayList<String> collectionOfNamesFromDB = userService.collectUserNames();
        return formatUsersToString(collectionOfNamesFromDB);
    }

    @Override
    public void execute() throws IOException {
        if (!request.getPath().equals("/")) {
            new SimpleHttpResponse(request).write(StatusCodes.NOT_FOUND.getCode(), outputMessages.getString("path_error"));
            return;
        }
//        Headers responseHeaders = exchange.getResponseHeaders();
//        responseHeaders.set("Content-Type", "text/plain");
        String users = collectNames();
        String response = outputMessages.getString("hello") + users + outputMessages.getString("time") + new Date().toString();
        new SimpleHttpResponse(request).write(StatusCodes.OK.getCode(), response);
    }
}
