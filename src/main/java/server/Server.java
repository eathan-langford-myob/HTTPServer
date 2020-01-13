package server;

import com.sun.net.httpserver.HttpServer;
import controllers.UsersController;
import db.UserDB;
import controllers.GreetingController;

import java.net.InetSocketAddress;
import java.util.Locale;
import java.util.ResourceBundle;

public class Server {
    private HttpServer server;
    private final String root_address = "/";
    private final String users_endpoint = "/users";
    private final int port = 4000;
    private UserDB DB;
    Locale locale = new Locale("en", "AU");
    private ResourceBundle outputMessages;

    public Server(UserDB DB){
        this.DB = DB;

        outputMessages = ResourceBundle.getBundle("OutputMessages", locale);
    }


    public void createServerConnection() throws Exception {
        server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext(root_address, new GreetingController(DB, outputMessages));

        server.createContext(users_endpoint, new UsersController(DB, outputMessages));

        server.setExecutor(null);
        server.start();
    }

    public void closeServerConnection() {
        server.stop(1);
    }
}