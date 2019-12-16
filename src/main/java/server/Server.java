package server;

import DB.HashMapDB;
import com.sun.net.httpserver.HttpServer;
import handlers.*;

import java.net.InetSocketAddress;

public class Server {
    public void createServerConnection() throws Exception {
        HashMapDB database = new HashMapDB();
        System.out.println("Database created");
        HttpServer server = HttpServer.create(new InetSocketAddress(4000), 0);
        System.out.println("Server created");
        server.createContext("/", new RootHandler(database));
        System.out.println("Root created");
        server.createContext("/post", new PostHandler(database));
        System.out.println("Post created");
        server.createContext("/delete", new DeleteHandler(database));
        System.out.println("Delete created");
        server.createContext("/allUsers", new AllUsersHandler(database));
        System.out.println("All Users Endpoint created");
        server.createContext("/put", new PutHandler(database));
        System.out.println("Put Endpoint created");
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Server started");
    }
}
