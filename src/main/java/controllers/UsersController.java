package controllers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import db.UserDB;
import handlers.DeleteUserHandler;
import handlers.GetUserHandler;
import handlers.PostUserHandler;
import handlers.PutUserHandler;
import utilities.Constants;
import utilities.HttpUtils;

import java.io.IOException;

public class UsersController implements HttpHandler {
    private GetUserHandler getRequest;
    private PostUserHandler postRequest;
    private PutUserHandler putRequest;
    private DeleteUserHandler deleteRequest;


    public UsersController(UserDB database) {
        getRequest = new GetUserHandler(database);
        postRequest = new PostUserHandler(database);
        putRequest = new PutUserHandler(database);
        deleteRequest = new DeleteUserHandler(database);
    }

    private void callRequestHandler(HttpExchange exchange, String httpRequest) throws IOException {
        System.out.println("Request handler method");
        switch (httpRequest.toLowerCase()) {
            case (Constants.get):
                requestMethodCall(getRequest, exchange);
                break;
            case (Constants.post) :
                requestMethodCall(postRequest, exchange);
                break;
            case (Constants.update) :
                requestMethodCall(putRequest, exchange);
                break;
            case (Constants.delete) :
                requestMethodCall(deleteRequest, exchange);
                break;
        }
    }

    private void requestMethodCall(HttpHandler requestMethod, HttpExchange exchange) throws IOException {
        System.out.println("Request method call");
        try {
            requestMethod.handle(exchange);
        } catch (IOException error) {
            HttpUtils.writeResponse(exchange, error.getMessage());
        }
    }


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Users Controller handle method");
        Headers responseHeaders = exchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "text/plain");
        callRequestHandler(exchange, exchange.getRequestMethod());
    }
}
