package controllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.UserDB;
import handlers.DeleteUserHandler;
import handlers.GetUserHandler;
import handlers.PostUserHandler;
import handlers.PutUserHandler;
import utilities.HttpUtils;
import utilities.StatusCodes;

import java.io.IOException;
import java.util.ResourceBundle;

public class UsersController implements HttpHandler {
    private GetUserHandler getHandler;
    private PostUserHandler postHandler;
    private PutUserHandler putHandler;
    private DeleteUserHandler deleteHandler;
    private ResourceBundle outputMessages;

    public static final String getRequest = "get";
    public static final String postRequest = "post";
    public static final String deleteRequest = "delete";
    public static final String updateRequest = "put";

    public UsersController(UserDB database, ResourceBundle outputMessages) {
        getHandler = new GetUserHandler(database, outputMessages);
        postHandler = new PostUserHandler(database, outputMessages);
        putHandler = new PutUserHandler(database, outputMessages);
        deleteHandler = new DeleteUserHandler(database, outputMessages);
    }

    private void callRequestHandler(HttpExchange exchange, String httpRequest) throws IOException {
        switch (httpRequest.toLowerCase()) {
            case (getRequest):
                requestMethodCall(getHandler, exchange);
                break;
            case (postRequest):
                requestMethodCall(postHandler, exchange);
                break;
            case (deleteRequest):
                requestMethodCall(putHandler, exchange);
                break;
            case (updateRequest):
                requestMethodCall(deleteHandler, exchange);
                break;
            default:
                HttpUtils.writeResponse(exchange, outputMessages.getString(""), StatusCodes.NOT_ACCEPTED.getCode());

        }
    }

    private void requestMethodCall(HttpHandler requestMethod, HttpExchange exchange) throws IOException {
        try {
            requestMethod.handle(exchange);
        } catch (IOException error) {
            HttpUtils.writeResponse(exchange, error.getMessage(), StatusCodes.BAD_REQUEST.getCode());
        }
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/plain");
        callRequestHandler(exchange, exchange.getRequestMethod());
    }
}
