package games.chinesecheckers.server;

public class RunServer {
    public static void main(String[] args) {
        try {
            Server server = new Server(8080);
            server.listen();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
