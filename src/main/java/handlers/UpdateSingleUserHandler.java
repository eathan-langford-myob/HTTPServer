package handlers;

import domain.UserService;
import server.InvalidRequestException;
import server.SimpleHttpRequest;
import server.SimpleHttpResponse;
import utilities.StatusCodes;

import java.io.IOException;
import java.util.ResourceBundle;

public class UpdateSingleUserHandler implements Handler {
    private UserService userService;
    private SimpleHttpRequest request;
    private final ResourceBundle outputMessages;

    public UpdateSingleUserHandler(SimpleHttpRequest request, UserService userService, ResourceBundle outputMessages) {
        this.request = request;
        this.userService = userService;
        this.outputMessages = outputMessages;
    }

    @Override
    public void execute() throws IOException {
        try {
            String newName = request.getBody().split(",")[1].trim();
            userService.updateUserNameByID(request.getID(), newName);
            new SimpleHttpResponse(request).write(StatusCodes.OK.getCode(), outputMessages.getString("success_put_user"));
        } catch (InvalidRequestException e) {
            new SimpleHttpResponse(request).write(StatusCodes.BAD_REQUEST.getCode(), outputMessages.getString(e.getMessage()));
        }
    }
}
