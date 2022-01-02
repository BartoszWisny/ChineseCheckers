package games.chinesecheckers.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.stage.Stage;

/**
 * Klasa tworzy nowego klienta, ustawia go pod odpowiednim adresem i portem oraz rozpoczyna dzia³anie elementu nas³uchuj¹cego informacji wysy³anych przez innych graczy i serwer gry.
 */

public class Client {
	private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
	
	private Stage stage;
	
	/**
	 * Konstruktor ustawia gniazdo na przekazanym adresie i porcie w celu po³¹czenia siê z serwerem gry oraz rozpoczyna dzia³anie klasy odpowiedzialnej za nas³uchiwanie informacji wysy³anych przez innych graczy i serwer gry.
	 */
	
	public Client(String address, int port) throws IOException {
		socket = new Socket(address, port);
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
        
        ListenerThread listener = new ListenerThread(in, this);
        listener.start();
	}
	
	/**
	 * Metoda odpowiada za wypisanie odpowiedniej informacji.
	 */
	
	public void sendOption(String option) {
		out.println(option);
	}
	
	/**
	 * Metoda odpowiada za ustawienie odpowiedniego okna dla klienta.
	 */
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	/**
	 * Metoda odpowiada za zamkniêcie poprzedniego okna wyœwietlanego dla danego klienta.
	 */
	
	public void closePreviousStage() {
		if(this.stage != null)
			stage.close();
	}
}
