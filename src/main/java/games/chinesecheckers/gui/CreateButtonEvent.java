package games.chinesecheckers.gui;

import games.chinesecheckers.client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class CreateButtonEvent implements EventHandler<ActionEvent> {
	private Stage stage;
	private Client client;
	
	public CreateButtonEvent(Stage stage, Client client) {
		this.stage = stage;
		this.client = client;
	}
	
	public void handle(ActionEvent actionEvent) {
		client.sendOption("host");
		this.stage.close();
		CreateGameStage newStage = new CreateGameStage(client);
		newStage.show();
	}
}