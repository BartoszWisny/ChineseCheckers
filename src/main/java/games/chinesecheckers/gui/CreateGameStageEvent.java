package games.chinesecheckers.gui;

import games.chinesecheckers.client.Client;
// import games.chinesecheckers.game.gamesettings.GameSettings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class EventForCreateGameStage implements EventHandler<ActionEvent> {
	private Stage stage;
	private String playersNumber;
	private Client client;
	
	public EventForCreateGameStage(Stage stage, String playersNumber, Client client) {
		this.stage = stage;
		this.playersNumber = playersNumber;
		this.client = client;
	}
	
	public void handle(ActionEvent event) {
		String string = playersNumber + "o";
		
		client.sendOption(string);
		this.stage.close();
		
		InfoStage infoStage = new InfoStage("Waiting for more players...");
		infoStage.show();
		client.setStage(infoStage);
	}
}
