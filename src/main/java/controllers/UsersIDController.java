package controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.User;
import domain.UserService;
import utilities.HttpRequestValidator;
import utilities.HttpUtils;
import utilities.StatusCodes;

import java.io.IOException;
import java.util.ResourceBundle;

public class UsersIDController implements HttpHandler {
    private UserService userService;
    private int IDFromPath;
    private String requestPath;
    private ResourceBundle outputMessages;

    public UsersIDController(UserService userService, ResourceBundle resourceBundle) {
        this.userService = userService;
        this.outputMessages = resourceBundle;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        requestPath = exchange.getRequestURI().getPath();
        IDFromPath = HttpUtils.getIdFromPath(requestPath);
        String requestMethod = exchange.getRequestMethod();
        if (HttpRequestValidator.isValidIdRequest(requestPath)) {
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
            HttpUtils.writeResponse( StatusCodes.BAD_REQUEST.getCode(), exchange, outputMessages.getString("path_error"));
        }
    }

    private void updateUserHandler(HttpExchange exchange) throws IOException {
        String nameFromRequest = HttpUtils.getRequestFromBody(exchange.getRequestBody());
        if (!nameFromRequest.isEmpty()
                && HttpRequestValidator.isValidUpdateRequest(nameFromRequest)
                && userService.isSameName(IDFromPath, nameFromRequest)
                && !userService.isUserAdmin(IDFromPath)) {
            String newName = nameFromRequest.split(",")[1].trim();
            userService.updateUserNameByID(IDFromPath, newName);
            HttpUtils.writeResponse(StatusCodes.OK.getCode(), exchange, outputMessages.getString("success_put_user"));
        } else {
            HttpUtils.writeResponse( StatusCodes.BAD_REQUEST.getCode(), exchange, outputMessages.getString("error_put_user"));

        }
    }

    private void deleteUserHandler(HttpExchange exchange) throws IOException {
        if (userService.isUserInDB(IDFromPath)) {
            userService.removeUserByID(IDFromPath);
            HttpUtils.writeResponse(StatusCodes.OK.getCode(), exchange, outputMessages.getString("success_delete_user"));
        } else {
            HttpUtils.writeResponse( StatusCodes.BAD_REQUEST.getCode(), exchange, outputMessages.getString("error_delete_user"));
        }
    }

    private User getValidSingleUserByID() {
        return userService.ReadByID(IDFromPath);
    }

    private void requestUserByIdHandler(HttpExchange exchange) throws IOException {
        if (userService.isUserInDB(IDFromPath)) {
            User singleUser = getValidSingleUserByID();
            HttpUtils.writeResponse(StatusCodes.OK.getCode(), exchange, singleUser.getName());
        } else {
            HttpUtils.writeResponse( StatusCodes.BAD_REQUEST.getCode(), exchange, outputMessages.getString("error_getting_user"));
        }
    }
}
