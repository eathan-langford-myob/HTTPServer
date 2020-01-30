package handlers;

import db.User;
import db.UserDB;
import server.Server;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestServer {
    public Server server;
    public User admin = new User("Eathan");
    public UserDB DB = new UserDB();
    public String localPath = "http://localhost:8080/";
    public String usersPath = "http://localhost:8080/users";
    public ResourceBundle outputMessages = ResourceBundle.getBundle("OutputMessages", new Locale("en", "AU"));

    public void setup() throws Exception {
        DB = new UserDB();
        DB.addUser(admin);
        server = new Server(DB);
        server.createServerConnection();
    }

    public void tearDown() {
        server.closeServerConnection();
    }
}
