import db.User;
import db.UserDB;
import server.Server;

public class Main {
    private static User admin_user = new User(System.getenv("ADMIN_NAME"), true);
    private static UserDB database = new UserDB();
    private static Server server;


    public static void main(String[] args) throws Exception {
        database.addUser(admin_user);
        server = new Server(database);
        server.createServerConnection();
    }
}