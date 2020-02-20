package handlers;

import domain.UserService;
import server.SimpleHttpRequest;
import server.SimpleHttpResponse;
import utilities.StatusCodes;

import java.io.IOException;

public class GetAllUsersHandler implements Handler {
    private final UserService userService;
    private final SimpleHttpRequest request;

    public GetAllUsersHandler(SimpleHttpRequest request, UserService userService) {
        this.request = request;
        this.userService = userService;
    }

    @Override
    public void execute() throws IOException {
        String allUsers = userService.readAll();
        new SimpleHttpResponse(request).write(StatusCodes.OK.getCode(), allUsers);
    }
}
