import db.User;
import db.UserDB;
import server.Server;

class Main {

    public static void main(String[] args) throws Exception {
        final User admin_user = new User(System.getenv("ADMIN_NAME"), true);
        final UserDB database = new UserDB();
        database.addUser(admin_user);

        Server server = new Server(database);
        server.createServerConnection();
    }
}