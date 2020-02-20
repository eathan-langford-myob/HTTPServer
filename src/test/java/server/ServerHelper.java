package server;

import db.User;
import db.UserDB;

import java.util.Locale;
import java.util.ResourceBundle;

public class ServerHelper {
    private Server server;
    public final User admin = new User("Eathan", true);
    public final String localPath = "http://localhost:8080/";
    public final String usersPath = "http://localhost:8080/users";
    public final ResourceBundle outputMessages = ResourceBundle.getBundle("OutputMessages", new Locale("en", "AU"));

    public void setup() throws Exception {
        UserDB DB = new UserDB();
        DB.addUser(admin);
        server = new Server(DB);
        server.createServerConnection();
    }

    public void tearDown() {
        server.closeServerConnection();
    }
}
