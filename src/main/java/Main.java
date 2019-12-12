import server.Server;

public class Main {
private static Server server = new Server();

    public static void main(String[] args) throws Exception {
        server.createServerConnection();
    }

}