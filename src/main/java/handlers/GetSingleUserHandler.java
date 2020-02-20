package handlers;

import db.User;
import domain.UserService;
import server.SimpleHttpRequest;
import server.SimpleHttpResponse;
import server.InvalidRequestException;
import utilities.StatusCodes;

import java.io.IOException;
import java.util.ResourceBundle;

public class GetSingleUserHandler implements Handler {
    private final ResourceBundle outputMessages;
    private final UserService userService;
    private final SimpleHttpRequest request;

    public GetSingleUserHandler(SimpleHttpRequest request, UserService userService, ResourceBundle outputMessages) {
        this.request = request;
        this.userService = userService;
        this.outputMessages = outputMessages;
    }

    @Override
    public void execute() throws IOException {
        try {
            User singleUser = userService.ReadByID(request.getID());
            new SimpleHttpResponse(request).write(StatusCodes.CREATED.getCode(), singleUser.getName());
        } catch (InvalidRequestException e) {
            new SimpleHttpResponse(request).write(StatusCodes.BAD_REQUEST.getCode(), outputMessages.getString(e.getMessage()));
        }
    }
}
