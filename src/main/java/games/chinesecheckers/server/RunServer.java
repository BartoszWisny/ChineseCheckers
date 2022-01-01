package games.chinesecheckers.server;

public class RunServer {
    public static void main(String[] args) {
    	Server server;
    	try {
            server = new Server(8080);
            server.listen();
        }
        catch (Exception e) {
        	System.out.println("Server is already running!");
        }
    }
}
