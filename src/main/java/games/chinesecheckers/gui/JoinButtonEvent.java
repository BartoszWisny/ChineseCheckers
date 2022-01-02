package games.chinesecheckers.gui;

import games.chinesecheckers.client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * Klasa ustala akcjê zwi¹zan¹ z naciœniêciem przycisku do³¹czacego gracza do poczekalni (w poczekalni gracz mo¿e wybraæ rozgrywkê, do której chce on do³¹czyæ).
 */

public class JoinButtonEvent implements EventHandler<ActionEvent> {
	private Stage stage;
	private Client client;
	
	/**
	 * Konstruktor ustala obecne okno klienta i dane klienta.
	 */
	
	public JoinButtonEvent(Stage stage, Client client) {
		this.stage = stage;
		this.client = client;
	}
	
	/**
	 * Metoda ustawia typ gracza jako join, zamyka bie¿¹ce okno dla gracza i otwiera okno z mo¿liwoœci¹ wyboru rozgrywki z listy dostêpnych gier.
	 */
	
	public void handle(ActionEvent actionEvent) {
		client.sendOption("join");
		this.stage.close();
		InfoStage infoStage = new InfoStage("Waiting for more players...");
		infoStage.show();
		client.setStage(infoStage);
	}
}
