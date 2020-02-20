package server;

import com.sun.net.httpserver.HttpExchange;

import java.util.Scanner;

public class SimpleHttpRequest {
    private String body;
    private long ID;
    private String path;
    private String method;
    private HttpExchange exchange;

    public SimpleHttpRequest(HttpExchange exchange) {
        this.body = getRequestFromBody(exchange);
        this.method = exchange.getRequestMethod();
        this.path = exchange.getRequestURI().getPath();
        this.ID = getIdFromPath(this.path);
        this.exchange = exchange;

    }

    public String getBody() {
        return body;
    }

    public long getID() {
        return ID;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public HttpExchange getExchange() {
        return exchange;
    }

    private String getRequestFromBody(HttpExchange exchange) {
        try (Scanner response = new Scanner(exchange.getRequestBody()).useDelimiter("\\A")) {
            return response.hasNext() ? response.next() : "";
        }
    }

        private Integer getIdFromPath(String path) {
            return Integer.parseInt(path.split("/users")[1].replace("/", ""));
        }

}
