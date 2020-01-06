package server;

import com.sun.net.httpserver.HttpServer;
import controllers.UsersController;
import db.UserDB;
import handlers.GreetingHandler;
import utilities.Constants;

import java.net.InetSocketAddress;

public class Server {
    private HttpServer server;

    public void createServerConnection() throws Exception {
        UserDB database = new UserDB();
        System.out.println(Constants.database_success);

        server = HttpServer.create(new InetSocketAddress(Constants.port), 0);
        System.out.println(Constants.server_start);

        server.createContext(Constants.root_address, new GreetingHandler(database));
        System.out.println(Constants.root_success);

        server.createContext("/users", new UsersController(database));
        System.out.println(Constants.users_endpoint_success);

        server.setExecutor(null);
        server.start();
        System.out.println(Constants.server_success);
    }

    public void closeServerConnection() {
        server.stop(1);
    }
}
