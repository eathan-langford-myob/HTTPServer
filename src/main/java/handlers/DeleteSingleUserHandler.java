package handlers;

import domain.UserService;
import server.SimpleHttpRequest;
import server.SimpleHttpResponse;
import server.InvalidRequestException;
import utilities.StatusCodes;

import java.io.IOException;
import java.util.ResourceBundle;

public class DeleteSingleUserHandler implements Handler {
    private final UserService userService;
    private final SimpleHttpRequest request;
    private final ResourceBundle outputMessages;

    public DeleteSingleUserHandler(SimpleHttpRequest request, UserService userService, ResourceBundle outputMessages) {
        this.request = request;
        this.userService = userService;
        this.outputMessages = outputMessages;
    }

    @Override
    public void execute() throws IOException {
        try {
            userService.removeUserByID(request.getID());
            new SimpleHttpResponse(request).write(StatusCodes.OK.getCode(), outputMessages.getString("success_delete_user"));
        } catch (InvalidRequestException e) {
            new SimpleHttpResponse(request).write(StatusCodes.BAD_REQUEST.getCode(), outputMessages.getString(e.getMessage()));
        }
    }
}
