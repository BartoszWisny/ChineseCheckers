package games.chinesecheckers.gui;

import games.chinesecheckers.client.Client;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.util.List;

/**
 * Klasa odpowiada za wygenerowanie okna z mo�liwo�ci� wyboru rozgrywki z listy dost�pnych gier.
 */

public class LobbyStage extends Stage {
	private int currentRow = 1;
    private boolean noGameToJoin = true;
	
    /**
     * Klasa odpowiada za wygenerowanie danych (widoku dla okna) dla pojedynczej rozgrywki znajduj�cej si� na li�cie gier.
     */
    
    private class GameInfoRow {
        public Label gameIDLabel;
        public Label playersLabel;
        public Label joinedPlayersLabel;
        public JoinButton button;

        /**
         * Konstruktor generuje dane (widok dla okna) dla pojedynczej rozgrywki znajduj�cej si� na li�cie gier.
         */
        
        public GameInfoRow(String gameID, String players, String joinedPlayers, String hasStarted) {
            gameIDLabel = new Label(gameID);
            gameIDLabel.setFont(new Font("Times New Roman", 14));
            playersLabel = new Label(players);
            playersLabel.setFont(new Font("Times New Roman", 14));
            joinedPlayersLabel = new Label(joinedPlayers);
            joinedPlayersLabel.setFont(new Font("Times New Roman", 14));
            button = new JoinButton(Boolean.valueOf(hasStarted));
            button.setFont(new Font("Times New Roman", 14));
        }
    }
    
    /**
     * Klasa odpowiada za wygenerowanie przycisku, kt�ry pozwala do��czy� do wybranej rozgrywki.
     */

    private class JoinButton extends Button {
        
    	/**
         * Konstruktor generuje przycisk, kt�ry pozwala do��czy� do wybranej rozgrywki i ustala go jako nieaktywny, je�eli dana rozgrywka si� rozpocz�a.
         */
    	
    	JoinButton(Boolean hasStarted) {
            setText("Join");
            setDisable(hasStarted == true);
        }
    }
    
    /**
     * Konstruktor generuje okno z mo�liwo�ci� wyboru rozgrywki z listy dost�pnych gier i odpowiada za zarz�dzanie obs�ug� okna.
     */

    public LobbyStage(final Client client, List<String> args) {
        GridPane pane = new GridPane();
        pane.setVgap(20);
        pane.setHgap(30);
        pane.setAlignment(Pos.CENTER);
        
        Label gameID = new Label("Game ID");
        gameID.setFont(new Font("Times New Roman", 16));
        Label players = new Label("Players");
        players.setFont(new Font("Times New Roman", 16));
        Label joinedPlayers = new Label("Joined players");
        joinedPlayers.setFont(new Font("Times New Roman", 16));
        Label joinGame = new Label("Join game");
        joinGame.setFont(new Font("Times New Roman", 16));

        ObservableList<GameInfoRow> rows = FXCollections.observableArrayList();
        
        pane.addRow(0, gameID, players, joinedPlayers, joinGame);
        pane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setStyle("-fx-border-color: black");
        
        for (String argLine : args) {
            String[] parameters = argLine.split(" ");
            final GameInfoRow row = new GameInfoRow(parameters[1], parameters[2], parameters[3] + "/" + parameters[2], parameters[4]);
            
            row.button.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
				    client.sendOption("choose " + row.gameIDLabel.getText());
				    LobbyStage.this.close();
				}
			});
            
            rows.add(row);
        }

        for (GameInfoRow row : rows) {
            pane.addRow(currentRow, row.gameIDLabel, row.playersLabel, row.joinedPlayersLabel, row.button);
            currentRow++;
        }
        
        Scene scene = new Scene(pane, 400.0, currentRow * 50.0);
	    setScene(scene);
	    setResizable(false);
	    initStyle(StageStyle.UNDECORATED);

        for (GameInfoRow row : rows) {
        	if (!row.button.isDisabled()) {
        		noGameToJoin = false;
        	}
        }
        
        if (noGameToJoin) {
        	Platform.runLater(new Runnable() {
				public void run() {
					LobbyStage.this.close();
					final InfoStage newStage = new InfoStage("No game found!");
					newStage.show();
					PauseTransition delay = new PauseTransition(Duration.seconds(10));
					delay.setOnFinished(new EventHandler<ActionEvent>() {
						public void handle(ActionEvent event) {
							newStage.close();
							System.exit(0);
						}
					});
					delay.play();
				}
			});
        }
        
        Platform.setImplicitExit(false);
		this.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
		        event.consume();
		        final InfoStage newStage = new InfoStage("You can't close this game!");
			    newStage.show();
			    PauseTransition delay = new PauseTransition(Duration.seconds(10));
				delay.setOnFinished( new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						newStage.close();
					}
				});
				delay.play();
		    }
		});
    }
}
