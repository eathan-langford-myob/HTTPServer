package server;

import com.sun.net.httpserver.HttpServer;
import controllers.UsersController;
import db.UserDB;
import handlers.GreetingHandler;

import java.net.InetSocketAddress;

public class Server {
    private HttpServer server;
    private final String root_address = System.getenv("ROOT_ADDRESS");
    private final String users_endpoint = System.getenv("USERS_ENDPOINT");
    private final int port = Integer.parseInt(System.getenv("PORT"));
    private UserDB DB;


    public Server(UserDB DB){
        this.DB = DB;
    }


    public void createServerConnection() throws Exception {
        System.out.println(System.getenv());
        server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext(root_address, new GreetingHandler(DB));

        server.createContext(users_endpoint, new UsersController(DB));

        server.setExecutor(null);
        server.start();
    }

    public void closeServerConnection() {
        server.stop(1);
    }
}
