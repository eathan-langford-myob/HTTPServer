package handlers;

import DB.HashMapDB;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import utilities.UserNameFormatter;

import java.io.IOException;
import java.io.OutputStream;

    public class AllUsersHandler implements HttpHandler {
        private HashMapDB DB;

        public AllUsersHandler(HashMapDB database) {
            this.DB = database;
        }





        @Override
        public void handle(HttpExchange t) throws IOException {
            Headers responseHeaders = t.getResponseHeaders();
            responseHeaders.set("Content-Type", "text/plain");
            java.lang.String response = UserNameFormatter.collectNames(DB);
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
}
