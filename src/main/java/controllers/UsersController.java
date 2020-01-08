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

import java.io.IOException;

public class UsersController implements HttpHandler {
    private GetUserHandler getHandler;
    private PostUserHandler postHandler;
    private PutUserHandler putHandler;
    private DeleteUserHandler deleteHandler;
    public static final String getRequestString = "get";
    public static final String postRequestString = "post";
    public static final String deleteRequestString = "delete";
    public static final String updateRequestString = "put";


    public UsersController(UserDB database) {
        getHandler = new GetUserHandler(database);
        postHandler = new PostUserHandler(database);
        putHandler = new PutUserHandler(database);
        deleteHandler = new DeleteUserHandler(database);
    }

    private void callRequestHandler(HttpExchange exchange, String httpRequest) throws IOException {
        switch (httpRequest.toLowerCase()) {
            case (getRequestString):
                requestMethodCall(getHandler, exchange);
                break;
            case (postRequestString):
                requestMethodCall(postHandler, exchange);
                break;
            case (updateRequestString):
                requestMethodCall(putHandler, exchange);
                break;
            case (deleteRequestString):
                requestMethodCall(deleteHandler, exchange);
                break;

            default:


        }
    }

    private void requestMethodCall(HttpHandler requestMethod, HttpExchange exchange) throws IOException {
        try {
            requestMethod.handle(exchange);
        } catch (IOException error) {
            HttpUtils.writeResponse(exchange, error.getMessage());
        }
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/plain");
        callRequestHandler(exchange, exchange.getRequestMethod());
    }
}
