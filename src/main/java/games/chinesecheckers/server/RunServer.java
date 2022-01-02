package games.chinesecheckers.server;

/**
 * Klasa odpowiada za uruchomienie serwera gry.
 */

public class RunServer {
    
	/**
	 * Metoda uruchamia serwer gry pod ustalonym portem i zwraca odpowiedni komunikat, jeœli serwer jest ju¿ uruchomiony.
	 */
	
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
