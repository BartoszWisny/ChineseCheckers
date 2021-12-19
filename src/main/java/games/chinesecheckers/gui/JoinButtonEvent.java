package games.chinesecheckers.gui;

import games.chinesecheckers.client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class EventForJoinButton implements EventHandler<ActionEvent> {
	private Stage stage;
	private Client client;
	
	public EventForJoinButton(Stage stage, Client client) {
		this.stage = stage;
		this.client = client;
	}
	
	public void handle(ActionEvent actionEvent) {
		client.sendOption("join");
		this.stage.close();
		InfoStage infoStage = new InfoStage("Waiting for more players...");
		infoStage.show();
		client.setStage(infoStage);
	}
}
