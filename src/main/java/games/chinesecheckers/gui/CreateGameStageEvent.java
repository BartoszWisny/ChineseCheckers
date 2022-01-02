package games.chinesecheckers.gui;

import games.chinesecheckers.client.Client;
// import games.chinesecheckers.game.gamesettings.GameSettings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * Klasa ustala akcj� zwi�zan� z naci�ni�ciem przycisku zatwierdzaj�cego wyb�r liczby graczy dla danej rozgrywki.
 */

public class CreateGameStageEvent implements EventHandler<ActionEvent> {
	private Stage stage;
	private String playersNumber;
	private Client client;
	
	/**
	 * Konstruktor ustala obecne okno klienta, dane klienta i wybran� liczb� graczy dla danej rozgrywki.
	 */
	
	public CreateGameStageEvent(Stage stage, String playersNumber, Client client) {
		this.stage = stage;
		this.playersNumber = playersNumber;
		this.client = client;
	}
	
	/**
	 * Metoda ustala liczb� graczy dla danej rozgrywki, zamyka bie��ce okno dla gracza i otwiera okno oczekiwania na kolejnych graczy.
	 */
	
	public void handle(ActionEvent event) {
		String string = playersNumber + "o";
		
		client.sendOption(string);
		this.stage.close();
		
		InfoStage infoStage = new InfoStage("Waiting for more players...");
		infoStage.show();
		client.setStage(infoStage);
	}
}
