package games.chinesecheckers.gui;

import games.chinesecheckers.client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class LobbyStage extends Stage {
    private int currentRow = 1;
    
    private class GameInfoRow {
        public Label humanPlayerLabel;
        public Label tempLabel;
        public Label numJoinedLabel;
        public Label gameIdLabel;
        public JoinButton joinButton;

        public GameInfoRow(String humanPlayers, String temp, String joinedPlayers, String gameId, String hasStarted) {
            humanPlayerLabel = new Label(humanPlayers);
            tempLabel = new Label(temp);
            numJoinedLabel = new Label(joinedPlayers);
            gameIdLabel = new Label(gameId);
            joinButton = new JoinButton(Integer.parseInt(hasStarted));
        }
    }

    private class JoinButton extends Button {
        JoinButton(int gameStarted) {
            setText("Join");
            setDisable(gameStarted == 1);
        }
    }

    public LobbyStage(final Client client, List<String> args) {
    	GridPane grid = new GridPane();
        grid.setVgap(50);
        grid.setHgap(50);
        grid.setAlignment(Pos.CENTER);

        Label humans = new Label("Human players");
        Label bots = new Label("Temp");
        Label joined = new Label("Joined players");
        Label id = new Label("Game id:");
        Label started = new Label("In progress: ");

        grid.addRow(0, humans, bots, joined, id, started);
        List<GameInfoRow> rows = new ArrayList<GameInfoRow>();
        for (String argLine : args) {
            String[] params = argLine.split(" ");
            final GameInfoRow row = new GameInfoRow(params[1], params[2], params[3], params[4], params[5]);
            row.joinButton.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
				    client.sendOption("choose "+row.gameIdLabel.getText());
				    LobbyStage.this.close();
				}
			});
            rows.add(row);
        }

        for (GameInfoRow row : rows) {
            grid.addRow(currentRow, row.humanPlayerLabel, row.tempLabel, row.numJoinedLabel, row.gameIdLabel, row.joinButton);
            currentRow++;
        }

        Scene scene = new Scene(grid);
        setScene(scene);


    }

}