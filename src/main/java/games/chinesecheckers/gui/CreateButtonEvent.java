package games.chinesecheckers.gui;

import games.chinesecheckers.client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * Klasa ustala akcj� zwi�zan� z naci�ni�ciem przycisku tworz�cego now� rozgrywk�.
 */

public class CreateButtonEvent implements EventHandler<ActionEvent> {
	private Stage stage;
	private Client client;
	
	/**
	 * Konstruktor ustala obecne okno klienta i dane klienta.
	 */
	
	public CreateButtonEvent(Stage stage, Client client) {
		this.stage = stage;
		this.client = client;
	}
	
	/**
	 * Metoda ustawia typ gracza jako host, zamyka bie��ce okno dla gracza i otwiera okno z mo�liwo�ci� wyboru liczby graczy dla danej rozgrywki.
	 */
	
	public void handle(ActionEvent actionEvent) {
		client.sendOption("host");
		this.stage.close();
		CreateGameStage newStage = new CreateGameStage(client);
		newStage.show();
	}
}
