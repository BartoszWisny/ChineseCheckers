package games.chinesecheckers.gui;

import games.chinesecheckers.board.BoardCurrent;
import games.chinesecheckers.client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SkipTurnEvent implements EventHandler<ActionEvent> {
	private Client client;
	private BoardCurrent board;
	private boolean active;
	
	public SkipTurnEvent(Client client, BoardCurrent board) {
		this.client = client;
		this.board = board;
		this.active = false;
	}
	
	public void activate() {
		this.active = true;
	}
	
	public void setUnactive() {
		this.active = false;
	}
	
	public void handle(ActionEvent event) {
		if(active) {
			client.sendOption("skip");
			board.setLabel("Wait for your turn...");
			setUnactive();
			board.setUnactive();
		}
	}
}
