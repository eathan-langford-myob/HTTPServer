package server;

import com.sun.net.httpserver.HttpServer;
import controllers.UsersController;
import controllers.UsersIDController;
import db.UserDB;
import controllers.GreetingController;
import domain.UserService;

import java.net.InetSocketAddress;
import java.util.Locale;
import java.util.ResourceBundle;

public class Server {
    private HttpServer server;
    private final UserService userService;
    private final ResourceBundle outputMessages;

    public Server(UserDB DB){
        Locale locale = new Locale("en", "AU");
        outputMessages = ResourceBundle.getBundle("OutputMessages", locale);
        this.userService = new UserService(DB, outputMessages);
    }


    public void createServerConnection() throws Exception {
        int port = 8080;
        String users_id_endpoint = "/users/";
        String users_endpoint = "/users";
        String root_address = "/";

        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext(root_address, new GreetingController(userService, outputMessages));
        server.createContext(users_id_endpoint, new UsersIDController(userService, outputMessages));
        server.createContext(users_endpoint, new UsersController(userService, outputMessages));

        server.setExecutor(null);
        server.start();
        System.out.println("Server Connection Created");
    }

    public void closeServerConnection() {
        server.stop(1);
    }
}