package games.chinesecheckers.gui;

import games.chinesecheckers.client.Client;

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

import java.util.List;

public class LobbyStage extends Stage {
    private int currentRow = 1;
    
    private class GameInfoRow {
        public Label gameIDLabel;
        public Label playersLabel;
        public Label joinedPlayersLabel;
        public JoinButton button;

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

    private class JoinButton extends Button {
        JoinButton(Boolean hasStarted) {
            setText("Join");
            setDisable(hasStarted == true);
        }
    }

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

        Scene scene = new Scene(pane, 400.0, 300.0);
        setScene(scene);
        setResizable(false);
    }
}
