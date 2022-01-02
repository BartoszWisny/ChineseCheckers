package games.chinesecheckers.gui;

import games.chinesecheckers.board.BoardCurrent;
import games.chinesecheckers.client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Klasa ustala akcjê zwi¹zan¹ z naciœniêciem przycisku pomijaj¹cego lub koñcz¹cego ruch gracza w danej kolejce.
 */

public class SkipTurnEvent implements EventHandler<ActionEvent> {
	private Client client;
	private BoardCurrent board;
	private boolean active;
	
	/**
	 * Konstruktor ustala obecne okno klienta, dane klienta i ustawia przycisk pomijaj¹cy lub koñcz¹cy ruch gracza w danej kolejce jako nieaktywny.
	 */
	
	public SkipTurnEvent(Client client, BoardCurrent board) {
		this.client = client;
		this.board = board;
		this.active = false;
	}
	
	/**
	 * Metoda aktywuje przycisk pomijaj¹cy lub koñcz¹cy ruch gracza w danej kolejce.
	 */
	
	public void activate() {
		this.active = true;
	}
	
	/**
	 * Metoda ustawia przycisk pomijaj¹cy lub koñcz¹cy ruch gracza w danej kolejce jako nieaktywny.
	 */
	
	public void setUnactive() {
		this.active = false;
	}
	
	/**
	 * Metoda dla aktywnego przycisku pomijaj¹cego lub koñcz¹cego ruch gracza w danej kolejce po jego naciœniêciu ustawia bie¿¹ce okno dla gracza jako nieaktywne i przycisk jako nieaktywny.
	 */
	
	public void handle(ActionEvent event) {
		if(active) {
			client.sendOption("skip");
			board.setLabel("Wait for your turn...");
			setUnactive();
			board.setUnactive();
		}
	}
}
