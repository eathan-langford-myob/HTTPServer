package controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.User;
import domain.UserService;
import utilities.HttpUtils;
import utilities.InvalidRequestException;
import utilities.StatusCodes;
import utilities.UserHttpRequestValidator;

import java.io.IOException;
import java.util.ResourceBundle;

public class UsersIDController implements HttpHandler {
    private final UserService userService;
    private int IDFromPath;
    private final ResourceBundle outputMessages;

    public UsersIDController(UserService userService, ResourceBundle resourceBundle) {
        this.userService = userService;
        this.outputMessages = resourceBundle;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestPath = exchange.getRequestURI().getPath();
        IDFromPath = HttpUtils.getIdFromPath(requestPath);
        String requestMethod = exchange.getRequestMethod();

        if (UserHttpRequestValidator.isValidIdRequest(requestPath)) {
            switch (requestMethod) {
                case "GET":
                    requestUserByIdHandler(exchange);
                    break;
                case "DELETE":
                    deleteUserHandler(exchange);
                    break;
                case "PUT":
                    updateUserHandler(exchange);
                    break;
                default:
                    HttpUtils.writeResponse(StatusCodes.NOT_ACCEPTED.getCode(), exchange, outputMessages.getString("request_error"));
                    break;
            }
        } else {
            HttpUtils.writeResponse(StatusCodes.BAD_REQUEST.getCode(), exchange, outputMessages.getString("path_error"));
        }
    }

    private void updateUserHandler(HttpExchange exchange) throws IOException {
        String nameFromRequest = HttpUtils.getRequestFromBody(exchange.getRequestBody());
        try {
            String newName = nameFromRequest.split(",")[1].trim();
            userService.updateUserNameByID(IDFromPath, newName);
            HttpUtils.writeResponse(StatusCodes.OK.getCode(), exchange, outputMessages.getString("success_put_user"));
        } catch (InvalidRequestException e) {
            HttpUtils.writeResponse(StatusCodes.BAD_REQUEST.getCode(), exchange, e.getMessage());
        }
    }

    private void deleteUserHandler(HttpExchange exchange) throws IOException {
        try {
            userService.removeUserByID(IDFromPath);
            HttpUtils.writeResponse(StatusCodes.OK.getCode(), exchange, outputMessages.getString("success_delete_user"));
        } catch (InvalidRequestException e) {
            HttpUtils.writeResponse(StatusCodes.BAD_REQUEST.getCode(), exchange, e.getMessage());
        }
    }

    private void requestUserByIdHandler(HttpExchange exchange) throws IOException {
        try {
            User singleUser = userService.ReadByID(IDFromPath);
            HttpUtils.writeResponse(StatusCodes.CREATED.getCode(), exchange, singleUser.getName());
        } catch (InvalidRequestException e) {
            HttpUtils.writeResponse(StatusCodes.BAD_REQUEST.getCode(), exchange, e.getMessage());
        }
    }
}
